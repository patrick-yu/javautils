package org.patrickyu.util;


public class ArrayUtils {
	public static boolean contains(Object[] array, Object objectToFind ) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, objectToFind);
	}

	public static boolean contains(boolean[] array, boolean valueToFind ) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	public static boolean contains(byte[] array, byte valueToFind ) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	public static boolean contains(char[] array, char valueToFind ) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	public static boolean contains(double[] array, double valueToFind ) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	public static boolean contains(float[] array, float valueToFind ) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	public static boolean contains(int[] array, int valueToFind ) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	public static boolean contains(long[] array, long valueToFind ) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	public static boolean contains(short[] array, short valueToFind ) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	public static String join(Object[] array, char separator) {
		return org.apache.commons.lang3.StringUtils.join(array, separator);
	}
	public static String join(Object[] array, String separator) {
		return org.apache.commons.lang3.StringUtils.join(array, separator);
	}

	public static <T> T[] add(final T[] array, final T element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}
}

