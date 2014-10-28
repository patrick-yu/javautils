package org.patrickyu.util;

import java.util.HashMap;
import java.util.Map;


public class ClassContainer<T> {
	private Map<String, T> instances = new HashMap<String, T>();

	public <D extends T> D load(Class<D> cls) {
		String name = cls.getCanonicalName();
		try {
			@SuppressWarnings("unchecked")
			D obj = (D)instances.get(name);
			if (obj == null) {
				obj = cls.newInstance();
				instances.put(name, obj);
			}
			return obj;
		} catch (Exception e) {
			return null;
		}
	}
}
