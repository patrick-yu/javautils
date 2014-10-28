package org.patrickyu.util;


public class DateUtils {

	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}

	public static long currentTime() {
		return System.currentTimeMillis() / 1000;
	}

	public static long daysToMicros(int days) {
		return (long)1e6 * 60 * 60 * 24 * days;
	}

	public static long daysToSeconds(int days) {
		return 60 * 60 * 24 * days;
	}

	public static long hoursToMicros(int hours) {
		return (long)1e6 * 60 * 60 * hours;
	}

	public static long hoursToSeconds(int hours) {
		return 60 * 60 * hours;
	}

}
