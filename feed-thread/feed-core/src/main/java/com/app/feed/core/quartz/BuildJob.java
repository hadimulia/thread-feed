package com.app.feed.core.quartz;

import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

import com.app.feed.core.entity.RssFeedDomain;

/**
 *
 * @author taufuk.muliahadi (&copy;Jul 13, 2019) 
 */
@Component
public class BuildJob {

	 /**
	 * create Job Detail
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 * @param domain
	 * @return
	 */
	public JobDetail buildJobDetail(RssFeedDomain domain) {
		 JobDataMap jobDataMap = new JobDataMap();
		 jobDataMap.put("domain", domain);
		 return JobBuilder.newJob(RssJob.class)
				 .withIdentity(UUID.randomUUID().toString().concat("-".concat(domain.getDomainName())))
				 .usingJobData(jobDataMap)
				 .storeDurably()
				 .build();
	 }
	 
	
	 /**
	 * create triger every 15 minute
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 * @param jobDetail
	 * @return
	 */
	public Trigger buildJobTriger(JobDetail jobDetail) {
		 return TriggerBuilder.newTrigger()
				 .forJob(jobDetail)
				 .withIdentity(jobDetail.getKey().getName())
				 .startNow()
				 .withSchedule(CronScheduleBuilder.cronSchedule("0 0/15 * * * ?"))
				 .build();
	 }
}
