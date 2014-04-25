package com.yatrix.activity.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.yatrix.activity.service.dto.EventDto;
import com.yatrix.activity.service.dto.Invitees;
import com.yatrix.activity.store.fb.domain.FacebookInvitee;
import com.yatrix.activity.store.fb.domain.FacebookInvitee.FacebookRsvpStatus;
import com.yatrix.activity.store.mongo.domain.Comment;
import com.yatrix.activity.store.mongo.domain.Comment.COMMENTTYPE;
import com.yatrix.activity.store.mongo.domain.Message;
import com.yatrix.activity.store.mongo.domain.Participant;
import com.yatrix.activity.store.mongo.domain.Participant.RSVPSTATUS;
import com.yatrix.activity.store.mongo.domain.Participant.TYPE;
import com.yatrix.activity.store.mongo.domain.PostMessage;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.UserActivity.EVENT_STATUS;
import com.yatrix.activity.store.mongo.domain.Venue;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;



public class EventMapper {

	private static final Logger logger = LoggerFactory.getLogger(EventMapper.class);
	public static final String TAG_SEPERATOR= ",";
	private static final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
	
	
	public static UserActivity toUserActivity(EventDto event, ProfileService profileService){
		isvalidEvent(event);
		UserActivity userActivity= new UserActivity();
		userActivity.setCreatedTimeStamp(Calendar.getInstance().getTime());
		if (event.getAccess().equalsIgnoreCase(Message.VISIBILITY.PRIVATE.toString())) {
			userActivity.setVisibility(Message.VISIBILITY.PRIVATE);
		} else if (event.getAccess().equalsIgnoreCase(Message.VISIBILITY.PUBLIC.toString())) {
			userActivity.setVisibility(Message.VISIBILITY.PUBLIC);
		} else if (event.getAccess().equalsIgnoreCase(Message.VISIBILITY.FRIENDSONLY.toString())) {
			userActivity.setVisibility(Message.VISIBILITY.FRIENDSONLY);
		}
		else{
			userActivity.setVisibility(Message.VISIBILITY.ME);
		}
		if(!StringUtils.isEmpty(event.getTitle())){
			userActivity.setTitle(event.getTitle());
		}
		String tags=event.getTags();
		if(!StringUtils.isEmpty(tags)){
			userActivity.setTags(Arrays.asList(tags.split(TAG_SEPERATOR)));
		}
		userActivity.setStatus(EVENT_STATUS.PENDING);
		userActivity.setOriginatorUserId(event.getFrom());
		userActivity.setDescription(event.getDescription());
		userActivity.setLocation(event.getLocation());
		userActivity.setFormattedAddress(event.getFormattedAddress());
		userActivity.setLocationLat(event.getLocationLat());
		userActivity.setLocationLng(event.getLocationLng()); 
		userActivity.setCategoryId(event.getCategoryId());
		userActivity.setSubCategory(event.getSubCategoryId());
		userActivity.setPlace(event.getPlace());
		// split the invitee list
		List<String> participants = new ArrayList<String>();
		participants = Arrays.asList(event.getTo().split(TAG_SEPERATOR));
		userActivity.setParticipants(participants);
		List<Participant> actParts= new ArrayList<Participant>();
		for(String participantId: participants ){
			Participant p = new Participant();
			p.setStatus(RSVPSTATUS.NOT_REPLIED);
			p.setUserType(TYPE.FB);
			p.setInviteeName(profileService.getByUserId(participantId).getName());  
			p.setUserId(participantId);
			actParts.add(p);
			
		}
		
		List<String> appParticipants = new ArrayList<String>();
		appParticipants = Arrays.asList(event.getToAppUsers().split(TAG_SEPERATOR));
		userActivity.setAppParticipants(appParticipants);
		for(String participantId: participants ){
			Participant p = new Participant();
			p.setStatus(RSVPSTATUS.NOT_REPLIED);
			p.setUserType(TYPE.APP);
			p.setInviteeName(profileService.getByUserId(participantId).getName());
			p.setUserId(participantId);
			actParts.add(p);
		}
		userActivity.getActivityParticipants().put(RSVPSTATUS.NOT_REPLIED, actParts);
		Date startTime = null;
		Date endTime = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
		try {
			startTime = format.parse(event.getFromdate() + " " + event.getFromtime());
			endTime = format.parse(event.getTodate() + " " + event.getTotime());
		} catch (ParseException e) {
			logger.error("Event start/end date format is incorrect");
			throw new IllegalStateException("Activity start/end date format is incorrect", e);
		}
		
		userActivity.setParticipants(participants);
		userActivity.setAppParticipants(appParticipants);
		userActivity.setStartTime(startTime);
		userActivity.setEndTime(endTime);
		PostMessage messageposted = new PostMessage();
		messageposted.setAuthorId(event.getFrom());
		messageposted.setAuthorFullName(event.getAuthorFullName());
		messageposted.setMessage(event.getMessage());
		userActivity.setMessageposted(messageposted);
		return userActivity;
	}
	
	
	public static UserEvent toCreateUserEventObject(EventDto event, ProfileService profileService, String fromUserType){
		isvalidEvent(event);
		UserEvent userActivity= new UserEvent();
		
		userActivity.setFromUserType(fromUserType);
		userActivity.setCreatedTimeStamp(Calendar.getInstance().getTime().getTime());
		if (event.getAccess().equalsIgnoreCase(Message.VISIBILITY.PRIVATE.toString())) {
			userActivity.setVisibility(Message.VISIBILITY.PRIVATE);
		} else if (event.getAccess().equalsIgnoreCase(Message.VISIBILITY.PUBLIC.toString())) {
			userActivity.setVisibility(Message.VISIBILITY.PUBLIC);
		} else if (event.getAccess().equalsIgnoreCase(Message.VISIBILITY.FRIENDSONLY.toString())) {
			userActivity.setVisibility(Message.VISIBILITY.FRIENDSONLY);
		}
		else{
			userActivity.setVisibility(Message.VISIBILITY.ME);
		}
		if(!StringUtils.isEmpty(event.getTitle())){
			userActivity.setTitle(event.getTitle());
		}
		else{
			userActivity.setTitle(event.getMessage());
		}
		String tags=event.getTags();
		userActivity.setStatus(UserEvent.EVENT_STATUS.PENDING);
		userActivity.setOriginatorUserId(event.getFrom());
		userActivity.setDescription(event.getDescription());
		Venue location = new Venue();
		location.setFormattedAddress(event.getLocation());
		location.setLocation(event.getLocation());
		location.setPlace(event.getPlace());
		location.setLatlng(new double[]{Double.valueOf(event.getLocationLat()),Double.valueOf(event.getLocationLng()) });
		if(!StringUtils.isEmpty(tags)){
			location.setTags(Arrays.asList(tags.split(TAG_SEPERATOR)));
		}
		userActivity.setLocation(location);
		userActivity.setCategoryId(event.getCategoryId());
		userActivity.setSubCategory(event.getSubCategoryId());
		// split the invitee list
		List<String> participants = new ArrayList<String>();
		participants = Arrays.asList(event.getTo().split(TAG_SEPERATOR));
		List<Participant> actParts= new ArrayList<Participant>();
		for(String participantId: participants ){
			Participant p = new Participant();
			p.setStatus(RSVPSTATUS.NOT_REPLIED);
			p.setUserType(TYPE.FB);
			if(!StringUtils.isEmpty(participantId)){
				p.setInviteeName(profileService.getByUserId(participantId).getName());
			}
			p.setUserId(participantId);
			actParts.add(p);
			userActivity.addInvitedId(p);
		}
		List<String> appParticipants = new ArrayList<String>();
		if(!StringUtils.isEmpty(event.getToAppUsers())){
			appParticipants = Arrays.asList(event.getToAppUsers().split(TAG_SEPERATOR));
			for(String participantId: appParticipants ){
				Participant p = new Participant();
				p.setStatus(RSVPSTATUS.NOT_REPLIED);
				p.setUserType(TYPE.APP);
				if(!StringUtils.isEmpty(participantId)){
					p.setInviteeName(profileService.getByUserId(participantId).getName());
				}
				p.setUserId(participantId);
				actParts.add(p);
				userActivity.addInvitedId(p);
			}
		}
		Date startTime = null;
		Date endTime = null;
		try {
			startTime = format.parse(event.getFromdate() + " " + event.getFromtime());
			endTime = format.parse(event.getTodate() + " " + event.getTotime());
		} catch (ParseException e) {
			logger.error("Event start/end date format is incorrect");
			throw new IllegalStateException("Activity start/end date format is incorrect", e);
		}
		//userActivity.setInvitedIds(actParts);
		userActivity.setStartTime(startTime.getTime());
		userActivity.setEndTime(endTime.getTime());
		Comment comment= new Comment();
		comment.setFromId(event.getFrom());
		UserProfile pf=profileService.getByUserId(event.getFrom());
		comment.setFromAuthorName(pf.getFirstName() + " "+ pf.getLastName());
		comment.setMessage(event.getMessage());
		//TODO MAKE IT MORE ROBUST
		//comment.addCommentSync("appCommentID1", COMMENTTYPE.APP,System.currentTimeMillis());
		userActivity.addComment(comment);
		PostMessage messageposted = new PostMessage();
		messageposted.setAuthorId(event.getFrom());
		messageposted.setAuthorFullName(event.getAuthorFullName());
		messageposted.setMessage(event.getMessage());
		userActivity.addPostedMessage(messageposted);
		
		return userActivity;
	}
	
