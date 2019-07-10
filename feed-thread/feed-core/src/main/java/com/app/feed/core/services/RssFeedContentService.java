package com.app.feed.core.services;

import com.app.feed.core.entity.RssFeedDomain;

public interface RssFeedContentService {

	void streamRss(RssFeedDomain domain);
}
