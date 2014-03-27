package com.yatrix.activity.hystrix.command.impl;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.hystrix.command.HystrixSocialResult;
import com.yatrix.activity.hystrix.command.IFacebookJoinEventCommand;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.repository.ConnectionService;

@Service
@Scope(
		value = "prototype",
		proxyMode = ScopedProxyMode.INTERFACES)
public class FacebookJoinEventCommand
extends HystrixCommand<HystrixSocialResult>
implements IFacebookJoinEventCommand
{
	
	private static final Logger logger = LoggerFactory.getLogger(FacebookJoinEventCommand.class);

	@Autowired
	ConnectionService userSocialConnectionService;

	@Autowired
	ConnectionFactoryLocator connectionFactoryLocator;

	public FacebookJoinEventCommand() {
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
	public HystrixSocialResult run() throws Exception {

		logger
		.info("* * * * * * * * * * * * * * * * * Running Facebook Command * * * * * * * * * * * * * * * * *" );

		

		logger.info("Syncing up: " + userActivity.getMessageposted().getMessage());

		String providerId = connectionFactoryLocator.getConnectionFactory(Facebook.class)
				.getProviderId();

		//String userName = userActivity.getOriginatorUserId();
		Connection<Facebook> connection = (Connection<Facebook>)userSocialConnectionService
		.getPrimaryConnection(username, providerId);
		
		if(connection == null){
			// User does not have facebook connection.
			return new HystrixSocialResult(Boolean.FALSE, null, "User does not have facebook connection!");
		}
		
		facebook = connection.getApi();

		//getFeed(userActivity.getFacebookEventId())
		  facebook.eventOperations().acceptInvitation(userActivity.getFacebookEventId());


		return new HystrixSocialResult(Boolean.TRUE, null, "Event feed post successful!");
	}

	@Override
	public Future<HystrixSocialResult> executeFacebookJoinEvent(
			UserActivity pUserActivity, String pUserId) {
		userActivity = pUserActivity;
		username = pUserId;
		
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

	Facebook facebook;

	UserActivity userActivity;
	
	String username;
	

}
