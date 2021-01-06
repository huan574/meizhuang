package com.meizhuang.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.meizhuang.entity.OperationRecord;
import com.meizhuang.entity.enums.OperationTypeEnum;
import com.meizhuang.mapper.meizhuang.OperationRecordMapper;
import com.meizhuang.utils.support.NetworkUtils;

/**
 * 用户操作日志工具类
 */
@Repository
public class UserOperationUtils {

	private static ExecutorService executorService = new ThreadPoolExecutor(5, 200, 0L, TimeUnit.MILLISECONDS, //
			new LinkedBlockingQueue<Runnable>(), //
			new ThreadFactoryBuilder().setNameFormat("operation-record-thread-pool-%d").build() //
	);

	private static UserOperationUtils userOperationUtils;

	@Autowired
	private OperationRecordMapper operationRecordMapper;

	@PostConstruct
	public void init() {
		userOperationUtils = this;
		userOperationUtils.operationRecordMapper = this.operationRecordMapper;
	}

	public static void addLog(Integer uid, OperationTypeEnum operationType) {
		addLog(uid, operationType.getDesc(), operationType);
	}

	public static void addLog(Integer uid, String logContent, OperationTypeEnum operationType) {
		String ip = NetworkUtils.getIp();
		OperationRecord log = new OperationRecord.Builder() //
				.uid(uid)//
				.type(operationType.getType())//
				.content(logContent) //
				.ip(ip)//
				.location("---")//
				.build();
		addLog(log);
	}

	public static void addLog(OperationRecord log) {
		executorService.execute(new OperationRecordThread(log));
	}

	private static class OperationRecordThread implements Runnable {

		private OperationRecord log;

		public OperationRecordThread(OperationRecord log) {
			this.log = log;
		}

		@Override
		public void run() {
			userOperationUtils.operationRecordMapper.insert(log);
		}
	}

}
