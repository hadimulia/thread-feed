package com.app.feed.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.app.thread.lib.dto.BaseRssFeed;

@Entity
@Table(name="rss_feed_content")
public class RssFeedContent extends BaseRssFeed{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String domain;
	
	@Column
	@Type(type="text")
	private String content;
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "rss_feed_domain_id")
	private RssFeedDomain rssFeedDomain;
	
	@Column
	private Date createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "RssFeedContent [id=" + id + ", domain=" + domain + ", content=" + content + ", createdTime=" + createdTime + "]";
	}
	
	
}
