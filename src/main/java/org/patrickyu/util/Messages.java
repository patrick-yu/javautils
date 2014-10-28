package org.patrickyu.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.patrickyu.util.FileUtils;
import org.patrickyu.util.StringUtils;
import org.vertx.java.core.json.JsonObject;

public class Messages {
	private Map<String, JsonObject> messages = new HashMap<String, JsonObject>();
	private String defaultLanguage = null;

	public void loadMessages(String defaultLanguage, String dir) {
		this.setDefaultLanguage(defaultLanguage);
		File directory = new File(dir);
		File[] files = directory.listFiles();
		for (File file: files) {
			if (!file.isFile())
				continue;
			String lang = FileUtils.getBaseName(file.getName());
			String ext = FileUtils.getExtension(file.getName());
			if (StringUtils.isEmpty(ext))
				continue;
			if (!StringUtils.equals(ext.toLowerCase(), "json"))
				continue;
			addMessages(lang, file);
		}
	}

	public Set<String> getLanguages() {
		return messages.keySet();
	}
	public void addMessages(String lang, File file) {
		String jsonString = FileUtils.readFileToString(file);
		JsonObject json = new JsonObject(jsonString);
		messages.put(lang, json);
	}

	public void removeMessages(String lang) {
		messages.remove(lang);
	}

	public void clear() {
		messages.clear();
	}

	public String getMessage(String lang, String key, String...args) {
		String msg = "";
		if (StringUtils.isEmpty(key))
			return "";
		String[] keys = key.split("\\.");
		JsonObject json = messages.get(lang);
		if (json == null) {
			json = messages.get(defaultLanguage);
			if (json == null)
				return "";
		}
		for (int i = 0; i < keys.length; i++ ) {
			if (i == keys.length - 1) {
				msg = json.getString(keys[i]);
				break;
			}
			json = json.getObject(keys[i]);
		}
		if (msg.length() == 0)
			return "";

		for (int i = 0; i < args.length; i++) {
			msg = msg.replaceAll(String.format("\\{%d\\}", i), args[i]);
		}

		return msg;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}
}
