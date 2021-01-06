package com.meizhuang.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.authority.app.session.AdminSession;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.meizhuang.entity.SystemLog;
import com.meizhuang.entity.enums.SystemOperationType;
import com.meizhuang.mapper.meizhuang.SystemLogMapper;
import com.meizhuang.utils.support.NetworkUtils;

/**
 * 管理员日志工具类
 */
@Repository
public class SystemLogUtils {

	private static ExecutorService executorService = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, //
			new LinkedBlockingQueue<Runnable>(), //
			new ThreadFactoryBuilder().setNameFormat("system-log-thread-pool-%d").build() //
	);

	private static SystemLogUtils userOperationUtils;

	@Autowired
	private SystemLogMapper systemLogMapper;

	@PostConstruct
	public void init() {
		userOperationUtils = this;
		userOperationUtils.systemLogMapper = this.systemLogMapper;
	}

	public static void addLog(String content, SystemOperationType systemOperationType) {
		SystemLog log = new SystemLog();
		log.setOperationType(systemOperationType.getValue());
		log.setContent(content);
		log.setMethod("");
		log.setParams("");
		log.setReturnValue("");
		log.setRunTime(0L);
		log.setIp(NetworkUtils.getIp());
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			log.setUrl(request.getRequestURI());
			Object object = request.getSession().getAttribute(AdminSession.SYSTEMUSER_SYSTEM_SESSIONID);
			if (object != null) {
				AdminSession adminSession = (AdminSession) object;
				log.setCman(adminSession.getUsername());
			} else {
				log.setCman("未登录");
			}
		} catch (Exception e) {
			log.setUrl("");
			log.setCman("未登录");
		}
		addLog(log);
	}

	public static void addLog(SystemLog log) {
		executorService.execute(new SystemLogThread(log));
	}

	private static class SystemLogThread implements Runnable {

		private SystemLog log;

		public SystemLogThread(SystemLog log) {
			this.log = log;
		}

		@Override
		public void run() {
			userOperationUtils.systemLogMapper.insert(log);
		}
	}

}
