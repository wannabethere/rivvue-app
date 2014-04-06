package com.yatrix.activity.service.facebook;





import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yatrix.activity.service.beans.Parameter;
import com.yatrix.activity.service.external.util.Connection;
import com.yatrix.activity.service.external.util.StringUtils;
import com.yatrix.activity.service.fb.rest.DefaultFacebookClient;
import com.yatrix.activity.service.fb.rest.FacebookClient;
import com.yatrix.activity.service.fb.types.Event;
import com.yatrix.activity.service.fb.types.FacebookType;
import com.yatrix.activity.service.fb.types.Post;
import com.yatrix.activity.service.fb.types.User;
import com.yatrix.activity.store.mongo.domain.Comment;
import com.yatrix.activity.store.mongo.domain.Comment.COMMENTTYPE;
import com.yatrix.activity.store.mongo.domain.Message.VISIBILITY;
import com.yatrix.activity.store.mongo.domain.Participant;
import com.yatrix.activity.store.mongo.domain.Participant.RSVPSTATUS;
import com.yatrix.activity.store.mongo.domain.Participant.TYPE;
import com.yatrix.activity.store.mongo.domain.UserEvent;


public class FacebookEventService {

	//private static final Logger logger = LoggerFactory.getLogger(FacebookEventService.class);
	/**
	 * RestFB Graph API client.
	 */
	private final FacebookClient facebookClient;
	public FacebookEventService(String accessToken){
		facebookClient = new DefaultFacebookClient(accessToken);
	}
	
	public UserEvent publishEvent(UserEvent event){
	    if(event==null){
	    	throw new IllegalArgumentException("Event Cannot be null");
	    }
	    FacebookType publishEventResponse =facebookClient.publish("me/events", FacebookType.class, 
	    									this.createeventParameters(event));
	    out.println("Published event ID: " + publishEventResponse.getId());
	    event.setFacebookEventId(publishEventResponse.getId());
	    this.inviteUsersToEvent(event);
	    //TODO: Only if response Successful
	    event.setFbLupd(System.currentTimeMillis());
	    
		return event;
	}
	
	public void updateEvent(UserEvent event){
		Boolean publishUpdateEventRespone=facebookClient.publish("/"+event.getFacebookEventId(), Boolean.class, 
				this.updateCreateEventParameters(event));
		if(publishUpdateEventRespone){
			event.setFbLupd(System.currentTimeMillis());
		}
		return;
	}
	

	/**
	 * This syncs the event from FB for all the comments
	 * Assumption is that all event Messages are created. If they are deleted or updated we need to handle them.
	 * @param event
	 * @return
	 */
	public UserEvent publishCommentsAndSyncFB(UserEvent event){
	    if(event==null){
	    	throw new IllegalArgumentException("Event Cannot be null");
	    }
	    //Now Update the Event
	    if(StringUtils.isBlank(event.getFacebookEventId())){
	    	throw new IllegalStateException("Facebook Event ID Cannot be null to publish comments");
	    }
	    
	    Event fbevent=facebookClient.fetchObject("/"+event.getFacebookEventId(), Event.class);
	    if(fbevent==null){
	    	throw new IllegalStateException("Facebook Event Cannot be null");
	    }
	    
	    event = this.SyncComments(event);
	    return event;
	}
	
	public UserEvent SyncComments(UserEvent event){
		 //Now we need to find all comments that have occurred
	    List<String> fbCommentsSynced=new ArrayList<String>();
	    for(Comment comment: event.getAppComments()){
	    	if(comment.getLupd()>event.getFbLupd()){
	    		boolean fbAlreadySynced=false;
	    		Comment originalComment=new Comment(comment);
	    		for(Comment.CommentSync sync:comment.getCommentSync()){
	    			if(sync.getType().equals(Comment.COMMENTTYPE.FB)){
	    				fbAlreadySynced=true;
	    				fbCommentsSynced.add(sync.getCommentId());
	    				break;
	    			}
	    		}
	    		if(!fbAlreadySynced){
	    			
	    			FacebookType publishUpdateCommentRespone=facebookClient.publish("/"+event.getFacebookEventId()+"/feed", FacebookType.class, 
	    					Parameter.with("message", comment.getMessage()));
	    			comment.addCommentSync(publishUpdateCommentRespone.getId(), COMMENTTYPE.FB, System.currentTimeMillis());
	    			event.updateComment(originalComment, comment);
	    		}
	    	}
	    }
		
		Connection<Post> fbPosts=facebookClient.fetchConnection("/"+event.getFacebookEventId()+"/feed", Post.class);
		long lastFBUpdateTime=System.currentTimeMillis();
		for(Post fbPost: fbPosts.getData()){
			if(fbCommentsSynced.contains(fbPost.getId())){
				continue;
			}
			Comment comment= new Comment();
			comment.setFromId(fbPost.getFrom().getId());
			comment.setFromAuthorName(fbPost.getFrom().getName());
			comment.setCreatedTime(lastFBUpdateTime);
			comment.setLupd(lastFBUpdateTime);
			comment.addCommentSync(fbPost.getId(), COMMENTTYPE.FB, lastFBUpdateTime);
			event.addComment(comment);
		}
		event.setFbLupd(lastFBUpdateTime);
		return event;
	}
	
