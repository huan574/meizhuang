package com.meizhuang.utils;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.meizhuang.entity.SendSmsLog;
import com.meizhuang.entity.User;
import com.meizhuang.entity.enums.SystemOperationType;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.exception.BussinessException;
import com.meizhuang.mapper.meizhuang.SendSmsLogMapper;
import com.meizhuang.mapper.meizhuang.UserMapper;
import com.meizhuang.result.JsonResult;
import com.meizhuang.sms.SendSmsUtil;
import com.meizhuang.sms.result.SmsResult;
import com.meizhuang.utils.redis.RedisRSAUtil;
import com.meizhuang.utils.redis.RedisUtil;

import cn.hutool.core.date.DatePattern;

@Repository
public class SmsUtils {

	private static Logger log = LoggerFactory.getLogger(SmsUtils.class);
	
	@Value("${sms.send.state: false}")
	private boolean sendState;

	private final static String SMS_TEMPLATE = "您正在进行{0}操作，验证码{1}，工作人员不会向您索要，请勿向任何人泄露。";

	private final static String SMS_KEY = "SMS_LAZY_{0}_{1}";
	private final static String SMS_SEND_DATE_KEY = "SMS_LAZY_SEND_DATE_KEY_{0}_{1}";
	private final static String SMS_VALIDATE_NUM_KEY = "SMS_LAZY_VALIDATE_NUM_{0}_{1}";

	private final static int VERIFYCODE_EFFECTIVE_TIME = 600;

	private static SmsUtils smsUtils;

	@Autowired
	private SendSmsLogMapper sendSmsLogMapper;
	@Autowired
	private UserMapper userMapper;

	@PostConstruct
	public void init() {
		smsUtils = this;
		smsUtils.sendState = this.sendState;
		smsUtils.sendSmsLogMapper = this.sendSmsLogMapper;
		smsUtils.userMapper = this.userMapper;
	}

	/**
	 * 发送短信
	 */
	public static JsonResult<Object> sendSms(Integer uid, String businessType, String businessExplain) {
		User user = smsUtils.userMapper.selectById(uid);
		if (user == null || StringUtils.isEmpty(user.getCountryCode()) || StringUtils.isEmpty(user.getMobileNumber())) {
			return JsonResult.buildError(BizExceptionEnum.USER_NOT_EXISTED);
		}
		return sendSms(user.getCountryCode(), user.getMobileNumber(), businessType, businessExplain);
	}

