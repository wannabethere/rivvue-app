package com.yatrix.activity.hystrix.fb.command.impl;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.util.StringUtils;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.hystrix.fb.command.HystrixSocialResult;

import com.yatrix.activity.hystrix.fb.command.impl.FacebookEventCreateCommand;
import com.yatrix.activity.service.facebook.FacebookEventService;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.domain.UserSocialConnection;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.service.impl.UserEventsService;

public class FacebookEventCreateCommand extends HystrixCommand<HystrixSocialResult>{
	private static final Logger logger = LoggerFactory.getLogger(FacebookEventCreateCommand.class);
	
	ConnectionService userSocialConnectionService;
	UserEventsService eventsService;
	ConnectionFactoryLocator connectionFactoryLocator;
	String userActivityId;
	UserEvent userEvent;
	String username;
	


	public FacebookEventCreateCommand(String pEventId, String pUserId) {
		// Set to one minute
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("FacebookCommand"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FacebookThread"))
				.andCommandPropertiesDefaults(
						// we default to a one minute timeout for primary
						HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		logger.info("Inside Facebook Command");
		
		userActivityId = pEventId;
		username = pUserId;
		
	}
	
	public FacebookEventCreateCommand(UserEvent pUserActivity, String pUserId) {
		// Set to one minute
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("FacebookCommand"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FacebookThread"))
				.andCommandPropertiesDefaults(
						// we default to a one minute timeout for primary
						HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		logger.info("Inside Facebook Command");
		
		userEvent = pUserActivity;
		username = pUserId;
		
		
	}
	
	public void setUserSocialConnectionService(
			ConnectionService userSocialConnectionService) {
		this.userSocialConnectionService = userSocialConnectionService;
	}

	public void setEventsService(UserEventsService eventsService) {
		this.eventsService = eventsService;
	}

	public void setConnectionFactoryLocator(
			ConnectionFactoryLocator connectionFactoryLocator) {
		this.connectionFactoryLocator = connectionFactoryLocator;
	}
	
	

	@Override
	public HystrixSocialResult run() throws Exception {

		
		if(userEvent == null){
			userEvent = eventsService.getActivity(userActivityId);
		}
		logger.info("* * * * * * * * * * * * * * * * * Running Facebook Command * * * * * * * * * * * * * * * * *" + this.userEvent.getTitle());

		String providerId = connectionFactoryLocator.getConnectionFactory(Facebook.class)
				.getProviderId();
		UserSocialConnection connection = userSocialConnectionService.getSocialConnection(username, providerId);
		//String userName = userActivity.getOriginatorUserId();
		if(connection == null){
			// User does not have facebook connection.
			return new HystrixSocialResult(Boolean.FALSE, null, "User does not have facebook connection!");
		}
		FacebookEventService fbService=new FacebookEventService(connection.getAccessToken());
		//getFeed(userActivity.getFacebookEventId())
		
		userEvent = fbService.publishEvent(this.userEvent);
		if(StringUtils.isEmpty(userEvent.getFacebookEventId())){
			return new HystrixSocialResult(Boolean.FALSE, "", "Event feed post failed!");
		}
		//Saving the update back to db.
		eventsService.updateUserEvent(userEvent);
		return new HystrixSocialResult(Boolean.TRUE, userEvent.getFacebookEventId(), "Event feed post successful!");
	}


	
	public Future<HystrixSocialResult> executeFacebookEventFeed() {
		return this.queue();
	}
	
	@Override
	protected HystrixSocialResult getFallback() {
		logger
		.info("* * * * * * * * * * * * * * * * * *Running fallback method * * * * * * * * * * * * * * * * * *");
		Throwable exception = getFailedExecutionException();
		logger.error("Facebook command exception: ", exception);
		return new HystrixSocialResult(Boolean.FALSE, null, "Event feed post failed!");
	}


}
