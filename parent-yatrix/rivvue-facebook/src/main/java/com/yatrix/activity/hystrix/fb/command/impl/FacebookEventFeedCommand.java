package com.yatrix.activity.hystrix.fb.command.impl;

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
import org.springframework.util.StringUtils;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.hystrix.fb.command.HystrixSocialResult;
import com.yatrix.activity.hystrix.fb.command.IFacebookPostEventFeedCommand;
import com.yatrix.activity.hystrix.fb.command.impl.FacebookEventFeedCommand;
import com.yatrix.activity.service.facebook.FacebookEventService;
import com.yatrix.activity.store.mongo.domain.Comment;
import com.yatrix.activity.store.mongo.domain.Participant;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.domain.UserSocialConnection;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;
import com.yatrix.activity.store.mongo.service.impl.UserEventsService;

@Service
@Scope(
		value = "prototype",
		proxyMode = ScopedProxyMode.INTERFACES)
public class FacebookEventFeedCommand extends HystrixCommand<HystrixSocialResult>{
	private static final Logger logger = LoggerFactory.getLogger(FacebookEventFeedCommand.class);
	ConnectionService userSocialConnectionService;
	UserEventsService eventsService;
	ConnectionFactoryLocator connectionFactoryLocator;
	String userActivityId;
	UserEvent userEvent;
	String username;
	Comment comment;
	String fbId;


	public FacebookEventFeedCommand(String pEventId, String pUserId,String fbUserId,Comment pComment) {
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
		comment=pComment;
		fbId=fbUserId;
		
	}
	
	public FacebookEventFeedCommand(UserEvent pUserActivity, String pUserId,String fbUserId,Comment pComment) {
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
		comment=pComment;
		fbId=fbUserId;
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
	@SuppressWarnings("unchecked")
	public HystrixSocialResult run() throws Exception {

		logger
		.info("* * * * * * * * * * * * * * * * * Running Facebook Command * * * * * * * * * * * * * * * * *" + comment.getMessage());

		if(userEvent == null){
			userEvent = eventsService.getActivity(userActivityId);
		}

		if(!StringUtils.isEmpty(fbId)){
			//If Facebook Id is found. Then make a call.
			String providerId = connectionFactoryLocator.getConnectionFactory(Facebook.class)
			.getProviderId();
			UserSocialConnection connection = userSocialConnectionService.getSocialConnection(fbId, providerId);
			//String userName = userActivity.getOriginatorUserId();
			if(connection == null){
				// User does not have facebook connection.
				return new HystrixSocialResult(Boolean.FALSE, null, "User does not have facebook connection!");
			}
			FacebookEventService fbService=new FacebookEventService(connection.getAccessToken());
			userEvent = fbService.postComment(userEvent, comment);
		}
		//Saving the update back to db.
		eventsService.updateUserEvent(userEvent);
		return new HystrixSocialResult(Boolean.TRUE, userEvent.getFacebookFeedId(), "Event feed post successful!");
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
