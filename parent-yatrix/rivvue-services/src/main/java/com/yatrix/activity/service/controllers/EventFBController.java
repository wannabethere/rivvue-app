package com.yatrix.activity.service.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.yatrix.activity.hystrix.fb.command.IFacebookJoinEventCommand;
import com.yatrix.activity.hystrix.fb.command.impl.FacebookEventCreateCommand;
import com.yatrix.activity.hystrix.fb.command.impl.FacebookEventFeedCommand;
import com.yatrix.activity.hystrix.fb.command.impl.FacebookEventJoinCommand;
import com.yatrix.activity.service.dto.AjaxResponse;
import com.yatrix.activity.service.dto.EventDto;
import com.yatrix.activity.service.dto.ProfileListDto;
import com.yatrix.activity.service.dto.UserDto;
import com.yatrix.activity.service.utils.EventMapper;
import com.yatrix.activity.service.utils.UserMapper;
import com.yatrix.activity.store.exception.ActivityDBException;
import com.yatrix.activity.store.fb.domain.FacebookInvitee;
import com.yatrix.activity.store.mongo.domain.Comment;
import com.yatrix.activity.store.mongo.domain.Participant;
import com.yatrix.activity.store.mongo.domain.Participant.TYPE;
import com.yatrix.activity.store.mongo.domain.ActivityComment;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.Participant.RSVPSTATUS;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;
import com.yatrix.activity.store.mongo.service.impl.UserEventsService;
@Controller
@RequestMapping("/myactivities")
public class EventFBController {


	private static final Logger logger = LoggerFactory.getLogger(EventFBController.class);
	// Create an instance of SimpleDateFormat used for formatting 
	// the string representation of date (month/day/year)
	private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	@Autowired
	private UserAccountService userAccountRepository;
	
	@Autowired
	private UserEventsService eventsService;

	@Autowired
	private ProfileService profileService;
	@Autowired
	private ConnectionService userSocialConnectionService;
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;
	
	
	@Autowired
	private UserAccountService userAccountService;

	/*
	 * Given useractvityId, search the db and return
	 * @Param
	 */
	@RequestMapping(
			value = "{userid}/event/{useractivityId}",
			method = RequestMethod.GET)
	public String getUserActivity(@PathVariable String userid, @PathVariable String useractivityId, ModelMap model) throws ActivityDBException {
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		Log.info("Auth Name: "+ authname);
		UserAccount acct=userAccountRepository.getUserAccount(userid);
		if(acct==null){
			acct = userAccountService.getUserAccount(authname);
			if(acct==null){
				return "access/login";
			}
		}
		UserEvent event = eventsService.getActivity(useractivityId);
		List<Comment> appCommentsNotPosted = new ArrayList<Comment>();
		
		boolean displayJoinButton = true;
		boolean isInviteeToEvent = false;

		if (event == null) {
			ActivityDBException noactivity = new ActivityDBException("no user event found ");
			throw noactivity;
		}

		try {
			UserProfile pf=profileService.getByUserId(StringUtils.isEmpty(acct.getFacebookId())?acct.getUserId():acct.getFacebookId()); 
			event.setDisplayName(acct.getUserId());
			event.setAuthorName(pf.getFirstName() + " " + pf.getLastName());
			//Will fix the not synced comments later.
			/*if(event.getAppComments() != null && event.getAppComments().size() > 0){
				for(Comment comment : event.getAppComments()){
					if("-1".equalsIgnoreCase(comment.getId())){
						appCommentsNotPosted.add(comment);
					}
				}
			}*/
		} catch(Exception e) {
			logger.error(e.getMessage());
			event.setDisplayName(event.getOriginatorUserId()); 
		}

		if(event.getOriginatorUserId().equalsIgnoreCase(userid) ||alreadyInvited(userid, event)  
				|| isJoinThruFacebook(userid, event)){
			displayJoinButton = false;
			isInviteeToEvent = true;
		}

		ProfileListDto userListDto = new ProfileListDto();
		List<UserDto> users = new ArrayList<UserDto>();
		
		//This is the facebook Id only check. Later we have
		List<UserProfile> friends=profileService.getMyContacts((!StringUtils.isEmpty(acct.getFacebookId()))?acct.getFacebookId():acct.getUserId());
		if(friends!=null){
			users.addAll(UserMapper.mapUserProfile(friends));
		}
		userListDto.setProfiles(users);
		model.put("friends", userListDto);
		model.addAttribute("displayJoin", displayJoinButton);
		//model.addAttribute("isInviteeToEvent", isInviteeToEvent);
		model.addAttribute("event", event);
		model.addAttribute("authname", authname);
		model.addAttribute("comments", appCommentsNotPosted);

		return isInviteeToEvent ? "events/events2" :"events/events";
	}
	
