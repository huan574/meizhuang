package com.meizhuang.job;

import com.meizhuang.quartz.QuartzScheduler;

public class SystemJob {

	private static QuartzScheduler scheduler = null;

	private SystemJob() {

	}

	public static void run() {
		scheduler = QuartzScheduler.getScheduler();
		//凌晨执行定时器  会员升级改成自动触发
		//scheduler.scheduleJob(LingDianJob.class, "5 0 0 * * ?");// 凌晨执行一次5 0 0 * * ?
		
		
		scheduler.start();
	}

}
