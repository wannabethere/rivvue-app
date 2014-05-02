package com.yatrix.activity.store.mongo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.Circle;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yatrix.activity.store.exception.ActivityDBException;
import com.yatrix.activity.store.mongo.domain.Comment;
import com.yatrix.activity.store.mongo.domain.Message.VISIBILITY;
import com.yatrix.activity.store.mongo.domain.Participant;
import com.yatrix.activity.store.mongo.domain.Participant.RSVPSTATUS;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.repository.UserEventsRepository;

@Service
public class UserEventsService {

	
	private static final Logger logger = LoggerFactory.getLogger(UserEventsService.class);
	@Autowired
	private UserEventsRepository userEventRepository;
	@Autowired
	private ProfileService userProfileService;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private UserAccountService userAccountService;
	
	
	public UserEvent createUserEvent(UserEvent activity){
		//Add logic to store in the database
		mongoTemplate.insert(activity);
		//Notification Objects are created for Master worker spring patterns to process the data.
		return activity;
	}
	
	
	public UserEvent updateUserEvent(UserEvent activity) throws ActivityDBException{
		UserEvent oldEvent = userEventRepository.findById(activity.getId());
		if(oldEvent==null){
			throw new ActivityDBException("Failed to find the original event so nothing to update");
		}
		//TODO: Merge Events.
		//this.mergeEvents(oldEvent, activity);
		mongoTemplate.save(activity);
		return activity;
	}
	
	public UserEvent updateUserEventStatus(Participant p, String eventId)  throws ActivityDBException{
		UserEvent existingEvent = userEventRepository.findById(eventId);
		long lupd=System.currentTimeMillis();
		//Find the participant in the event.
		List<Participant> existingParticipants= existingEvent.getInvitedIds();
		if(existingParticipants==null){
			throw new ActivityDBException("Participants cannot be null");
		}
		Participant existingParticipant=null;
		for(Participant p1: existingParticipants){
			if(p1.getUserId().equals(p.getUserId())){
				existingParticipant=p1;
				break;
			}
		}
		String message=p.getStatus().getMessage();
		if(existingParticipant!=null){
			//Create a new Participant.
			logger.info("Updating existing Participant " + existingParticipant.toString());
			message=p.getStatus().getJoinedMessage();
			existingEvent.removedInvited(existingParticipant);
			//Remove this from the existingParticipant to update the current status.
		}
		p.setLupd(lupd);
		existingParticipant=p;
		p.setLupd(lupd);
		existingEvent.addInvitedId(existingParticipant);
		//Adding a Message
		existingEvent.addComment(this.createComment(p, null));
		existingEvent.setLupd(lupd);
		//Add the notification that this event has to be propogated to FB, TWITTER, GOOGLE etc...
		return userEventRepository.save(existingEvent);
	}
	
	public UserEvent updateUserEventStatus(List<Participant> participants, String eventId)  throws ActivityDBException{
		UserEvent event=null;
		for(Participant participant: participants){
			event=this.updateUserEventStatus(participant, eventId);
		}
		return event;
	}
	
	
	public UserEvent removeEventParticipants(List<Participant> participants, String eventId)  throws ActivityDBException{
		UserEvent event=null;
		for(Participant participant: participants){
			event=this.removeEventParticipant(participant, eventId);
		}
		return event;
	}
	
	public UserEvent removeEventParticipant(Participant p, String eventId)  throws ActivityDBException{
		UserEvent existingEvent = userEventRepository.findById(eventId);
		long lupd=System.currentTimeMillis();
		//Find the participant in the event.
		List<Participant> existingParticipants= existingEvent.getInvitedIds();
		if(existingParticipants==null){
			throw new ActivityDBException("Participants cannot be null");
		}
		Participant existingParticipant=null;
		for(Participant p1: existingParticipants){
			if(p1.getUserId().equals(p.getUserId())){
				existingParticipant=p1;
				break;
			}
		}
		//String message=p.getStatus().getMessage();
		if(existingParticipant!=null){
			//Create a new Participant.
			logger.info("Updating existing Participant " + existingParticipant.toString());
			//message=p.getStatus().getJoinedMessage();
			existingEvent.removedInvited(existingParticipant);
			//Remove this from the existingParticipant to update the current status.
		}
		//existingEvent.addComment(this.createComment(p, p.getInviteeName() + " is not coming to the event"));
		existingEvent.setLupd(lupd);
		return userEventRepository.save(existingEvent);
	}
	
	
	
	
	public UserEvent updateUserEventMessage(Participant p,String eventId, String message) throws ActivityDBException{
		UserEvent existingEvent = userEventRepository.findById(eventId);
		if(existingEvent==null){
			throw new ActivityDBException("Failed to find the original event so nothing to update");
		}
		
		existingEvent.addComment(this.createComment(p, message));
		return userEventRepository.save(existingEvent);
	}
	
