package com.app.feed.core.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.feed.core.constant.ThreadConstant;

@Component
public class ThreadBuilder {

	static Integer threadNumber = 0;
	@Autowired
	private TrheadProcess trheadProcess;
	
	@PostConstruct
	public void createThread() {
		
		ExecutorService executor = Executors.newFixedThreadPool(ThreadConstant.TRHEAD_POOL,new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				
				return new Thread(r, "rss-feed-thread-"+threadNumber++);
			}
		});
		
		for (int i = 0; i < ThreadConstant.TRHEAD_POOL; i++) {
			Runnable worker = trheadProcess;
			executor.execute(worker);
		}
	}
}
