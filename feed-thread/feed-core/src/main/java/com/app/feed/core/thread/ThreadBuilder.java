package com.app.feed.core.thread;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThreadBuilder {

	
	@Autowired
	private TrheadProcess trheadProcess;
	
	@PostConstruct
	public void createThread() {
		
	}
}
