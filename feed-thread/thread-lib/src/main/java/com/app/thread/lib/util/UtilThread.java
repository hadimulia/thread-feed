package com.app.thread.lib.util;

public class UtilThread {

	public static void doSleep(long sleepMillis) {
		try {
			Thread.sleep(sleepMillis);
		} catch (Exception e) {
			// silent
		}
	}
}
