package com.yatrix.activity.service.commands;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.store.mongo.domain.UserEvent;


public abstract class ExecuteFacebookRestServices extends HystrixCommand<UserEvent> {

	private final String accessToken;
	public ExecuteFacebookRestServices(String accessToken ) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
			      .andCommandKey(HystrixCommandKey.Factory.asKey("UpdateEvents"))
			      .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FacebookThread"))
			      .andCommandPropertiesDefaults(
			      // we default to a one minute timeout for primary
			        HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		this.accessToken=accessToken;
	}
}
