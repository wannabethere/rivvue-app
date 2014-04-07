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

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.hystrix.fb.command.HystrixSocialResult;
import com.yatrix.activity.hystrix.fb.command.IFacebookJoinEventCommand;
import com.yatrix.activity.hystrix.fb.command.impl.FacebookEventJoinCommand;
import com.yatrix.activity.service.facebook.FacebookEventService;
import com.yatrix.activity.store.mongo.domain.Comment;
import com.yatrix.activity.store.mongo.domain.Participant;
import com.yatrix.activity.store.mongo.domain.Participant.RSVPSTATUS;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.domain.UserSocialConnection;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.service.impl.UserEventsService;


public class FacebookEventJoinCommand extends HystrixCommand<HystrixSocialResult>{
	
	private static final Logger logger = LoggerFactory.getLogger(FacebookEventJoinCommand.class);

	ConnectionService userSocialConnectionService;
	ConnectionFactoryLocator connectionFactoryLocator;
	UserEventsService eventsService;
	UserEvent userActivity;
	Participant participant;
	boolean addUser;
	public FacebookEventJoinCommand(UserEvent pUserActivity,Participant p,boolean addUser) {
		// Set to one minute
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
				.andCommandKey(HystrixCommandKey.Factory.asKey("FacebookCommand"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FacebookThread"))
				.andCommandPropertiesDefaults(
						// we default to a one minute timeout for primary
						HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		logger.info("Inside Facebook Command");
		userActivity = pUserActivity;
		participant=p;
		this.addUser=addUser;
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
		.info("* * * * * * * * * * * * * * * * * Running Facebook Command * * * * * * * * * * * * * * * * *" );
		
		if(userActivity == null){
			return new HystrixSocialResult(Boolean.FALSE, null, "Event feed post was not successful!");
		}
		
		userActivity.addInvitedId(participant);
	
		Comment comment= new Comment();
		comment.setFromId(participant.getUserId());
		comment.setFromAuthorName(participant.getInviteeName());
		comment.setMessage(participant.getInviteeName() + " has Joined you.");
		comment.setCreatedTime(System.currentTimeMillis());
		userActivity.addComment(comment);
		eventsService.updateUserEvent(userActivity);
		
		if(participant.getUserType().equals("FB")){
			String providerId = connectionFactoryLocator.getConnectionFactory(Facebook.class)
					.getProviderId();
			UserSocialConnection connection = userSocialConnectionService.getSocialConnection(participant.getUserId(), providerId);
			//String userName = userActivity.getOriginatorUserId();
			if(connection == null){
				// User does not have facebook connection.
				return new HystrixSocialResult(Boolean.FALSE, null, "User does not have facebook connection!");
			}
			FacebookEventService fbService=new FacebookEventService(connection.getAccessToken());
			//Adding user
			fbService.publishUserInvitationUpdate(userActivity, participant, addUser);
			//Posting
			userActivity=fbService.postComment(userActivity, comment);			
		}
		
		eventsService.updateUserEvent(userActivity);
		return new HystrixSocialResult(Boolean.TRUE, null, "Event feed post successful!");
	}

	
	public Future<HystrixSocialResult> executeFacebookJoinEvent() {
		return this.queue();
	}

	
	protected HystrixSocialResult getFallback() {
		logger
		.info("* * * * * * * * * * * * * * * * * *Running fallback method * * * * * * * * * * * * * * * * * *");
		Throwable exception = getFailedExecutionException();

		logger.error("Facebook command exception: ", exception);

		return new HystrixSocialResult(Boolean.FALSE, null, "Event feed post failed!");
	}

		

}
