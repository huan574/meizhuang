package com.meizhuang.utils.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;

import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;

import com.meizhuang.config.properties.PropertiesUtils;
import com.meizhuang.result.JsonResult;



import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;



import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.set.SetParams;


public class RedisUtil {

	private static Logger logger = Logger.getLogger(RedisUtil.class);
	

	private static JedisPool JedisPool = null;
	
	private static String host = null;
	private static String port = null;

	private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    
	static {
		// 初始化配置文件
		String profiles = PropertiesUtils.getProperty("spring.profiles.active");
		PropertiesConfiguration config = loadConfig("cachecloud-" + profiles + ".properties");
		host = config.getString("host");
		port = config.getString("port");
		
	}
	
	/**
	 * 根据配置文件名读取配置文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static PropertiesConfiguration loadConfig(String fileName) {
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration(fileName);
			config.setEncoding("UTF-8");
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
			config.setAutoSave(true);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return config;
	}

	
	
	public static Jedis getJedis(){
		
		JedisPool jp = getJedisPool();
		Jedis jedis = jp.getResource();
		return jedis;
	}
	private static JedisPool getJedisPool() {
		if (JedisPool == null) {
			// 第一步：创建一个JedisPool对象。需要指定服务端的ip及端口。

			// 1 获得连接池配置对象，设置配置项
			JedisPoolConfig pConfig = new JedisPoolConfig();
			// 1.1 最大连接数
			pConfig.setMaxTotal(100);

			// 1.2 最大空闲连接数
			pConfig.setMaxIdle(3);

			// 获取Jedis连接的最大等待时间（50秒）
			pConfig.setMaxWaitMillis(50 * 1000);
			// 在获取Jedis连接时，自动检验连接是否可用
			pConfig.setTestOnBorrow(true);
			// 在将连接放回池中前，自动检验连接是否有效
			pConfig.setTestOnReturn(true);
			// 自动测试池中的空闲连接是否都是可用连接
			pConfig.setTestWhileIdle(true);

			JedisPool = new JedisPool(pConfig, host, Integer.valueOf(port));

			// 第二步：从JedisPool中获得Jedis对象。
		}
		return JedisPool;
	}

	/**
	 * 对key对应的value 做+1操作 返回+1后的新值 保持原子性
	 * 
	 */
	public static Long incr(String key) {
		// 获取Jedis对象：
		Jedis jedis = getJedis();
		Long l = jedis.incr(key);
		jedis.close();
		return l;
	}

	/**
	 * 获取数据
	 * 
	 * @param key 缓存的key
	 * @return
	 */
	public static JsonResult<Object> get(String key) {
		ByteArrayInputStream is = null;
		ObjectInputStream in = null;
		Jedis jedis = null;
		try {
			// 从连接池获取Jedis对象：
			jedis = getJedis();
			if (jedis == null) {
				return JsonResult.buildError("缓存初始化失败");
			}
			byte[] data = jedis.get(key.getBytes());
			if (data == null) {
				return JsonResult.buildError("查无数据");
			}
			is = new ByteArrayInputStream(data);
			in = new ObjectInputStream(is);
			Object obj = in.readObject();
			return JsonResult.buildSuccess("获取缓存成功", obj);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return JsonResult.buildError("查询缓存异常");
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if(is != null){
					is.close();
				}
				if(jedis != null){
					jedis.close();
				}
				
				
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				return JsonResult.buildError("关闭流异常");
			}
		}
	}

	/**
	 * 设置缓存
	 * 
	 * @param key     设置的key
	 * @param value   设置的值 object
	 * @param seconds 设置的时间 s为单位
	 * @return
	 */
	public static JsonResult<Object> set(String key, Object value, int seconds) {
		String str = null;
		ByteArrayOutputStream os = null;
		ObjectOutputStream out = null;
		Jedis jedis  = null;
		try {
			jedis = getJedis();
			os = new ByteArrayOutputStream();
			out = new ObjectOutputStream(os);
			out.writeObject(value);
			byte[] objByte = os.toByteArray();
			str = jedis.setex(key.getBytes(), seconds, objByte);
			if ("OK".equals(str)) {
				return JsonResult.buildSuccess("设置缓存成功");
			} else {
				return JsonResult.buildError("设置缓存失败");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return JsonResult.buildError("设置缓存异常");
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if(os != null){
					os.close();
				}
				if(jedis != null){
					jedis.close();
				}
				
			} catch (Exception e) {
				logger.error(e);
				e.printStackTrace();
				return JsonResult.buildError("关闭流异常");
			}
		}
	}

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 */
	public static JsonResult<Object> delete(String key) {
		Jedis jedis = null;
		try {
			// 获取Jedis对象：
			jedis = getJedis();
			if (jedis == null) {
				return JsonResult.buildError("初始化缓存失败");
			}
			if (1 == jedis.del(key)) {
				return JsonResult.buildSuccess("删除缓存成功");
			} else {
				return JsonResult.buildError("删除缓存失败");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return JsonResult.buildError("删除缓存异常");
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}

	/**
	 * 设置缓存
	 * 
	 * @param key     设置的key
	 * @param seconds 设置的时间 s为单位
	 * @return
	 */
	public static JsonResult<Object> setIncr(String key, int seconds) {
		String str = null;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			str = jedis.setex(key, seconds, incr(key).toString());
			if ("OK".equals(str)) {
				return JsonResult.buildSuccess("设置缓存成功");
			} else {
				return JsonResult.buildError("设置缓存失败");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			return JsonResult.buildError("设置缓存异常");
		}finally{
			if(jedis != null){
				jedis.close();
			}
		}
	}
	
	
	/**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(String lockKey, int expireTime) {
    	Jedis jedis = getJedis();
    	  //SET命令的参数 
        SetParams params = SetParams.setParams().nx().px(expireTime);
        String result = jedis.set(lockKey, lockKey,params);

        jedis.close();
        if ("OK".equals(result)) {
            return true;
        }
        return false;

    }
	
	/**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(String lockKey) {

    	Jedis jedis = getJedis();
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(lockKey));

        jedis.close();
        if (Long.valueOf(1).equals(result)) {
            return true;
        }
        return false;

    }
	

}
