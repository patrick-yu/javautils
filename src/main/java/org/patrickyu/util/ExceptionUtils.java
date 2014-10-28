package org.patrickyu.util;


public class ExceptionUtils {
	public static String getStackTrace(Throwable e) {
		return org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace(e);
	}
}
