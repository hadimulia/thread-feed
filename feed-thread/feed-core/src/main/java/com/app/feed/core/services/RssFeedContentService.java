package com.app.feed.core.services;

import com.app.feed.core.entity.RssFeedContent;
import com.app.feed.core.entity.RssFeedDomain;

/**
 * service for stream data
 *
 * @author taufuk.muliahadi (&copy;Jul 13, 2019) 
 */
public interface RssFeedContentService {

	/**
	 * get content rss
	 *
	 * @author taufuk.muliahadi (&copy;Jul 13, 2019)
	 * @param domain
	 * @return
	 */
	public RssFeedContent streamRss(RssFeedDomain domain);
}
