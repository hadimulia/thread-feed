package com.app.feed.core.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.feed.core.entity.RssFeedContent;

@Repository
public interface RssFeedContentDao extends CrudRepository<RssFeedContent, Long> {

}