	@RequestMapping(value="/{userId}", produces="application/json" ,method=RequestMethod.GET)
	public String  getAllEvents(@PathVariable String userId,ModelMap model) {
		//Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use cookie.
		logger.debug("finding all events for user"+userId);
		List<UserEvent> events =  eventsService.getMyActivities(userId);
		List<EventDto> eventList=new ArrayList<EventDto>();
		for(UserEvent event: events){
			eventList.add(EventMapper.convertToEventDto(event));
		}
		logger.debug("number of events found :"+eventList.size());
		model.addAttribute("events", eventList);
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		return "events/postlogin";
	}
	
	@RequestMapping(value = "/{userid}", produces = "application/json",method = RequestMethod.POST)
	public String createUserEvent(@PathVariable String userid, EventDto dto, ModelMap model) throws InterruptedException, ExecutionException {
			String authname = SecurityContextHolder.getContext().getAuthentication().getName();
			Log.info("Auth Name: "+ authname);
			UserAccount acct=userAccountRepository.getUserAccount(userid);
			UserEvent event=EventMapper.toCreateUserEventObject(dto, profileService, StringUtils.isEmpty(acct.getFacebookId())?"APP":"FB");
			event=eventsService.createUserEvent(event);
			if(!StringUtils.isEmpty(acct.getFacebookId())){
				FacebookEventCreateCommand createCommand = new FacebookEventCreateCommand(event, acct.getFacebookId());
				createCommand.setConnectionFactoryLocator(connectionFactoryLocator);
				createCommand.setEventsService(eventsService);
				createCommand.setUserSocialConnectionService(userSocialConnectionService);
				createCommand.executeFacebookEventFeed();
			}
			
			model.addAttribute("activityId",event.getId());
			model.addAttribute("status","Successully Created: ");
			return "redirect:/calendarevents/"+userid;
	}
	
	
	

	/**
	 * http://localhost:8080/store/calendarevents/publicevents?state=CA
	 * http://localhost:8080/store/calendarevents/user581/invitedevents
	 * @param state
	 * @param model
	 * @return
	 */

	@RequestMapping(value="/publicevents", produces="application/json" ,method=RequestMethod.GET)
	public String  getAllPublicEvents(@RequestParam String state, ModelMap model) {
		//Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use cookie.
		logger.debug("finding all public events for user" + state);
		List<UserEvent> events =  eventsService.getActivitiesByState(state);
		List<EventDto> eventList=new ArrayList<EventDto>();
		for(UserEvent event: events){
			eventList.add(EventMapper.convertToEventDto(event));
		}
		logger.debug("number of events found :"+eventList.size());
		model.addAttribute("events", eventList);
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		return "events/postlogin";
	}

	@RequestMapping(value="/{userId}/invitedevents", produces="application/json" ,method=RequestMethod.GET)
	public String  getEventsIAmInvited(@PathVariable String userId, ModelMap model) {
		//Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use cookie.
		logger.debug("finding all invited events for user" + userId);
		UserAccount userAccount = userAccountRepository.getUserAccount(userId);
		if(userAccount == null){
			userAccount = userAccountRepository.getUserAccountByUserName(userId);
			
			if(userAccount == null){
				return "access/login";
			}
		}
		List<UserEvent> events=eventsService.getInvitedActivities(userId);
		List<EventDto> eventList=new ArrayList<EventDto>();
		for(UserEvent event: events){
			eventList.add(EventMapper.convertToEventDto(event));
		}
		logger.debug("number of events found :"+eventList.size());
		model.addAttribute("events", eventList);
		model.addAttribute("authname", userId);
		return "events/postlogin";
	}
	
	

