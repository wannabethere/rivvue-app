package com.yatrix.activity.hystrix.command.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.EventInvitee;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.ext.domain.facebook.FacebookSyncupSocialResult;
import com.yatrix.activity.hystrix.command.IFacebookSyncupCommand;
import com.yatrix.activity.store.fb.domain.FacebookInvitee;
import com.yatrix.activity.store.fb.domain.FacebookPost;
import com.yatrix.activity.store.fb.domain.FacebookReference;
import com.yatrix.activity.store.fb.domain.FacebookInvitee.FacebookRsvpStatus;
import com.yatrix.activity.store.mongo.domain.ActivityComment;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;

@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
public class FacebookSyncupCommand extends HystrixCommand<FacebookSyncupSocialResult> implements IFacebookSyncupCommand{
	
	private static final Logger logger = LoggerFactory.getLogger(FacebookSyncupCommand.class);

	@Autowired
	ConnectionService userSocialConnectionService;

	@Autowired
	IUserActivityCatalogService userActivityCatalogService;

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
			userActivity = userActivityCatalogService.findActivity(userActivityId);
		}

		logger.info("Syncing up: " + userActivity.getMessageposted().getMessage());

		String providerId = connectionFactoryLocator.getConnectionFactory(Facebook.class)
				.getProviderId();

		String userName = userActivity.getOriginatorUserId();
		Connection<Facebook> connection = (Connection<Facebook>)userSocialConnectionService
		.getPrimaryConnection(userName, providerId);
		facebook = connection.getApi();

		PagedList<EventInvitee> attending = facebook.eventOperations().getAttending(userActivity.getFacebookEventId());
		PagedList<EventInvitee> declined = facebook.eventOperations().getDeclined(userActivity.getFacebookEventId());
		PagedList<EventInvitee> maybeAttending = facebook.eventOperations().getMaybeAttending(userActivity.getFacebookEventId());

		PagedList<Post> comments = facebook.feedOperations().getFeed(userActivity.getFacebookEventId());

		userActivity.setFacebookAccepted(processUsers(attending, FacebookInvitee.FacebookRsvpStatus.ATTENDING));
		userActivity.setFacebookRejected(processUsers(declined, FacebookInvitee.FacebookRsvpStatus.DECLINED));
		userActivity.setFacebookUnsure(processUsers(maybeAttending, FacebookInvitee.FacebookRsvpStatus.UNSURE));

		userActivity.setFacebookPosts(processFeeds(comments));

		return new FacebookSyncupSocialResult(Boolean.TRUE, userActivity, "Event Synced up successfully!");
	}

	private List<FacebookPost> processFeeds(PagedList<Post> feeds) {

		logger.debug("Comments Size: " + (feeds == null ? 0 : feeds.size()));
		
		List<FacebookPost> postList = new ArrayList<FacebookPost>();
		FacebookPost facebookPost = null;
		FacebookReference reference = null;
		
		for(Post feed : feeds){
			logger.debug(feed.getMessage());
			List<ActivityComment> commentsList = getComments(feed.getComments());
			reference = new FacebookReference(feed.getFrom().getId(), feed.getFrom().getName());
			//String pId, FacebookReference pFrom, Date pCreatedDate, Date pUpdatedTime, String pMessage, List<ActivityComment> pComments
			facebookPost = new FacebookPost(feed.getId(), reference, feed.getCreatedTime(), feed.getUpdatedTime(), feed.getMessage(), commentsList);
			
			postList.add(facebookPost);
		}
		
		return postList;

	}

	private List<ActivityComment> getComments(List<Comment> comments) {
		
		List<ActivityComment> commentList = new ArrayList<ActivityComment>();
		ActivityComment activityComment = null;
		FacebookReference reference = null;
		
		for(Comment comment : comments){
			reference = new FacebookReference(comment.getFrom().getId(), comment.getFrom().getName());
			activityComment = new ActivityComment(comment.getId(), comment.getMessage(), comment.getCreatedTime(), reference);
			
			commentList.add(activityComment);
		}
		
		return commentList;
	}

	private List<FacebookInvitee> processUsers(PagedList<EventInvitee> invitees, FacebookRsvpStatus status) {

		List<FacebookInvitee> inviteeList = new ArrayList<FacebookInvitee>();
		FacebookInvitee invitee = null;
		
		for(EventInvitee eventInvitee : invitees){
			logger.debug("User " + eventInvitee.getName() + " is " + eventInvitee.getRsvpStatus());
			invitee = new FacebookInvitee(eventInvitee.getId(), eventInvitee.getName(), status); 	
			
			inviteeList.add(invitee);
		}

		return inviteeList;
	}

	@Override
	public Future<FacebookSyncupSocialResult> executeFacebookSyncupCommand(String pUserActivityId) {
		userActivityId = pUserActivityId;
		return this.queue();
	}

	@Override
	public Future<FacebookSyncupSocialResult> executeFacebookSyncupCommand(
			UserActivity pUserActivity) {
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

	UserActivity userActivity;


}
