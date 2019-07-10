package com.app.feed.core;

import java.util.Date;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.app.feed.core.dao.RssFeedDomainDao;
import com.app.feed.core.entity.RssFeedDomain;
import com.app.feed.core.quartz.BuildJob;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.app.feed.core")
@EntityScan(basePackages = "com.app.feed.core.entity")
@EnableTransactionManagement
@EnableAutoConfiguration
public class FeedCoreApplication implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(FeedCoreApplication.class);

	@Autowired
	private RssFeedDomainDao rssFeedDomainDao;
	
	@Autowired
	private BuildJob buildJob;
	
	@Autowired
	private Scheduler scheduler;

	public static void main(String[] args) {
		SpringApplication.run(FeedCoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Start Application");
		if (rssFeedDomainDao.count() == 0) {
			rssFeedDomainDao.save(new RssFeedDomain("https://www.kapanlagi.com/feed"));
			rssFeedDomainDao.save(new RssFeedDomain("http://www.bisnis.com/rss/index?c=186"));
			rssFeedDomainDao.save(new RssFeedDomain("http://www.bisnis.com/rss"));
			rssFeedDomainDao.save(new RssFeedDomain("http://banten.antaranews.com/rsrs/terkini.xml"));
			rssFeedDomainDao.save(new RssFeedDomain("http://jakartagreater.com/feed/?alt=rss"));
			rssFeedDomainDao.save(new RssFeedDomain("http://www.cahayapapua.com/feed"));
			rssFeedDomainDao.save(new RssFeedDomain("http://www.jatam.org/feed"));
			rssFeedDomainDao.save(new RssFeedDomain("http://108jakarta.com/RSS/index/"));
		logger.info(new Date());
		}
		
		Iterator<RssFeedDomain> iterator = rssFeedDomainDao.findAll().iterator();
		logger.info("Register Jobs");
		while(iterator.hasNext()) {
			RssFeedDomain domain = iterator.next();
			JobDetail jobDetail = buildJob.buildJobDetail(domain);
			Trigger triger = buildJob.buildJobTriger(jobDetail);
			scheduler.scheduleJob(jobDetail,triger);
			scheduler.start();
			logger.info("Job for ".concat(domain.getDomainName()).concat(" Registred"));
		}

	}

}
