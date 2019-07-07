package com.app.thread.lib.util;

import java.util.List;
import java.util.Map;

public class UtilString {

	public static String build(Object... values) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			Object obj = values[i];
			builder.append(obj != null ? obj : "");
		}
		return builder.toString();
	}
	
	public static String toString(List<?> values) {
		StringBuilder res = new StringBuilder();
		if (values != null && !values.isEmpty()) {
			for (int i = 0; i < values.size(); i++) {
				Object objValue = values.get(i);
				String value = "";
				if (objValue instanceof List<?>) {
					value = toString((List<?>) objValue);
				} else if (objValue instanceof Map<?, ?>) {
					value = toString((Map<?, ?>) objValue);
				} else {
					value = String.valueOf(objValue);
				}
				res.append(build(value, ", "));
			}
		}
		return res.toString();
	}
	
	public static String toString(Map<?, ?> values) {
		StringBuilder res = new StringBuilder();
		if (values != null && !values.isEmpty()) {
			for (Map.Entry<?, ?> entry : values.entrySet()) {
				Object objValue = entry.getValue();
				String value = "";
				if (objValue instanceof List<?>) {
					value = toString((List<?>) objValue);
				} else if (objValue instanceof Map<?, ?>) {
					value = toString((Map<?, ?>) objValue);
				} else {
					value = String.valueOf(objValue);
				}
				res.append(build(keyValue(entry.getKey(), value), ", "));
			}
		}
		return res.toString();
	}
	
	public static String keyValue(Object key, Object value) {
		return build(key, "=", value);
	}
}
