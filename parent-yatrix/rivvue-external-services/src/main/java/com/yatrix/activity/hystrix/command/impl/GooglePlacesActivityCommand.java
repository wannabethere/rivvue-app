package com.yatrix.activity.hystrix.command.impl;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.ext.domain.google.PlacesRequest;
import com.yatrix.activity.ext.domain.google.PlacesSearchResults;
import com.yatrix.activity.ext.service.IGooglePlacesService;
import com.yatrix.activity.hystrix.command.IGooglePlacesActivityCommand;


import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope( value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
public class GooglePlacesActivityCommand extends HystrixCommand<PlacesSearchResults> implements IGooglePlacesActivityCommand{

	private static final Logger logger = LoggerFactory.getLogger(GooglePlacesActivityCommand.class);

	@Autowired
	private IGooglePlacesService googlePlacesService;
	PlacesRequest placesRequest;

	public GooglePlacesActivityCommand() {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("GooglePlacesActivityCommand"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GooglePlacesActivityThread"))
				.andCommandPropertiesDefaults(
						// we default to a one minute timeout for primary
						HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		logger.info("Inside GooglePlacesActivityCommand.");
	}

	@Override
	protected PlacesSearchResults run() throws Exception {
		return googlePlacesService.findActivityPlace(placesRequest);
	}


	public Future<PlacesSearchResults> loadPlaceDetails(PlacesRequest pPlacesRequest) {
		placesRequest = pPlacesRequest;
		return this.queue();
	}

	@Override
	public Future<PlacesSearchResults> loadPlaceDetails(String term, String location) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not Implemented yet");
	}

}
