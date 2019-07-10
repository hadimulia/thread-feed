package com.app.feed.core.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.app.feed.core.entity.RssFeedDomain;
import com.app.feed.core.services.RssFeedContentService;

@Component
public class RssJob extends QuartzJobBean{

	private static final Logger logger = LogManager.getLogger(RssJob.class);
	
	@Autowired
	private RssFeedContentService rssFeedContentService;
	
	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		logger.info("Executing Job with key {}", jobExecutionContext.getJobDetail().getKey());
		
		JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
		
		RssFeedDomain rssFeedDomain = (RssFeedDomain) jobDataMap.get("domain");
		rssFeedContentService.streamRss(rssFeedDomain);
		
	}

}
