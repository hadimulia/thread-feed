package com.app.feed.core.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name="rss_feed_domain")
@Entity
public class RssFeedDomain {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String domainName;
	
	@OneToMany(mappedBy = "rssFeedDomain", cascade = CascadeType.ALL)
	private List<RssFeedContent> rssFeedContent;
	
	
	public RssFeedDomain() {
		super();
	}

	public RssFeedDomain(String domainName) {
		super();
		this.domainName = domainName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public List<RssFeedContent> getRssFeedContent() {
		return rssFeedContent;
	}

	public void setRssFeedContent(List<RssFeedContent> rssFeedContent) {
		this.rssFeedContent = rssFeedContent;
	}

}