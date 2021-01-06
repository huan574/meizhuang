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

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.meizhuang.entity.SysException;
import com.meizhuang.mapper.meizhuang.SysExceptionMapper;
import com.meizhuang.utils.support.NetworkUtils;

/**
 * 系统异常日志工具类
 */
@Repository
public class SysExceptionUtils {

	public static final String DEFAULT_SYSTEM = "lazy";

	private static ExecutorService executorService = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, //
			new LinkedBlockingQueue<Runnable>(), //
			new ThreadFactoryBuilder().setNameFormat("sys-exception-thread-pool-%d").build() //
	);

	private static SysExceptionUtils sysExceptionUtils;

	@Autowired
	private SysExceptionMapper sysExceptionMapper;

	@PostConstruct
	public void init() {
		sysExceptionUtils = this;
		sysExceptionUtils.sysExceptionMapper = this.sysExceptionMapper;
	}

	public static void addSysException(Exception e) {
		addSysException(DEFAULT_SYSTEM, e);
	}

	public static void addSysException(String useSystem, Exception e) {
		HttpServletRequest request = null;
		try {
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		} catch (Exception e2) {
		}
		String currentLocalIp = NetworkUtils.getIp();
		StringBuffer sb = new StringBuffer();
		if (request != null) { // 不是request请求时，通过另一种方式获取Ip
			sb.append("服务器IP(" + request.getLocalAddr()).append(")<br>");
		} else {
			sb.append("服务器IP(" + currentLocalIp).append(")<br>");
		}
		StackTraceElement[] messages = e.getStackTrace();
		sb.append("" + e.toString() + "<br>");
		int length = messages.length;
		for (int i = 0; i < length; i++) {
			sb.append("   " + messages[i].toString());
			sb.append("<br>");
		}
		String uri = "同步信息URL"; // 不是request请求时，URL使用默认设置
		if (request != null) {
			uri = request.getRequestURI();
			String ctx = request.getContextPath();
			uri = uri.substring(ctx.length());
		}
		// 加入到数据
		SysException sysException = new SysException.Builder()//
				.sysContents(sb.toString()) //
				.ipAddress(currentLocalIp) //
				.resourceUrl(uri) //
				.useSystem(useSystem) //
				.build();
		addSysException(sysException);
	}

	public static void addSysException(SysException log) {
		executorService.execute(new SysExceptionThread(log));
	}

	private static class SysExceptionThread implements Runnable {

		private SysException log;

		public SysExceptionThread(SysException log) {
			this.log = log;
		}

		@Override
		public void run() {
			sysExceptionUtils.sysExceptionMapper.insert(log);
		}
	}

}