	/**
	 * 发送短信
	 */
	public static JsonResult<Object> sendSms(String countryCode, String mobile, String businessType, String businessExplain) {
		SystemLogUtils.addLog("手机号" + mobile + "进行" + businessExplain + "操作发送短信", SystemOperationType.UNKNOWN);
		log.info("手机号" + mobile + "进行" + businessExplain + "操作发送短信");
		if (!NumberUtils.isNumber(countryCode)) {
			countryCode = "86";
		}
		JsonResult<Object> result = null;
		String key = MessageFormat.format(SMS_KEY, businessType, mobile);
		String numKey = MessageFormat.format(SMS_VALIDATE_NUM_KEY, businessType, mobile);
		String dateKey = MessageFormat.format(SMS_SEND_DATE_KEY, businessType, mobile);
		String verifyCode = null;

		result = RedisRSAUtil.get(key);
		if (result.isSuccess()) { // 存在缓存说明验证码未失效
			String verifyCodeTemp = result.getData().toString(); // 发送的验证码
			long differTime = 0;
			// 查询验证码的发送时间
			result = RedisRSAUtil.get(dateKey);
			if (result.isSuccess()) {
				String lastTime = result.getData().toString();// 上次的时间
				differTime = calLastedTime(lastTime);
				if (differTime < 60) {
					return JsonResult.buildError("验证码请求过于频繁，请稍后再试");
				}
			}

			// 查询验证失败次数缓存
			result = RedisRSAUtil.get(numKey);
			if (result.isSuccess()) {
				int num = Integer.parseInt(result.getData().toString());// 验证失败次数
				if (differTime < VERIFYCODE_EFFECTIVE_TIME && num == 0) { // 有效时间内，没有验证过从新发送的验证码用上一次生成的
					verifyCode = verifyCodeTemp;
				}
			}
		}else {
			log.info("手机号" + mobile+" result:"+result);
		}

		// 生成短信验证码
		if (StringUtils.isEmpty(verifyCode)) {
			verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
		}
		if (!smsUtils.sendState) {
			verifyCode = "123456";
		}

		boolean redisState = true;
		result = RedisRSAUtil.set(key, verifyCode, VERIFYCODE_EFFECTIVE_TIME); // 设置验证码缓存
		if (!result.isSuccess()) {
			log.info("手机号" + mobile+" result1:"+result);
			redisState = false;
		}
		result = RedisRSAUtil.set(numKey, "0", VERIFYCODE_EFFECTIVE_TIME); // 设置验证码验证失败次数，初始化为0，验证失败+1，超过3次验证码失效
		if (!result.isSuccess()) {
			log.info("手机号" + mobile+" result2:"+result);

			redisState = false;
		}
		result = RedisRSAUtil.set(dateKey, DatePattern.NORM_DATETIME_FORMAT.format(new Date()), VERIFYCODE_EFFECTIVE_TIME); // 设置验证码缓存
		if (!result.isSuccess()) {
			log.info("手机号" + mobile+" result3:"+result);

			redisState = false;
		}
		if (!redisState) { // 缓存设置失败
			return JsonResult.buildError("验证码发送失败");
		}

		// 根据短信模版获取短信内容
		String content = SmsUtils.getSmsContent(businessExplain, verifyCode);
		SmsResult smsResult = SendSmsUtil.sendSms(countryCode, mobile, content);
		if (!smsResult.isSuccess()) {
			return JsonResult.buildError(smsResult.getMessage());
		}
		smsUtils.sendSmsLogMapper.insert(new SendSmsLog.Builder().mobile("+" + countryCode + mobile).msg(content).build());
		// result = JsonResult.buildSuccess(smsResult.getMessage());
		result = JsonResult.buildSuccess("验证码已发送至" + mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
		return result;
	}

	private static String getSmsContent(String businessExplain, String verifyCode) {
		return MessageFormat.format(SMS_TEMPLATE, businessExplain, verifyCode);
	}

	/**
	 * 根据用户ID校验验证码
	 * 
	 * @param uid
	 * @param verifyCode
	 * @param businessType
	 * @return
	 */
	public static JsonResult<Object> checkSms(Integer uid, String verifyCode, String businessType) {
		User user = smsUtils.userMapper.selectById(uid);
		if (user == null || StringUtils.isEmpty(user.getMobileNumber())) {
			return JsonResult.buildError(BizExceptionEnum.USER_NOT_EXISTED);
		}
		return checkSms(user.getMobileNumber(), verifyCode, businessType);
	}

	/**
	 * 根据手机号校验验证码
	 * 
	 * @param mobile
	 * @param verifyCode
	 * @param businessType
	 * @return
	 */
	public static JsonResult<Object> checkSms(String mobile, String verifyCode, String businessType) {
		JsonResult<Object> result;
		String key = MessageFormat.format(SMS_KEY, businessType, mobile);
		String numKey = MessageFormat.format(SMS_VALIDATE_NUM_KEY, businessType, mobile);
		String dateKey = MessageFormat.format(SMS_SEND_DATE_KEY, businessType, mobile);

		int num = 0;
		result = RedisRSAUtil.get(numKey);
		if (result.isSuccess()) {
			num = Integer.parseInt(result.getData().toString());
			if (num >= 3) { // 验证次数超过3次，验证码失效
				return JsonResult.buildError("验证码已失效");
			}
		}

		result = RedisRSAUtil.get(key);
		if (!result.isSuccess()) { // 验证码已过期
			return JsonResult.buildError("验证码已过期");
		}
		Object code = result.getData();
		if (!verifyCode.equals(code)) { // 验证码未过期
			RedisRSAUtil.set(numKey, String.valueOf(++num), VERIFYCODE_EFFECTIVE_TIME); // 验证码错误，失败次数+1
			return JsonResult.buildError("验证码验证失败");
		}

		// 验证成功
		RedisUtil.delete(key);
		RedisUtil.delete(numKey);
		RedisUtil.delete(dateKey);
		return JsonResult.buildSuccess();
	}

	/**
	 * 比较时间相差多少秒数
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	private static long calLastedTime(String dateString) {
		try {
			Date startDate = DatePattern.NORM_DATETIME_FORMAT.parse(dateString);
			long a = new Date().getTime();
			long b = startDate.getTime();
			long c = (a - b) / 1000;
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BussinessException(BizExceptionEnum.SERVER_ERROR);
		}
	}

}
