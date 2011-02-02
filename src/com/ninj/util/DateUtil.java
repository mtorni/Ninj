package com.ninj.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

	public static String parseDate(Date date, String format) {
		String ret = "";
		SimpleDateFormat f = new SimpleDateFormat("MMMM dd");
		try {
			f = new SimpleDateFormat(format);
		} catch (Exception e) {
			System.out.println("invalid format using default");
			// eat
		}
		ret = f.format(date);
		return ret;
	}

	public static Date parseDate(String date, String format) throws Exception {
		Date d = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
			f = new SimpleDateFormat(format);
		} catch (Exception e) {
			System.out.println("invalid format using default");
			// eat
		}
		d = f.parse(date);

		return d;
	}

	public static String millisecondsToString(long t) {
		int milliseconds = (int) (t % 1000);
		int seconds = (int) ((t / 1000) % 60);
		int minutes = (int) ((t / 60000) % 60);
		int hours = (int) ((t / 3600000) % 24);
		String millisecondsStr = (milliseconds < 10 ? "00"
				: (milliseconds < 100 ? "0" : ""))
				+ milliseconds;
		String secondsStr = (seconds < 10 ? "0" : "") + seconds;
		String minutesStr = (minutes < 10 ? "0" : "") + minutes;
		String hoursStr = (hours < 10 ? "0" : "") + hours;
		return new String(hoursStr + ":" + minutesStr + ":" + secondsStr + "."
				+ millisecondsStr);

	}
}