	/**
	 *  // the query object
        Criteria findSeriesCriteria = Criteria.where("title").is(title);
        // the field object
        Criteria findSagaNumberCriteria = Criteria.where("books").elemMatch(Criteria.where("seriesNumber").is(seriesNumber));
        BasicQuery query = new BasicQuery(findSeriesCriteria.getCriteriaObject(), findSagaNumberCriteria.getCriteriaObject());
 
	 * @param userId
	 * @return
	 */
	
	public List<UserEvent> getInvitedActivities(String userId){
		return this.getStatusActivitiesForUser(userId,RSVPSTATUS.NOT_REPLIED,false);
	}
	
	private List<UserEvent> getStatusActivitiesForUser(String userId, RSVPSTATUS status, boolean allEvents){
		Criteria findUsersInvited;
		//Criteria eventCriteria= Criteria.where("deleted").is(false);
		if(!allEvents){
			findUsersInvited=Criteria.where("invitedIds").elemMatch(Criteria.where("userId").is(userId).and("status").is(status.toString())).and("deleted").is(false);
		}
		else{
			findUsersInvited=Criteria.where("invitedIds").elemMatch(Criteria.where("userId").is(userId)).and("deleted").is(false);
		}
		BasicQuery query = new BasicQuery( findUsersInvited.getCriteriaObject());
		List<UserEvent> allEventsList= mongoTemplate.find(query, UserEvent.class);
		return allEventsList;
	}
	
	/**
	 *  // the query object
        Criteria findSeriesCriteria = Criteria.where("title").is(title);
        // the field object
        Criteria findSagaNumberCriteria = Criteria.where("books").elemMatch(Criteria.where("seriesNumber").is(seriesNumber));
        BasicQuery query = new BasicQuery(findSeriesCriteria.getCriteriaObject(), findSagaNumberCriteria.getCriteriaObject());
 
	 * @param userId
	 * @return
	 */
	
	public List<UserEvent> getInvitedActivities(List<String> userIds){
		return this.getStatusActivitiesForUser(userIds,RSVPSTATUS.NOT_REPLIED,false);
	}
	
	private List<UserEvent> getStatusActivitiesForUser(List<String> userIds, RSVPSTATUS status, boolean allEvents){
		Criteria findUsersInvited;
		//Criteria eventCriteria= Criteria.where("deleted").is(false);
		if(!allEvents){
			findUsersInvited=Criteria.where("invitedIds").elemMatch(Criteria.where("userId").in(userIds).and("status").is(status.toString())).and("deleted").is(false);
		}
		else{
			findUsersInvited=Criteria.where("invitedIds").elemMatch(Criteria.where("userId").in(userIds)).and("deleted").is(false);
		}
		BasicQuery query = new BasicQuery( findUsersInvited.getCriteriaObject());
		List<UserEvent> allEventsList= mongoTemplate.find(query, UserEvent.class);
		return allEventsList;
	}
	
	/**
	 * Get all the Events that I need to show on my calendar.
	 * @param userId
	 * @return
	 */
	public List<UserEvent> getMyActivities(String userId){
		
		
		List<UserEvent> allEvents= getStatusActivitiesForUser(userId, null,true);
		List<UserEvent> myCreatedEvents= userEventRepository.findByoriginatorUserId(userId);
		
		if(myCreatedEvents!=null && myCreatedEvents.size()>0){
			allEvents.removeAll(myCreatedEvents);
			allEvents.addAll(myCreatedEvents);
		}
		
		return allEvents;
	}
	