	@RequestMapping(value="/{userId}/{eventId}/postFBMessage", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody Comment postFeedToFacebook(@PathVariable String userId, @PathVariable String eventId, @RequestParam String message, ModelMap model) throws Exception {
		UserAccount userAccount = userAccountService.getUserAccountByUserName(userId);
		if(userAccount == null){
			userAccount = userAccountService.getUserAccount(userId);
			if(userAccount == null){
				return null;
			}
		}
		
		UserEvent event=eventsService.getActivity(eventId);
		Comment comment= new Comment();
		comment.setFromId(userId);
		comment.setMessage(message);
		comment.setFromAuthorName(userAccount.getFirstName() + "" + userAccount.getLastName());
		comment.setCreatedTime(System.currentTimeMillis());
		event.addComment(comment);
		FacebookEventFeedCommand fbPostCommand = new FacebookEventFeedCommand(event, userId, comment);
		fbPostCommand.setConnectionFactoryLocator(connectionFactoryLocator);
		fbPostCommand.setEventsService(eventsService);
		fbPostCommand.setUserSocialConnectionService(userSocialConnectionService);
		fbPostCommand.executeFacebookEventFeed();
		return comment;
	}
	
	/**
	 * User is adding himself to a public event. THis means that user is added and then accepting as well.
	 * @param userId
	 * @param eventId
	 * @param userType
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{userId}/{eventId}/joinEvent", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody AjaxResponse joinEvent(@PathVariable String userId, @PathVariable String eventId,@RequestParam String userType, ModelMap model) throws Exception {
		
		UserAccount userAccount = userAccountService.getUserAccountByUserName(userId);
		if(userAccount == null){
			userAccount = userAccountService.getUserAccount(userId);
			if(userAccount == null){
				return  new AjaxResponse("User Not Available");
			}
		}
		UserEvent event=eventsService.getActivity(eventId);
		
		UserProfile pf = profileService.getByUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId());
		Participant p = new Participant();
		p.setUserId(userId);
		p.setInviteeName(pf.getName());
		p.setLupd(System.currentTimeMillis());
		p.setStatus(RSVPSTATUS.ATTENDING);
		if(pf.getSrcprofileType().equals(PROFILETYPE.FB)){
			p.setUserType(TYPE.FB);
		}
		else{
			p.setUserType(TYPE.APP);
		}
		boolean addUser=true;
		FacebookEventJoinCommand command = new FacebookEventJoinCommand(event, p,addUser);
		//Running the request in asynchronous mode.
		command.setConnectionFactoryLocator(connectionFactoryLocator);
		command.setEventsService(eventsService);
		command.setUserSocialConnectionService(userSocialConnectionService);
		command.executeFacebookJoinEvent();
		//We will update the result later. 
		return new AjaxResponse("User join request Successful");
	}
	
	@RequestMapping(value="/{userId}/{eventId}/rejectEvent", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody AjaxResponse rejectEvent(@PathVariable String userId, @PathVariable String eventId, ModelMap model) throws Exception {
		UserAccount userAccount = userAccountService.getUserAccountByUserName(userId);
		if(userAccount == null){
			userAccount = userAccountService.getUserAccount(userId);
			if(userAccount == null){
				return  new AjaxResponse("User Not Available");
			}
		}
		UserEvent event=eventsService.getActivity(eventId);
		UserProfile pf = profileService.getByUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId());
		Participant p = new Participant();
		p.setUserId(userId);
		p.setInviteeName(pf.getName());
		p.setLupd(System.currentTimeMillis());
		p.setStatus(RSVPSTATUS.DECLINED);
		if(pf.getSrcprofileType().equals(PROFILETYPE.FB)){
			p.setUserType(TYPE.FB);
		}
		else{
			p.setUserType(TYPE.APP);
		}
		FacebookEventJoinCommand command = new FacebookEventJoinCommand(event, p,false);
		//Running the request in asynchronous mode.
		command.setConnectionFactoryLocator(connectionFactoryLocator);
		command.setEventsService(eventsService);
		command.setUserSocialConnectionService(userSocialConnectionService);
		command.executeFacebookJoinEvent();
		//We will update the result later. 
		return new AjaxResponse("User reject Successful");
	}
	
	@RequestMapping(value="/{userId}/{eventId}/maybeEvent", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody AjaxResponse maybeEvent(@PathVariable String userId, @PathVariable String eventId, ModelMap model) throws Exception {

		UserAccount userAccount = userAccountService.getUserAccountByUserName(userId);
		if(userAccount == null){
			userAccount = userAccountService.getUserAccount(userId);
			if(userAccount == null){
				return  new AjaxResponse("User Not Available");
			}
		}
		UserEvent event=eventsService.getActivity(eventId);
		UserProfile pf = profileService.getByUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId());
		Participant p = new Participant();
		p.setUserId(userId);
		p.setInviteeName(pf.getName());
		p.setLupd(System.currentTimeMillis());
		p.setStatus(RSVPSTATUS.MAYBE);
		if(pf.getSrcprofileType().equals(PROFILETYPE.FB)){
			p.setUserType(TYPE.FB);
		}
		else{
			p.setUserType(TYPE.APP);
		}
		FacebookEventJoinCommand command = new FacebookEventJoinCommand(event, p,false);
		//Running the request in asynchronous mode.
		command.setConnectionFactoryLocator(connectionFactoryLocator);
		command.setEventsService(eventsService);
		command.setUserSocialConnectionService(userSocialConnectionService);
		command.executeFacebookJoinEvent();
		//We will update the result later. 
		return new AjaxResponse("Tentatively accepted");
	}
	
	
	@RequestMapping(value="/{userId}/{eventId}/acceptedEvent", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody AjaxResponse acceptedEvent(@PathVariable String userId, @PathVariable String eventId, ModelMap model) throws Exception {

		UserAccount userAccount = userAccountService.getUserAccountByUserName(userId);
		if(userAccount == null){
			userAccount = userAccountService.getUserAccount(userId);
			if(userAccount == null){
				return  new AjaxResponse("User Not Available");
			}
		}
		UserEvent event=eventsService.getActivity(eventId);
		UserProfile pf = profileService.getByUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId());
		Participant p = new Participant();
		p.setUserId(userId);
		p.setInviteeName(pf.getName());
		p.setLupd(System.currentTimeMillis());
		p.setStatus(RSVPSTATUS.ATTENDING);
		if(pf.getSrcprofileType().equals(PROFILETYPE.FB)){
			p.setUserType(TYPE.FB);
		}
		else{
			p.setUserType(TYPE.APP);
		}
		FacebookEventJoinCommand command = new FacebookEventJoinCommand(event, p,false);
		//Running the request in asynchronous mode.
		command.setConnectionFactoryLocator(connectionFactoryLocator);
		command.setEventsService(eventsService);
		command.setUserSocialConnectionService(userSocialConnectionService);
		command.executeFacebookJoinEvent();
		//We will update the result later. 
		return new AjaxResponse("Tentatively accepted");
	}
	
	@RequestMapping(value="/{authname}/{eventId}/deleteEvent", produces="application/json", method=RequestMethod.GET)
	public String deleteEvent(@PathVariable String authname, @PathVariable String eventId, ModelMap model) throws Exception {
		try{
			eventsService.deleteUserEvent(eventId);
		}catch(Exception ex){
			logger.error("Error while deleting event", ex);
		}		
		return "redirect:/calendarevents/" + authname;
	}
	
	
	private boolean isJoinThruFacebook(String userId, UserEvent event) {

		UserAccount userAccount = userAccountService.getUserAccountByUserName(userId);

		if(userAccount == null){
			userAccount = userAccountService.getUserAccount(userId);
		}

		System.out.println("User Account: " + userAccount);
		System.out.println("Event: " + event);

		if(userAccount.getFacebookId() == null || event.getAcceptedIds() == null){
			return false;
		}

		for(Participant facebookAccepted : event.getAcceptedIds()){
			if(userAccount.getFacebookId().equalsIgnoreCase(facebookAccepted.getUserId())){
				return true;
			}
		}

		return false;
	}
	
	private boolean alreadyInvited(String userId, UserEvent event){
		//event.getAcceptedIds().contains(userid)
		UserAccount userAccount = userAccountService.getUserAccountByUserName(userId);

		if(userAccount == null){
			userAccount = userAccountService.getUserAccount(userId);
		}

		System.out.println("User Account: " + userAccount);
		System.out.println("Event: " + event);

		if(userAccount.getFacebookId() == null || event.getAcceptedIds() == null){
			return false;
		}

		
		for(Participant facebookAccepted : event.getInvitedIds()){
			if(userAccount.getFacebookId().equalsIgnoreCase(facebookAccepted.getUserId())){
				return true;
			}
		}
		return true;
	}
	
}