	/**
	 * TODO: Fix this method completely. Its all broken.
	 * @param event
	 * @param profileService
	 * @return
	 */
	public static EventDto convertToEventDto(UserActivity event, ProfileService profileService){
		EventDto dto = new EventDto();
		dto.setId(event.getId());
		dto.setCategoryName(event.getDisplayName());
		dto.setAuthorFullName(event.getAuthorName());
		dto.setDescription(event.getDescription());
		dto.setSubCategoryId(event.getSubCategory());
		dto.setTitle(event.getTitle());
		dto.setStartDate(event.getStartTime());
		dto.setEndDate(event.getEndTime());
		dto.setLocation(event.getLocation()); 
		dto.setFormattedAddress(event.getFormattedAddress());
		dto.setLocationLat(event.getLocationLat());
		dto.setLocationLng(event.getLocationLng()); 
		if(event.getMessageposted()!=null) {
			PostMessage message = event.getMessageposted();
			dto.setMessage(message.getMessage());
		}
		dto.setFacebookAccepted(event.getFacebookAccepted());
		List<String> participants = event.getParticipants();
		StringBuffer temp= new StringBuffer("");
		int index=1;
		for(String s :participants) {
			UserProfile pName = profileService.getByUserId(s);
			if(index!=participants.size()) {
				temp.append(pName == null ? s : pName.getName()).append(",");
			}else {
				temp.append(pName == null ? s : pName.getName());
			}
			index++;
		}
		dto.setTo(temp.toString());

		return dto;
	}
	
	
	/**
	 * TODO: Fix this method completely. Its all broken.
	 * @param event
	 * @param profileService
	 * @return
	 */
	public static EventDto convertToEventDto(UserEvent event){
		EventDto dto = new EventDto();
		dto.setId(event.getId());
		dto.setCategoryName(event.getDisplayName());
		dto.setAuthorFullName(event.getAuthorName());
		dto.setDescription(event.getDescription());
		dto.setSubCategoryId(event.getSubCategory());
		dto.setStartDate(new Date(event.getStartTime()));
		dto.setTitle(event.getTitle());
		dto.setEndDate(new Date(event.getEndTime()));
		dto.setLocation(event.getLocation().getLocation()); 
		dto.setFormattedAddress(event.getLocation().getFormattedAddress());
		dto.setLocationLat(Double.toString(event.getLocation().getLatlng()[0]));
		dto.setLocationLng(Double.toString(event.getLocation().getLatlng()[1])); 
		if(event.getPostedMessage().size()>0) {
			PostMessage message = event.getPostedMessage().get(0);
			dto.setMessage(message.getMessage());
		}
		List<FacebookInvitee> facebookInvitees= new ArrayList<FacebookInvitee>();
		List<Invitees> invitees= new ArrayList<Invitees>();
		List<Participant> participants = event.getInvitedIds();
		StringBuffer temp= new StringBuffer("");
		int index=0;
		for(Participant s :participants) {
			String pName = s.getInviteeName();
			if(!StringUtils.isEmpty(pName)){
				//Second one in the list add it. 
				//Last one or first one dont add it.
				if(index>0 && index<participants.size()-1){
					temp.append(",");
				}
				temp.append(pName);
				
			}
			
			//TODO: Remove the invocation.
			if(s.getStatus().equals(RSVPSTATUS.ATTENDING) && s.getUserType().equals(TYPE.FB)){
				facebookInvitees.add(new FacebookInvitee(s.getUserId(), s.getInviteeName(), FacebookRsvpStatus.ATTENDING));
			}
			invitees.add(new Invitees(s.getUserId(), s.getInviteeName(), s.getStatus().toString(), s.getUserType().toString()));
			index++;
		}
		 
		dto.setFacebookAccepted(facebookInvitees);
		dto.setRsvpStatusses(invitees);
		dto.setTo(temp.toString());
		dto.setAppComments(event.getAppComments());
		
		return dto;
	}
	
	
	
	
	
