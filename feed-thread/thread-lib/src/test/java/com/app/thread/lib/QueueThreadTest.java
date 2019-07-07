package com.app.thread.lib;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.app.thread.lib.storage.IMemoryQ;
import com.app.thread.lib.storage.impl.MemoryQFactory;
import com.app.thread.lib.util.UtilThread;

public class QueueThreadTest {

	private static final Logger logger = LogManager.getLogger(QueueThreadTest.class);
	private static final String INSTANCE_ID = QueueThreadTest.class.getName();
	private static final String QID = String.valueOf(System.currentTimeMillis());
	
	@Test
	public void testThread() {
		try {
			logger.info("start...");
			consumer();
			producer();
		} catch (Exception e) {
			logger.error("Error...!", e);
		} finally {
			logger.info("end...");
			UtilThread.doSleep(3000);
		}
	}

	
	private static void consumer() {
		new Consumer().start();
	}

	private static void producer() {
		new Producer(10).start();
	}
	
	private static class Consumer extends Thread {
		private final MemoryQFactory<Object> factory = new MemoryQFactory<Object>();
		private final IMemoryQ<Object> mq = factory.getMemQ(INSTANCE_ID, QID);
		
		@Override
		public void run() {
			while (true) {
				Object obj = mq.waitPull();
				logger.error("pull=" + String.valueOf(obj));
				UtilThread.doSleep(100);
			}
		}
	}
	
	private static class Producer extends Thread {
		private final MemoryQFactory<Object> factory = new MemoryQFactory<Object>();
		private final IMemoryQ<Object> mq = factory.getMemQ(INSTANCE_ID, QID);
		private int dataCount = 0;
		
		public Producer(int dataCount) {
			this.dataCount = dataCount;
		}
		
		@Override
		public void run() {
			for (int i = 0; i < dataCount; i++) {
				String data = "test data #" + i;
				logger.error("push=" + data);
				mq.pushNotify(data);
				UtilThread.doSleep(100);
			}
		}
		
	}
}
