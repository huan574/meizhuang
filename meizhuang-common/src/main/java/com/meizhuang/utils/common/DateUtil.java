package com.meizhuang.utils.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.format.FastDateFormat;

public class DateUtil {

	public static final FastDateFormat sdf = DatePattern.NORM_DATETIME_FORMAT;

	public static final FastDateFormat FORMAT = DatePattern.NORM_DATE_FORMAT;

	public static String getTimeInterval(Date date, String type) {
		String timeDate = null;
		String beginDate = "";
		String endDate = "";
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if ("day".equals(type)) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			beginDate = sdf.format(calendar.getTime());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			endDate = sdf.format(calendar.getTime());
			timeDate = beginDate + "," + endDate;
		} else if ("week".equals(type)) {
			// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
			int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
			if (1 == dayWeek) {
				calendar.add(Calendar.DAY_OF_MONTH, -1);
			}
			// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			// 获得当前日期是一个星期的第几天
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
			calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			beginDate = sdf.format(calendar.getTime());
			calendar.add(Calendar.DATE, 6);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			endDate = sdf.format(calendar.getTime());
			timeDate = beginDate + "," + endDate;
		} else if ("month".equals(type)) {
			calendar.add(Calendar.MONTH, 0);
			calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			beginDate = sdf.format(calendar.getTime());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			endDate = sdf.format(calendar.getTime());
			timeDate = beginDate + "," + endDate;
		} else {
			return null;
		}
		return timeDate;
	}

	public static String getTimeStrBySecond(long second) {
		if (second <= 0) {
			return "00:00";
		}
		StringBuilder sb = new StringBuilder();
		long hours = second / (60 * 60);
		if (hours > 0) {
			second -= hours * (60 * 60);
			sb.append((hours >= 10 ? (hours + "") : ("0" + hours)) + ":");
		}
		long minutes = second / 60;
		if (minutes > 0) {
			second -= minutes * 60;
		}
		sb.append((minutes >= 10 ? (minutes + "") : ("0" + minutes)) + ":" + (second >= 10 ? (second + "") : ("0" + second)));
		return sb.toString();
	}
	
	
	/**
	 * 两个时间分钟差
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public  static int getMinutesDiffTime(Date fromDate,Date toDate) {
		try {
			
			long from = fromDate.getTime();
			long to = toDate.getTime();
			int minutes = (int) ((to - from)/(1000 * 60));
			return minutes;
		}catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}
	
	
	/**
	 * 两个时间分钟差
	 * @param fromDate
	 * @param toDate
	 * @return
	 */
	public  static int getSecondDiffTime(Date fromDate,Date toDate) {
		try {
			long from = fromDate.getTime();
			long to = toDate.getTime();
			int minutes = (int) ((to - from)/1000 );
			return minutes;
		}catch (Exception e) {
			// TODO: handle exception
			return -1;
		}
	}
	
	public static void main(String[] args) {
		Date date1=new Date();
		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date date2=new Date();
		System.out.println(getSecondDiffTime(date1,date2));
		
		String effectiveTime="2019/06/19";
		Date effDate=new Date(effectiveTime);
		System.out.println(getSecondDiffTime(effDate,date2));
		
	}

}