	public UserEvent postComment(UserEvent event, Comment comment){
		if(event==null){
			throw new IllegalArgumentException("Event Cannot be null");
		}
		//Now Update the Event
		if(StringUtils.isBlank(event.getFacebookEventId())){
			throw new IllegalStateException("Facebook Event ID Cannot be null to publish comments");
		}
		if(comment==null){
			return event;
		}
		else{
			for(Comment.CommentSync sync:comment.getCommentSync()){
    			if(sync.getType().equals(Comment.COMMENTTYPE.FB)){
    				return event;
    			}
    		}
		}
		Comment oldComment=new Comment(comment);
		FacebookType publishUpdateCommentRespone=facebookClient.publish("/"+event.getFacebookEventId()+"/feed", FacebookType.class, 
				Parameter.with("message",comment.getMessage()));
		
		comment.addCommentSync(publishUpdateCommentRespone.getId(), COMMENTTYPE.FB, System.currentTimeMillis());
		event.updateComment(oldComment, comment);
		return event;
	}
	
	public void inviteUsersToEvent(UserEvent event){
		if(event==null){
			throw new IllegalArgumentException("Event Cannot be null");
		}
		//Now Update the Event
		if(StringUtils.isBlank(event.getFacebookEventId())){
			throw new IllegalStateException("Facebook Event ID Cannot be null to publish comments");
		}
		List<String> ids = new ArrayList<String>();
		for(Participant p : event.getInvitedIds()){
			if(p.getUserType().equals(TYPE.FB)){
				ids.add(p.getUserId());
			}
		}
		if(ids.size()>0){
			String users=StringUtils.join(ids);
			Boolean response=facebookClient.publish("/"+event.getFacebookEventId()+"/invited", Boolean.class, 
					Parameter.with("users",users));
			if(!response){
				System.out.println("Failed to update user Status");
			}
		}
		
		
	}
	
	public UserEvent publishUserInvitationUpdate(UserEvent event, Participant p, boolean addUser){
		if(event==null){
			throw new IllegalArgumentException("Event Cannot be null");
		}
		//Now Update the Event
		if(StringUtils.isBlank(event.getFacebookEventId())){
			throw new IllegalStateException("Facebook Event ID Cannot be null to publish comments");
		}
		Parameter parameter;
		String url="attending";
		switch(p.getStatus()){
		case ATTENDING:{
				url="attending";
				parameter=Parameter.with("","");
				break;
			}
		case DECLINED:{
				url="declined";
				parameter=Parameter.with("","");
				break;
			}
		case MAYBE:{
				url="maybe";
				parameter=Parameter.with("","");
				break;
			}
		default:{
				if(!addUser){
					//Nothing to update.
					return event;
				}
				url="invited";
				parameter=Parameter.with("users",p.getUserId());
			}
		}
		Boolean response=facebookClient.publish("/"+event.getFacebookEventId()+"/"+url, Boolean.class, parameter);
		if(!response){
			System.out.println("Failed to update user Status");
		}
		return event;
	}
	
