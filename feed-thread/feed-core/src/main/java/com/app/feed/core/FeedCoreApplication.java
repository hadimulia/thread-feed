package com.app.feed.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.app.feed.core")
@EntityScan(basePackages="com.app.feed.core.entity")
@EnableTransactionManagement
public class FeedCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeedCoreApplication.class, args);
	}

}
