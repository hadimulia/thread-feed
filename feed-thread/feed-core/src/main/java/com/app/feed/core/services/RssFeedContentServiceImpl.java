package com.app.feed.core.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.app.feed.core.dao.RssFeedContentDao;
import com.app.feed.core.entity.RssFeedContent;
import com.app.feed.core.entity.RssFeedDomain;
import com.app.feed.core.rss.GetFeedRss;

@Service
public class RssFeedContentServiceImpl implements RssFeedContentService {

	private static final Logger logger = LogManager.getLogger(GetFeedRss.class);
	
	@Autowired
	private GetFeedRss getFeedRss;
	
	@Autowired
	private RssFeedContentDao contentDao;
	

	@Override
	public RssFeedContent streamRss(RssFeedDomain domain) {
		logger.info("prepate data ");
		Map<String, String> header = new HashMap<>();
		header.put("Accept", "text/plain");
		
		String content = getFeedRss.getReponseFromURL(domain.getDomainName(), HttpMethod.GET, header);
		
		RssFeedContent rssFeedContent = new RssFeedContent();
		rssFeedContent.setDomain(domain.getDomainName());
		rssFeedContent.setCreatedTime(new Date());
		rssFeedContent.setLinkRssFed(domain.getDomainName());
		rssFeedContent.setContent(content);
		rssFeedContent.setRssFeedDomain(domain);
		
		contentDao.save(rssFeedContent);
		
		logger.info("content : ".concat(rssFeedContent.toString()));
		
		return rssFeedContent;
	}
	
}
