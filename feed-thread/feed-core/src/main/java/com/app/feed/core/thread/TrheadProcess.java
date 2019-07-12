package com.app.feed.core.thread;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.feed.core.constant.ThreadConstant;
import com.app.feed.core.entity.RssFeedContent;
import com.app.feed.core.entity.RssFeedDomain;
import com.app.feed.core.services.RssFeedContentService;
import com.app.thread.lib.storage.IMemoryQ;
import com.app.thread.lib.storage.impl.MemoryQFactory;
import com.app.thread.lib.util.UtilThread;

@Component
public class TrheadProcess implements Runnable{
	
	private static final Logger logger = LogManager.getLogger(TrheadProcess.class);
	private final MemoryQFactory<RssFeedDomain> factory = new MemoryQFactory<RssFeedDomain>();
	private final IMemoryQ<RssFeedDomain> mq = factory.getMemQ(ThreadConstant.INSTANCE_ID, ThreadConstant.QUE_ID);
	
	@Autowired
	private RssFeedContentService rssFeedContentService;
	
	
	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		while (true) {
			
			RssFeedDomain rssFeedDomain = mq.waitPull();
			logger.info("["+threadName+"] Get Data Domain : "+rssFeedDomain);
			logger.info("["+threadName+"] start process get RSS ");
			RssFeedContent rssFeedContent = rssFeedContentService.streamRss(rssFeedDomain);
			mq.rssThread().remove(rssFeedContent.getLinkRssFed());
			logger.info("["+threadName+"] end process get RSS ");
			UtilThread.doSleep(100);
		}
		
	}

}