	/**
	 * Doesnot Look like FB allows events to be updated from outside. 
	 * So the users will need to accept in FB and app together.
	 * But we will put messages that will indicated the user is coming. Even though the event might not updated in FB.
	 * @param event
	 * @return
	 */
	public UserEvent SyncInvitations(UserEvent event){
		if(event==null){
			throw new IllegalArgumentException("Event Cannot be null");
		}
		//Now Update the Event
		if(StringUtils.isBlank(event.getFacebookEventId())){
			throw new IllegalStateException("Facebook Event ID Cannot be null to publish comments");
		}
		Connection<User> fbPosts=facebookClient.fetchConnection("/"+event.getFacebookEventId()+"/accepted", User.class);
		for(User user: fbPosts.getData()){
			Participant p = new Participant();
			p.setUserId(user.getId());
			p.setUserType(TYPE.FB);
			p.setLupd(System.currentTimeMillis());
			p.setInviteeName(user.getFirstName()+" "+user.getLastName());
			
			event.addInvitedId(p);
		}

		Connection<User> fbPostsMaybe=facebookClient.fetchConnection("/"+event.getFacebookEventId()+"/maybe", User.class);
		for(User user: fbPostsMaybe.getData()){
			Participant p = new Participant();
			p.setUserId(user.getId());
			p.setUserType(TYPE.FB);
			p.setLupd(System.currentTimeMillis());
			p.setInviteeName(user.getFirstName()+" "+user.getLastName());
			event.addInvitedId(p);
		}
		
		Connection<User> fbPostsDeclined=facebookClient.fetchConnection("/"+event.getFacebookEventId()+"/accepted", User.class);
		for(User user: fbPostsDeclined.getData()){
			Participant p = new Participant();
			p.setUserId(user.getId());
			p.setUserType(TYPE.FB);
			p.setLupd(System.currentTimeMillis());
			p.setInviteeName(user.getFirstName()+" "+user.getLastName());
			event.addInvitedId(p);
		}
		return event;
	}
	
	/**
	 * Method Deletes Event.
	 * @param deleteEvent
	 */
	public void deleteEvent(UserEvent deleteEvent){
		facebookClient.deleteObject("/"+deleteEvent.getFacebookEventId(),Parameter.with("delete",deleteEvent.getFacebookEventId()));
		return;
	}
	
	
	
	/**
	 * name The name of the event. This field is required. string
	   start_time The start time of the event, in ISO-8601 format. The following formats are accepted: string
    			Date-only (e.g., '2012-07-04'): events that have a date but no specific time yet.
    			Precise-time (e.g., '2012-07-04T19:00:00-0700'): events that start at a particular point in time, in a specific offset from UTC.
	   end_time The end time of the event, in ISO-8601 format as above. string
	   description   Event description string
	   location   Where the event is located as an arbitrary descriptive string. string
	   location_id Where the event is located as the page ID of a specific place. If supplied, this will supersede the location string value. string 
	   (location_id= This is not going to be populated)
	   privacy_type  Privacy of the event. enum{'OPEN', 'SECRET', 'FRIENDS'}
	 * @param event
	 * @return
	 */
	
	private Parameter[] createeventParameters(UserEvent event){
		Parameter[] params= new Parameter[6];
		params[0] = Parameter.with("name", event.getTitle());
		Date startTime = new Date(event.getStartTime());
	    Date endTime = new Date(event.getEndTime());
		params[1] = Parameter.with("start_time", startTime);
		params[2] = Parameter.with("end_time", endTime);
		params[3] = Parameter.with("description", event.getDescription());
		params[4] = Parameter.with("location", event.getLocation().getFormattedAddress());
		String visibility="OPEN";
		if(VISIBILITY.ME.equals(event.getVisibility()) || VISIBILITY.PRIVATE.equals(event.getVisibility())){
			visibility="SECRET";
		}
		else if(VISIBILITY.FRIENDSONLY.equals(event.getVisibility())){
			visibility="FRIENDS";
		}
		params[5] = Parameter.with("privacy_type", visibility);
		return params;
	}
	
	/**
	 * This method is used for Updating the event.
	 * @param event
	 * @return
	 */
	private Parameter[] updateCreateEventParameters(UserEvent event){
		Parameter[] params= new Parameter[7];
		params[0] = Parameter.with("name", event.getTitle());
		Date startTime = new Date(event.getStartTime());
	    Date endTime = new Date(event.getEndTime());
		params[1] = Parameter.with("start_time", startTime);
		params[2] = Parameter.with("end_time", endTime);
		params[3] = Parameter.with("description", event.getDescription());
		params[4] = Parameter.with("location", event.getLocation().getFormattedAddress());
		String visibility="OPEN";
		if(VISIBILITY.ME.equals(event.getVisibility()) || VISIBILITY.PRIVATE.equals(event.getVisibility())){
			visibility="SECRET";
		}
		else if(VISIBILITY.FRIENDSONLY.equals(event.getVisibility())){
			visibility="FRIENDS";
		}
		params[5] = Parameter.with("privacy_type", visibility);
		params[6]=Parameter.with("ticket_uri","http://google.com");
		return params;
	}
	
	
	
	
	  
}
