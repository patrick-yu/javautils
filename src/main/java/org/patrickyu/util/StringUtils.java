package org.patrickyu.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.vertx.java.core.json.JsonArray;

public class StringUtils {

	public static String capitalize(String str) {
		return org.apache.commons.lang3.StringUtils.capitalize(str);
	}

	public static String hash256(String data) {
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

	public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

	public static String newUuid() {
		return UUID.randomUUID().toString();
	}

	public static String newUuidWithoutDash() {
		String uuid = newUuid();
		return org.apache.commons.lang3.StringUtils.remove(uuid, '-');
	}

	public static String getHostName(String urlString) {
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			return "";
		}
		return url.getHost();
	}

	public static String[] jsonArrayToStringArray(JsonArray jsonArray) {
		List<String> strList = new ArrayList<String>();
		for(int i = 0; i < jsonArray.size(); i++ )
			strList.add(jsonArray.get(i).toString());
		String[] arr = new String[jsonArray.size()];
		return strList.toArray(arr);
	}

	public static boolean isNotEmpty(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isNotEmpty(cs);
	}

	public static boolean isEmpty(CharSequence cs) {
		return org.apache.commons.lang3.StringUtils.isEmpty(cs);
	}

	public static boolean equals(CharSequence cs1, CharSequence cs2) {
		return org.apache.commons.lang3.StringUtils.equals(cs1, cs2);
	}

	public static boolean equalsIgnoreCase(CharSequence cs1, CharSequence cs2) {
		return org.apache.commons.lang3.StringUtils.equalsIgnoreCase(cs1, cs2);
	}

	public static boolean contains(CharSequence seq, CharSequence searchSeq) {
		return org.apache.commons.lang3.StringUtils.contains(seq, searchSeq);
	}

	public static boolean containsIgnoreCase(CharSequence seq, CharSequence searchSeq) {
		return org.apache.commons.lang3.StringUtils.containsIgnoreCase(seq, searchSeq);
	}

	public static String join(String[] array, String separator) {
		return org.apache.commons.lang3.StringUtils.join(array, separator);
	}

	public static String deleteWhitespace(String str) {
		return org.apache.commons.lang3.StringUtils.deleteWhitespace(str);
	}

	public static String digitOnly(CharSequence cs) {
		if (isEmpty(cs))
			return "";
		StringBuilder build = new StringBuilder();
		for ( int i = 0; i < cs.length(); i++ ) {
			char c = cs.charAt(i);
			if ( c >= '0' && c <= '9' )
				build.append(c);
		}
		return build.toString();
	}

// Base64 변환은 직접 하도록 한다.

//	public static String encodeBase64(String value) {
//		if (StringUtils.isEmpty(value))
//			return value;
//		try {
//			String v = Base64.encodeBase64String(value.getBytes("UTF-8"));
//			return v;
//		} catch (UnsupportedEncodingException e) {
//			return "";
//		}
//	}
//
//	public static String decodeBase64(String value) {
//		if (StringUtils.isEmpty(value))
//			return value;
//		byte[] bytes = Base64.decodeBase64(value);
//		return new String(bytes);
//	}


	public static void main(String[] args) {

	}
}
