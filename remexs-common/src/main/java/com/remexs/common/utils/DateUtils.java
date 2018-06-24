package com.remexs.common.utils;

import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * 时间处理工具类
 * 
 * @author remexs
 *
 */
public class DateUtils {
	
	static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static Date getDate() {
		return new Date(System.currentTimeMillis());
	}
	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static String getDateStr() {
		Date date = new Date(System.currentTimeMillis());
		return FastDateFormat.getInstance(DATE_FORMAT).format(date);
	}

	/**
	 * 获得当前时间 指定格式
	 * 
	 * @param format
	 * @return
	 */
	public static String getDateStr(String format) {
		Date date = new Date(System.currentTimeMillis());
		return FastDateFormat.getInstance(format).format(date);
	}

	/**
	 * 格式化指定时间
	 * 
	 * @return
	 */
	public static String getDateStr(Date date) {
		return FastDateFormat.getInstance(DATE_FORMAT).format(date);
	}

	/**
	 * 格式化指定时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getDateStr(Date date, String format) {
		return FastDateFormat.getInstance(DATE_FORMAT).format(date);
	}

	/**
	 * 获得下一小时
	 * 
	 * @return
	 */
	public static String getNextHourStr() {
		Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
		return FastDateFormat.getInstance(DATE_FORMAT).format(date);
	}

	/**
	 * 获得下一小时
	 * 
	 * @param format
	 * @return
	 */
	public static String getNextHourStr(String format) {
		Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000);
		return FastDateFormat.getInstance(format).format(date);
	}
}