	/**
	 * Get all the Events that I need to show on my calendar for a month.
	 * @param userId
	 * @return
	 */
	public List<UserEvent> getMyActivities(String userId, Long start, Long end){
		List<String> userIds = new ArrayList<String>();
		userIds.add(userId);
		
		// Get invited to FB id even.
		UserAccount userAccount = userAccountService.getUserAccount(userId);
		
		if(userAccount != null && !StringUtils.isEmpty(userAccount.getFacebookId())){
			userIds.add(userAccount.getFacebookId());
		}
		
		List<UserEvent> allEvents= getStatusActivitiesForUser(userIds, start, end, null,true);
		List<UserEvent> myCreatedEvents= findByoriginatorUserId(userId, start, end);
		
		if(myCreatedEvents!=null && myCreatedEvents.size()>0){
			allEvents.removeAll(myCreatedEvents);
			allEvents.addAll(myCreatedEvents);
		}
		
		return allEvents;
	}
	
	
	private List<UserEvent> findByoriginatorUserId(String userId, Long start,
			Long end) {
		
		Criteria eventCriteria;
		if(end==null){
			eventCriteria= Criteria.where("originatorUserId").is(userId).and("startTime").gte(start).and("deleted").is(false);
		}
		else{
			eventCriteria= Criteria.where("originatorUserId").is(userId).and("startTime").gte(start).and("endTime").lte(end).and("deleted").is(false);
		}
		BasicQuery query = new BasicQuery( eventCriteria.getCriteriaObject());
		List<UserEvent> allEventsList= mongoTemplate.find(query, UserEvent.class);
		
		return allEventsList;
	}


	private List<UserEvent> getStatusActivitiesForUser(List<String> userIds,
			Long start, Long end, Object status, boolean allEvents) {
		
		Criteria findUsersInvited;
		//Criteria eventCriteria= Criteria.where("deleted").is(false);
		if(!allEvents){
			findUsersInvited=Criteria.where("invitedIds").elemMatch(Criteria.where("userId").in(userIds).and("status").and("startTime").gte(start).and("endTime").lte(end).is(status.toString())).and("deleted").is(false);
		}
		else{
			findUsersInvited=Criteria.where("invitedIds").elemMatch(Criteria.where("userId").in(userIds)).and("startTime").gte(start).and("endTime").lte(end).and("deleted").is(false);
		}
		BasicQuery query = new BasicQuery( findUsersInvited.getCriteriaObject());
		List<UserEvent> allEventsList= mongoTemplate.find(query, UserEvent.class);
		
		return allEventsList;
		
	}


	public List<UserEvent> getActivitiesCreatedByMe(String userId){
		List<UserEvent> allEvents= userEventRepository.findByoriginatorUserId(userId);
		List<UserEvent> activeEvents= new ArrayList<UserEvent>();
		for(UserEvent event:allEvents){
			if(!event.isDeleted()){
				activeEvents.add(event);
			}
		}
		return activeEvents;
	}
	
	/**
	 * Service to find all the activities for a user start now to whenever.
	 * @param userId
	 * @param st
	 * @param end
	 * @return
	 */
	public List<UserEvent> getActivitiesBetweenStAndEnd(String userId,Long st, Long end){
		
		Criteria eventCriteria;
		if(end==null){
			eventCriteria= Criteria.where("userId").is(userId).and("startTime").gte(st).and("deleted").is(false);
		}
		else{
			eventCriteria= Criteria.where("userId").is(userId).and("startTime").gte(st).and("endTime").lte(end).and("deleted").is(false);
		}
		BasicQuery query = new BasicQuery( eventCriteria.getCriteriaObject());
		List<UserEvent> allEventsList= mongoTemplate.find(query, UserEvent.class);
		return allEventsList;
		
	}
	
	
	/**
	 * Service to find all the activities near you at this time.
	 * 
	 * @param st
	 * @param end
	 * @param lngLat
	 * @return
	 */
	public List<UserEvent> getActivitiesBetweenStAndEnd(Long st, Long end, double[] lngLat){
		
		Criteria eventCriteria;
		if(end==null){
			eventCriteria= Criteria.where("startTime").gte(st).and("deleted").is(false);
		}
		else{
			eventCriteria= Criteria.where("startTime").gte(st).and("endTime").lte(end).and("deleted").is(false);
		}
		//Need to find what is the right query.
		Circle circle = new Circle(lngLat[0], lngLat[1],0.01);
		Criteria range= Criteria.where("location.latlng").withinSphere(circle);
		BasicQuery query = new BasicQuery( eventCriteria.getCriteriaObject(),range.getCriteriaObject());
		List<UserEvent> allEventsList= mongoTemplate.find(query, UserEvent.class);
		return allEventsList;
		
	}
	
	
	public UserEvent getActivity(String eventId){
		return userEventRepository.findById(eventId);
	}
	
