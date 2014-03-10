package com.yatrix.activity.hystrix.command.impl;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.ext.domain.yelp.PlaceDetails;
import com.yatrix.activity.ext.service.IYelpPlacesService;
import com.yatrix.activity.hystrix.command.IYelpCommand;


import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
public class YelpCommand extends HystrixCommand<PlaceDetails> implements IYelpCommand{

	private static final Logger logger = LoggerFactory.getLogger(YelpCommand.class);

	@Autowired
	private IYelpPlacesService yelpService;
	String term;
	String location;
	
	public YelpCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("YelpCommand"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("YelpThread"))
				.andCommandPropertiesDefaults(
						// we default to a one minute timeout for primary
						HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		logger.info("Inside YelpCommand.");
	}

	@Override
	protected PlaceDetails run() throws Exception {

		return yelpService.loadPlaceDetails(term, location);
	}

	@Override
	public Future<PlaceDetails> loadPlaceDetails(String pTerm, String pLocation) {
		term = pTerm;
		location = pLocation;
		return this.queue();
	}

	

	@Override
	public Future<PlaceDetails> loadPlaceDetails(String Object) {
		throw new UnsupportedOperationException("Service yet to be build");
	}

}
