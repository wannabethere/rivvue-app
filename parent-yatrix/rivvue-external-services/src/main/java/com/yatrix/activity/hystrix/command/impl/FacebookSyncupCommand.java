package com.yatrix.activity.hystrix.command.impl;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.ext.domain.facebook.FacebookSyncupSocialResult;
import com.yatrix.activity.hystrix.command.IFacebookSyncupCommand;
import com.yatrix.activity.service.facebook.FacebookEventService;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.domain.UserSocialConnection;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.service.impl.UserEventsService;

@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
public class FacebookSyncupCommand extends HystrixCommand<FacebookSyncupSocialResult> implements IFacebookSyncupCommand{
	
	private static final Logger logger = LoggerFactory.getLogger(FacebookSyncupCommand.class);

	@Autowired
	ConnectionService userSocialConnectionService;

	@Autowired
	UserEventsService eventService;

	@Autowired
	ConnectionFactoryLocator connectionFactoryLocator;

	public FacebookSyncupCommand() {
		// Set to one minute
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("FacebookCommand"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FacebookThread"))
				.andCommandPropertiesDefaults(
						// we default to a one minute timeout for primary
						HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		logger.info("Inside Facebook Command");
	}

	@Override
	@SuppressWarnings("unchecked")
	public FacebookSyncupSocialResult run() throws Exception {

		logger
		.info("* * * * * * * * * * * * * * * * * Running Facebook Command * * * * * * * * * * * * * * * * *");

		if(userActivity == null){
			userActivity = eventService.getActivity(userActivityId);
		}

		logger.info("Syncing up: " + userActivity.getTitle());
		
		String providerId = connectionFactoryLocator.getConnectionFactory(Facebook.class)
				.getProviderId();
		UserSocialConnection connection = userSocialConnectionService.getSocialConnection(userActivity.getOriginatorUserId(), providerId);

		if(connection == null){
			// User does not have facebook connection.
			return new FacebookSyncupSocialResult(Boolean.FALSE, null, "User does not have facebook connection!");
		}
		
		FacebookEventService fbService = new FacebookEventService(connection.getAccessToken());

		fbService.publishCommentsAndSyncFB(userActivity);
		
		return new FacebookSyncupSocialResult(Boolean.TRUE, userActivity, "Event Synced up successfully!");
	}

	@Override
	public Future<FacebookSyncupSocialResult> executeFacebookSyncupCommand(String pUserActivityId) {
		userActivityId = pUserActivityId;
		return this.queue();
	}

	@Override
	public Future<FacebookSyncupSocialResult> executeFacebookSyncupCommand(
			UserEvent pUserActivity) {
		userActivity = pUserActivity;

		return this.queue();
	}

	@Override
	protected FacebookSyncupSocialResult getFallback() {
		logger
		.info("* * * * * * * * * * * * * * * * * *Running fallback method * * * * * * * * * * * * * * * * * *");
		Throwable exception = getFailedExecutionException();

		logger.error("Facebook command exception: ", exception);

		return new FacebookSyncupSocialResult(Boolean.FALSE, null, "Event creation failed!");
	}

	String userActivityId;

	Facebook facebook;

	UserEvent userActivity;


}
