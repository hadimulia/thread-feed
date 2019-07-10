package com.app.feed.core;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import com.app.feed.core.rss.GetFeedRss;

public class FeedCoreApplicationTests {

	private GetFeedRss getFeedRss = new GetFeedRss();
	private Map<String, String> header = new HashMap<String,String>();
	
	@Test
	public void contextLoads() {
		header.put("Accept", "text/plain");
		getFeedRss.getReponseFromURL("http://www.bisnis.com/rss", HttpMethod.GET, header);
		
	}

}
