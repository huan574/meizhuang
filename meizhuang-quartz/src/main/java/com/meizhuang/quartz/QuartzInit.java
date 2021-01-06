package com.meizhuang.quartz;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.meizhuang.job.SystemJob;

/**
 * 项目启动完成初始化定时任务
 */
@Component
public class QuartzInit implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		SystemJob.run(); // 初始化定时任务
	}

}
