package com.app.thread.lib;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.app.thread.lib.dto.BaseRssFeed;
import com.app.thread.lib.storage.IMemoryQ;
import com.app.thread.lib.storage.impl.MemoryQFactory;
import com.app.thread.lib.util.UtilThread;

public class QueueThreadTest {

	private static final Logger logger = LogManager.getLogger(QueueThreadTest.class);
	private static final String INSTANCE_ID = QueueThreadTest.class.getName();
	private static final String QID = String.valueOf(System.currentTimeMillis());
	private static final List<String> listRss = new ArrayList<String>();
	
	@Before
	public void before() {
		listRss.add("http://www.bisnis.com/rss");
		listRss.add("http://banten.antaranews.com/rsrs/terkini.xml");
		listRss.add("http://www.bisnis.com/rss");
		listRss.add("http://www.jatam.org/feed");
	}
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
			UtilThread.doSleep(5000);
		}
	}

	
	private static void consumer() {
		new Consumer().start();
	}

	private static void producer() {
		new Producer().start();
	}
	
	private static class Consumer extends Thread {
		private final MemoryQFactory<BaseRssFeed> factory = new MemoryQFactory<BaseRssFeed>();
		private final IMemoryQ<BaseRssFeed> mq = factory.getMemQ(INSTANCE_ID, QID);
		
		@Override
		public void run() {
			while (true) {
				UtilThread.doSleep(500);
				BaseRssFeed obj = mq.waitPull();
				logger.error("pull=" + obj);
				UtilThread.doSleep(100);
				mq.rssThread().remove(obj.getLinkRssFed());
			}
		}
	}
	
	private static class Producer extends Thread {
		private final MemoryQFactory<BaseRssFeed> factory = new MemoryQFactory<BaseRssFeed>();
		private final IMemoryQ<BaseRssFeed> mq = factory.getMemQ(INSTANCE_ID, QID);
		
		@Override
		public void run() {
			for (String link : listRss) {
				BaseRssFeed rssFeed = new BaseRssFeed();
				rssFeed.setLinkRssFed(link);
				logger.error("push=" + rssFeed);
				mq.pushNotify(rssFeed);
				UtilThread.doSleep(100);
			}
		}
		
	}
}
