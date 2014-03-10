package com.yatrix.activity.apps.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.yatrix.activity.batch.config.EventPartitioningJobConfiguration;
import com.yatrix.activity.store.config.MongoConfig;



@Configuration 
@ComponentScan(basePackages = {"com.yatrix.activity"}, includeFilters={@ComponentScan.Filter(value=Service.class, type=FilterType.ANNOTATION)})
@Import({MongoConfig.class, CacheConfig.class, EventPartitioningJobConfiguration.class})
@PropertySource("classpath:spring.properties")
public class ApplicationContext {
}
	