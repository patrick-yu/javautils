package org.patrickyu.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.vertx.java.core.json.JsonObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClassUtils {
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 *
	 * @param filePathFromClassPath "com/gmtsoft/util/StringUtils.class"
	 * @return
	 */
	public static URI getResourcePath(String filePathFromClassPath) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null)
			loader = ClassLoader.getSystemClassLoader();
		URL url = loader.getResource(filePathFromClassPath);
		return urlToUri(url);
	}

	public static URI urlToUri(URL url) {
		if (url == null)
			return null;
		try {
			return url.toURI();
		} catch (URISyntaxException e) {
			return null;
		}
	}

	public static String objectToJsonString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			return null;
		}
	}

	public static <T> T jsonStringToObject(String jsonString, Class<T> cls) {
		String tmp = StringUtils.deleteWhitespace(jsonString);
		if (StringUtils.isEmpty(jsonString) || tmp.length() < 4)
			return null;
		tmp = tmp.substring(1,tmp.length()-1);
		if (StringUtils.isEmpty(tmp))
			return null;
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			return mapper.readValue(jsonString, cls);
		} catch (IOException e) {
			return null;
		}
	}

	public static JsonObject objectToJsonObject(Object obj) {
		String jsonString = objectToJsonString(obj);
		if (jsonString == null)
			jsonString = "{}";
		return new JsonObject(jsonString);
	}

	public static <T> T jsonObjectToObject(JsonObject json, Class<T> cls) {
		if (json.getFieldNames().size() == 0)
			return null;
		String jsonStr = json.toString();
		return jsonStringToObject(jsonStr, cls);
	}

	public static <T> void copy(T target, T source) {
        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();
        for( Field field: sourceFields ) {
            Field sameField = findSameField(targetFields, field);
            if(sameField == null)
                continue;
            try {
                field.setAccessible(true);
                sameField.setAccessible(true);
                sameField.set(target, field.get(source));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

	public static Field findSameField(Field[] fields, Field field) {
		for (Field f: fields) {
			if (f.getName().equals(field.getName()))
				return f;
		}
		return null;
	}

}
