package com.app.feed.core.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.app.feed.core.constant.ThreadConstant;
import com.app.feed.core.entity.RssFeedDomain;
import com.app.feed.core.services.RssFeedContentService;
import com.app.thread.lib.dto.BaseRssFeed;
import com.app.thread.lib.storage.IMemoryQ;
import com.app.thread.lib.storage.impl.MemoryQFactory;

@Component
public class RssJob extends QuartzJobBean{

	private static final Logger logger = LogManager.getLogger(RssJob.class);
	
	private final MemoryQFactory<RssFeedDomain> factory = new MemoryQFactory<RssFeedDomain>();
	private final IMemoryQ<RssFeedDomain> mq = factory.getMemQ(ThreadConstant.INSTANCE_ID, ThreadConstant.QUE_ID);
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());
		
		JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
		
		RssFeedDomain rssFeedDomain = (RssFeedDomain) jobDataMap.get("domain");
		mq.pushNotify(rssFeedDomain);
		
	}

}
