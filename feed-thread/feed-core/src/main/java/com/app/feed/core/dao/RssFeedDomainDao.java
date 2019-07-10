package com.app.feed.core.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.feed.core.entity.RssFeedDomain;

@Repository
public interface RssFeedDomainDao extends CrudRepository<RssFeedDomain, Long>{

}
