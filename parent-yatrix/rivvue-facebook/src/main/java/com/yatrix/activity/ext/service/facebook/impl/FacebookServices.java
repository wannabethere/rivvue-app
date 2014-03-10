package com.yatrix.activity.ext.service.facebook.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.EventInvitee;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Service;

import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.domain.ActivityComment;
import com.yatrix.activity.store.fb.domain.FacebookInvitee;
import com.yatrix.activity.store.fb.domain.FacebookPost;
import com.yatrix.activity.store.fb.domain.FacebookReference;
import com.yatrix.activity.store.fb.domain.FacebookInvitee.FacebookRsvpStatus;


@Service
public class FacebookServices {

	
	private static final Logger logger = LoggerFactory.getLogger(FacebookServices.class);
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@Autowired
	private ConnectionService userSocialConnectionService;
	
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;
	private Facebook facebook;
	
	public void getFBAPI(String userId){
		if(userId==null){
			throw new IllegalStateException("USer ID cannot be nul");
		}
		String providerId =connectionFactoryLocator.getConnectionFactory(Facebook.class).getProviderId();
		@SuppressWarnings("unchecked")
		Connection<Facebook> connection = (Connection<Facebook>)userSocialConnectionService.getPrimaryConnection(userId, providerId);
		if(connection==null){
			throw new IllegalStateException("USer Has not granted Privileges for FB");
		}
		facebook = connection.getApi();
		return;
	}
	
	/**
	 * Given a userId return all the friends as a page List.
	 * @param userId
	 * @return
	 */
	public PagedList<FacebookProfile> getFriends(String userId){
		if(this.facebook==null){
			this.getFBAPI(userId);
		}
		PagedList<FacebookProfile> friends = facebook.friendOperations().getFriendProfiles();
		return friends;
	}
	
	public FacebookProfile getUserProfile(String userId){
		if(this.facebook==null){
			this.getFBAPI(userId);
		}
		return facebook.userOperations().getUserProfile();
	}
	
	/**
	 * Creates an event, posts a message and returns the eventId
	 * @param userActivity
	 * @return
	 */
	
	public String createEvent(UserActivity userActivity){
		if(this.facebook==null){
			this.getFBAPI(userActivity.getOriginatorUserId());
		}
		String message = userActivity.getMessageposted() == null ? "" : userActivity.getMessageposted().getMessage();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

	    String invitees[] = userActivity.getParticipants().toArray(new String[0]);
	    long stime=userActivity.getStartTime().getTime();
	    long etime=userActivity.getEndTime().getTime();
	    
	    if(stime+3600>etime){
	    	etime=stime+3600;
	    }
	    Date startTime= new Date(stime);
	    Date endTime= new Date(etime);
	    
	    String startDate = dateFormat.format(startTime);
	    String endDate = dateFormat.format(endTime);
	    String eventId = facebook.eventOperations().createEvent(message, startDate, endDate);
	    facebook.eventOperations().sendInvitation(eventId, invitees);
	    userActivity.setFacebookEventId(eventId);
		return eventId;
	}
	
	//Ideally this should be done differently.
	public String postEventMessage(UserActivity activity, String postFBUserId, String message){
		if(this.facebook==null){
			this.getFBAPI(activity.getOriginatorUserId());
		}
		String feedId = facebook.feedOperations().post(activity.getFacebookEventId(), message);
		activity.setFacebookFeedId(feedId);
		return feedId;
	}
	
	
	public void postMessage(){	
	}
	
	public void syncFBEventToActivity(UserActivity userActivity ){
		if(this.facebook==null){
			this.getFBAPI(userActivity.getOriginatorUserId());
		}
		PagedList<EventInvitee> attending = facebook.eventOperations().getAttending(userActivity.getFacebookEventId());
		PagedList<EventInvitee> declined = facebook.eventOperations().getDeclined(userActivity.getFacebookEventId());
		PagedList<EventInvitee> maybeAttending = facebook.eventOperations().getMaybeAttending(userActivity.getFacebookEventId());
		PagedList<Post> comments = facebook.feedOperations().getFeed(userActivity.getFacebookEventId());
		userActivity.setFacebookAccepted(processUsers(attending, FacebookInvitee.FacebookRsvpStatus.ATTENDING));
		userActivity.setFacebookRejected(processUsers(declined, FacebookInvitee.FacebookRsvpStatus.DECLINED));
		userActivity.setFacebookUnsure(processUsers(maybeAttending, FacebookInvitee.FacebookRsvpStatus.UNSURE));

		userActivity.setFacebookPosts(processFeeds(comments));
		return;
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
	
	
}
