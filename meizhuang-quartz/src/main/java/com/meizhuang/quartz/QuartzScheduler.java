package com.meizhuang.quartz;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import com.meizhuang.config.properties.PropertiesUtils;
import com.meizhuang.utils.SysExceptionUtils;

public class QuartzScheduler {

	private static QuartzScheduler instance = new QuartzScheduler();

	private static final String GROUP_NAME = "flowManage";

	private static Scheduler scheduler = null;

	private QuartzScheduler() {

	}

	public static QuartzScheduler getScheduler() {
		try {
			if (scheduler == null) {
				synchronized (Scheduler.class) {
					if (scheduler == null) {
						// 初始化配置文件
						String profiles = PropertiesUtils.getProperty("spring.profiles.active");
						scheduler = new StdSchedulerFactory("quartz-" + profiles + ".properties").getScheduler();
					}
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			SysExceptionUtils.addSysException(e);
		}
		return instance;
	}

	public void start() {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
			SysExceptionUtils.addSysException(e);
		}
	}

	/**
	 * 添加指定时间执行任务
	 * 
	 * @param job_name
	 * @param triggerStartTime
	 * @return
	 */
	public String scheduleJob(Class<? extends Job> jobClass, Date triggerStartTime) {
		return scheduleJob(jobClass, null, triggerStartTime);
	}

	/**
	 * 添加指定时间执行任务
	 * 
	 * @param jobClass
	 * @param map
	 * @param triggerStartTime
	 * @return
	 */
	public String scheduleJob(Class<? extends Job> jobClass, Map<?, ?> map, Date triggerStartTime) {
		try {
			JobDataMap jobDataMap = null;
			if (map != null && map.size() > 0) {
				jobDataMap = new JobDataMap(map);
			}
			String name = jobClass.getSimpleName() + "_" + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
			JobBuilder jobBuilder = JobBuilder.newJob(jobClass).withIdentity(name + "_Job", GROUP_NAME);
			TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(name + "_Trg", GROUP_NAME).startAt(triggerStartTime);
			if (jobDataMap != null) {
				jobBuilder.setJobData(jobDataMap);
				triggerBuilder.usingJobData(jobDataMap);
			}
			JobDetail job = jobBuilder.build();
			Trigger trigger = triggerBuilder.build();
			scheduler.scheduleJob(job, trigger);
			return name + "_Job";
		} catch (SchedulerException e) {
			e.printStackTrace();
			SysExceptionUtils.addSysException(e);
		}
		return null;
	}

	/**
	 * 添加任务
	 * 
	 * @param jobClass
	 * @param cronExpression
	 */
	public Date scheduleJob(Class<? extends Job> jobClass, String cronExpression) {
		JobDetail job = JobBuilder.newJob(jobClass).withIdentity(jobClass.getSimpleName() + "_Job", GROUP_NAME).build();
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClass.getSimpleName() + "_Trg", GROUP_NAME).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
		return scheduleJob(job, trigger);
	}

	/**
	 * 注册任务
	 * 
	 * @param job
	 * @param trigger
	 * @return
	 * @throws SchedulerException
	 */
	private synchronized Date scheduleJob(JobDetail job, Trigger trigger) {
		try {
//			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(job.getKey().getGroup()))) {
//				if (jobKey.getName().equals(job.getKey().getName())) {
//					return null;
//				}
//			}
			scheduler.deleteJob(job.getKey());//
			return scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
			SysExceptionUtils.addSysException(e);
		}
		return null;
	}

	/**
	 * 启动任务
	 * 
	 * @param job_name
	 * @param job_group
	 * @return
	 */
	public boolean resumeJob(String job_name, String job_group) {
		try {
			JobKey jobKey = getJobKey(job_name, job_group);
			if (jobKey != null) {
				scheduler.resumeJob(jobKey);
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			SysExceptionUtils.addSysException(e);
		}
		return false;
	}

	/**
	 * 暂停任务
	 * 
	 * @param job_name
	 * @param job_group
	 * @return
	 */
	public boolean pauseJob(String job_name, String job_group) {
		try {
			JobKey jobKey = getJobKey(job_name, job_group);
			if (jobKey != null) {
				scheduler.pauseJob(jobKey);
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			SysExceptionUtils.addSysException(e);
		}
		return false;
	}

	/**
	 * 手动执行
	 * 
	 * @param job_name
	 * @param job_group
	 * @return
	 */
	public boolean triggerJob(String job_name, String job_group) {
		try {
			JobKey jobKey = getJobKey(job_name, job_group);
			if (jobKey != null) {
				scheduler.triggerJob(jobKey);
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			SysExceptionUtils.addSysException(e);
		}
		return false;
	}

	/**
	 * 删除任务
	 * 
	 * @param job_name
	 * @param job_group
	 * @return
	 */
	public boolean deleteJob(String job_name, String job_group) {
		try {
			JobKey jobKey = getJobKey(job_name, job_group);
			if (jobKey != null) {
				scheduler.deleteJob(jobKey);
			}
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
			SysExceptionUtils.addSysException(e);
		}
		return false;
	}

	private JobKey getJobKey(String job_name, String job_group) {
		try {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(job_group))) {
				if (jobKey.getName().equals(job_name)) {
					return jobKey;
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			SysExceptionUtils.addSysException(e);
		}
		return null;
	}

}
