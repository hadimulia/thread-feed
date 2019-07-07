package com.app.thread.lib.util;

public class Validation {

	public static boolean hasValue(Object obj) {
		boolean res = false;
		if (obj != null) {
			if (obj instanceof String) {
				res = !((String) obj).isEmpty();
			} else {
				res = true;
			}
		}
		return res;
	}
}