	public void deleteUserEvent(String eventId) throws ActivityDBException{
		UserEvent event = userEventRepository.findById(eventId);
		if(event==null){
			throw new ActivityDBException("Failed to find the original event so nothing to update");
		}
		event.setDeleted(true);
		userEventRepository.save(event);
		return;
	}
	
	public List<UserEvent> getActivitiesByState(String state){
		//Criteria eventCriteria=Criteria.where("visibility").is(VISIBILITY.PUBLIC.toString()).and("deleted").is(false);
		Criteria locationCriteria = Criteria.where("location.place").regex(state).and("visibility").is(VISIBILITY.PUBLIC.toString()).and("deleted").is(false);
		BasicQuery query = new BasicQuery(locationCriteria.getCriteriaObject());
		List<UserEvent> events =mongoTemplate.find(query, UserEvent.class);
		return events;	
	}
	
	/**
	 * Searching a set of activities by the tags of user
	 * @param tags
	 * @return
	 */
	public  List<UserEvent> searchActivities(List<String> tags){
		
		
		return null;
	}
	
	/**
	 * Search activities by location
	 * @param location
	 * @return
	 */
	public  List<UserEvent> searchActivitiesByLocation(String location){
		return null;
	}
	
	/**
	 * Search activities by closest time.
	 * @param location
	 * @return
	 */
	public  List<UserEvent> searchActivitiesByTime(String location){
		return null;
	}
	
	
	/**
	 * Search activities nearest to the values populated as much.
	 * @param activity
	 * @return
	 */
	public  List<UserEvent> searchActivities(UserEvent activity){
		return null;
	}
	
	/**
	 * Paged list to be used by syncup command.
	 * @return
	 */
	public List<UserEvent> getUserEvents(int skip, int limit){
		
		Criteria eventCriteria = Criteria.where("deleted").is(false);
		
		
		BasicQuery query = new BasicQuery( eventCriteria.getCriteriaObject());
		query.skip(skip);
		query.limit(limit);
		
		return mongoTemplate.find(query, UserEvent.class);
	}

	
	
	private void mergeEvents(UserEvent oldEvent, UserEvent newEvent){
		
	}
	
	
	private Comment createComment(Participant p, String message){
		String messageForEvent = "Dummy Message";
		if(p.getStatus()!=null){
			if(p.getStatus().equals(RSVPSTATUS.ATTENDING)){
				messageForEvent=p.getInviteeName()  + " is joining you.";
			}
			else if(p.getStatus().equals(RSVPSTATUS.DECLINED)){
				messageForEvent=p.getInviteeName()  + " is going to miss the event.";
			}
			else if(p.getStatus().equals(RSVPSTATUS.MAYBE)){
				messageForEvent=p.getInviteeName()  + " is not sure about coming to the event.";
			}
			else{
				messageForEvent=p.getInviteeName()  + " has been invited.";
			}
			if(!StringUtils.isEmpty(message)){
				messageForEvent=message;
			}
		}
		else{
			if(!StringUtils.isEmpty(message)){
				messageForEvent=message;
			}
			
		}
		Comment comment=new Comment(messageForEvent, System.currentTimeMillis(), p);
		return comment;
	}
	
	
}