	private static boolean isvalidEvent(EventDto event) {
		
		logger.info(event.toString());
		if (StringUtils.isEmpty(event.getCategoryId()) || event.getCategoryId().equalsIgnoreCase("select")) {
			logger.error("Activity category value is empty " + event.toString());
			throw new IllegalStateException("category.empty");

		}
		if (StringUtils.isEmpty(event.getFrom())) {
			logger.error("User name is empty " + event.toString());
			throw new IllegalStateException("user.empty");
		}
		if ( StringUtils.isEmpty(event.getTo()) && StringUtils.isEmpty(event.getToAppUsers()) ) {
			logger.error("Invitee user list is empty " + event.toString());
			throw new IllegalStateException("invitee.empty");
		}
		if (StringUtils.isEmpty(event.getLocation())) {
			logger.error("Event location value is empty");
			throw new IllegalStateException("location.empty");
		}
		if (StringUtils.isEmpty(event.getFromdate()) || StringUtils.isEmpty(event.getTodate())) {
			logger.error("Event start/end date format is incorrect");
			throw new IllegalStateException("date.error");			
		}
		if (StringUtils.isEmpty(event.getFromtime()) && StringUtils.isEmpty(event.getTotime())) {
			logger.error("Event start/end date format is incorrect");
			throw new IllegalStateException("date.error");			
		}
		
		if (StringUtils.isEmpty(event.getDescription())) {
			logger.error("Description value is empty");
			throw new IllegalStateException("message.empty");

		}
		return true;
	}
}
