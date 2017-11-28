package com.szw.easyquotation.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

	public final static long MINUTE = 60 * 1000;

	public static boolean isAfter(Date date1, Date date2) {
		return date1.after(date2);
	}

	public static boolean isBefore(Date date1, Date date2) {
		return date1.before(date2);
	}

	public static Date getTime(int hour, int minute, int second) {
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.set(Calendar.HOUR_OF_DAY, hour);
		now.set(Calendar.MINUTE, minute);
		now.set(Calendar.SECOND, second);

		return now.getTime();
	}

	public static int getHour(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.HOUR_OF_DAY);
	}

	public static int getMinute(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.MINUTE);
	}

	public static int getSecond(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.SECOND);
	}

	public static Date resetLastSeconds(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.SECOND, 59);
		now.set(Calendar.MILLISECOND, 0);
		return now.getTime();
	}

	public static Date resetZeroSeconds(Date date) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		return now.getTime();
	}

	public static String format_yyyyMMddHHmmss(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(date);

	}

	public static String format_yyyyMMdd(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return sdf.format(date);
	}

	public static long countMinutes(Date end, Date start) {

		long minute = (end.getTime() - start.getTime()) / MINUTE;

		return minute;
	}

	public static void main(String args[]) throws Exception {

		System.out.println(resetZeroSeconds(new Date()));
	}
}
