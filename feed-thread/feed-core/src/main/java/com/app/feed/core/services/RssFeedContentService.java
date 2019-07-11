package com.app.feed.core.services;

import com.app.feed.core.entity.RssFeedContent;
import com.app.feed.core.entity.RssFeedDomain;

public interface RssFeedContentService {

	public RssFeedContent streamRss(RssFeedDomain domain);
}
