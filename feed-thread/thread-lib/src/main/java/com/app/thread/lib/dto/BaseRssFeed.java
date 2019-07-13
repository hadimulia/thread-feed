package com.app.thread.lib.dto;

/**
 *
 * @author taufuk.muliahadi (&copy;Jul 13, 2019) 
 */
public class BaseRssFeed {

	private String linkRssFed;

	public String getLinkRssFed() {
		return linkRssFed;
	}

	public void setLinkRssFed(String linkRssFed) {
		this.linkRssFed = linkRssFed;
	}

	@Override
	public String toString() {
		return "BaseRssFeed [linkRssFed=" + linkRssFed + "]";
	}
	
	
}
