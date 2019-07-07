package com.app.thread.lib.dto;

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
