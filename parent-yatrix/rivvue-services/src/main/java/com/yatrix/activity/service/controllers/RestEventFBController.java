package com.yatrix.activity.service.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yatrix.activity.hystrix.fb.command.HystrixSocialResult;
import com.yatrix.activity.hystrix.fb.command.impl.FacebookEventCreateCommand;
import com.yatrix.activity.hystrix.fb.command.impl.FacebookEventFeedCommand;
import com.yatrix.activity.hystrix.fb.command.impl.FacebookEventJoinCommand;
import com.yatrix.activity.service.dto.AjaxResponse;
import com.yatrix.activity.service.dto.EventDetailsResponse;
import com.yatrix.activity.service.dto.EventDto;
import com.yatrix.activity.service.dto.EventsResponse;
import com.yatrix.activity.service.dto.Invitees;
import com.yatrix.activity.service.dto.ProfileListDto;
import com.yatrix.activity.service.dto.RatingDetails;
import com.yatrix.activity.service.dto.RatingResponse;
import com.yatrix.activity.service.dto.UserDto;
import com.yatrix.activity.service.dto.WeatherDetails;
import com.yatrix.activity.service.dto.WeatherResponse;
import com.yatrix.activity.service.utils.EventMapper;
import com.yatrix.activity.service.utils.UserMapper;
import com.yatrix.activity.store.exception.ActivityDBException;
import com.yatrix.activity.store.mongo.domain.Comment;
import com.yatrix.activity.store.mongo.domain.Participant;
import com.yatrix.activity.store.mongo.domain.Participant.RSVPSTATUS;
import com.yatrix.activity.store.mongo.domain.Participant.TYPE;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserDraftEvent;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;
import com.yatrix.activity.store.mongo.service.impl.UserEventsService;
@Controller
@RequestMapping("/rest/myactivities")
public class RestEventFBController {


	private static final Logger logger = LoggerFactory.getLogger(RestEventFBController.class);
	// Create an instance of SimpleDateFormat used for formatting 
	// the string representation of date (month/day/year)
	private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	@Autowired
	Environment env;

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
			method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody EventDetailsResponse getUserActivity(@PathVariable String userid, @PathVariable String useractivityId) throws ActivityDBException {

		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		Log.info("Auth Name: "+ authname);
		UserAccount acct=userAccountRepository.getUserAccount(userid);

		if(acct==null){
			acct = userAccountService.getUserAccount(authname);
			if(acct==null){
				return createNotLoggedInEventRespones();
			}
		}

		UserEvent event = eventsService.getActivity(useractivityId);
		List<Comment> appCommentsNotPosted = new ArrayList<Comment>();

		if (event == null) {
			ActivityDBException noactivity = new ActivityDBException("no user event found ");
			throw noactivity;
		}

		try {
			UserProfile pf=profileService.getByUserId(StringUtils.isEmpty(acct.getFacebookId())?acct.getUserId():acct.getFacebookId()); 
			event.setDisplayName(acct.getUserId());
			event.setAuthorName(pf.getFirstName() + " " + pf.getLastName());
			//model.put("userType",pf.getSrcprofileType().toString() );
		} catch(Exception e) {
			logger.error(e.getMessage());
			event.setDisplayName(event.getOriginatorUserId()); 
			//model.put("userType","APP" );
		}

		ProfileListDto userListDto = new ProfileListDto();
		List<UserDto> users = new ArrayList<UserDto>();

		//This is the facebook Id only check. Later we have
		List<UserProfile> friends=profileService.getMyContacts((!StringUtils.isEmpty(acct.getFacebookId()))?acct.getFacebookId():acct.getUserId());
		if(friends!=null){
			users.addAll(UserMapper.mapUserProfile(friends));
		}
		userListDto.setProfiles(users);

		EventDetailsResponse eventDetailsResponse = new EventDetailsResponse();
		eventDetailsResponse.setStatus(200);
		eventDetailsResponse.setFriendsList(userListDto);
		eventDetailsResponse.setEventDto(EventMapper.convertToEventDto(event));
		eventDetailsResponse.setComments(appCommentsNotPosted);

		return eventDetailsResponse;
	}

	/*
	 * Returns the USER ID for Public Search Pages.
	 * Need to figure what to display.
	 * @Param
	 */
	@RequestMapping(value = "/event/{useractivityId}",method = RequestMethod.GET)
	public @ResponseBody EventDetailsResponse getPublicUserActivity( @PathVariable String useractivityId, ModelMap model) throws ActivityDBException {

		UserEvent event = eventsService.getActivity(useractivityId);
		if (event == null) {
			ActivityDBException noactivity = new ActivityDBException("no user event found ");
			throw noactivity;
		}
		List<Comment> appCommentsNotPosted = new ArrayList<Comment>();
		//This is the facebook Id only check. Later we have
		//model.addAttribute("isInviteeToEvent", isInviteeToEvent);
		ProfileListDto userListDto = new ProfileListDto();
		EventDetailsResponse eventDetailsResponse = new EventDetailsResponse();
		eventDetailsResponse.setStatus(200);
		eventDetailsResponse.setFriendsList(userListDto);
		eventDetailsResponse.setEventDto(EventMapper.convertToEventDto(event));
		eventDetailsResponse.setComments(appCommentsNotPosted);

		return eventDetailsResponse;
	}

	/*
	 * Given useractvityId, search the db and return
	 * @Param
	 */
	@RequestMapping(value = "{userid}/eventinvitees/{useractivityId}",method = RequestMethod.GET)
	public @ResponseBody List<Invitees>  getUserEventInvitees(@PathVariable String userid, @PathVariable String useractivityId) throws ActivityDBException {
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		Log.info("Auth Name: "+ authname);
		UserAccount acct=userAccountRepository.getUserAccount(userid);
		if(acct==null){
			acct = userAccountService.getUserAccount(authname);
			if(acct==null){
				throw new ActivityDBException("Authentication failed");
			}
		}
		UserEvent event = eventsService.getActivity(useractivityId);
		if (event == null) {
			ActivityDBException noactivity = new ActivityDBException("no user event found ");
			throw noactivity;
		}
		UserProfile pf=profileService.getByUserId(StringUtils.isEmpty(acct.getFacebookId())?acct.getUserId():acct.getFacebookId());
		List<UserProfile> friends=profileService.getMyContacts((!StringUtils.isEmpty(acct.getFacebookId()))?acct.getFacebookId():acct.getUserId());

		return EventMapper.getEventInvitees(event, acct, pf, friends);
	}

	/**
	 * Returns Weather details
	 * @param userid
	 * @param useractivityId
	 * @return
	 * @throws ActivityDBException
	 */
	@RequestMapping(value = "{userid}/weather",method = RequestMethod.GET)
	public @ResponseBody WeatherResponse  getWeatherDetails(@PathVariable String userid, @RequestParam List<String> events) throws ActivityDBException {

		WeatherResponse weatherResponse = new WeatherResponse();
		WeatherDetails weatherDetails;

		//TODO: Place holders. Replace with original values
		weatherResponse.setStatus(200);

		if(events != null){
			for(String eventId : events){
				weatherDetails = new WeatherDetails();

				weatherDetails.setEventId(eventId);
				weatherDetails.setWeather(new Random().nextInt(50) + ".25*F");

				weatherResponse.addWeatherDetails(weatherDetails);
			}
		}


		return weatherResponse;
	}

	/**
	 * Returns Weather details
	 * @param userid
	 * @param useractivityId
	 * @return
	 * @throws ActivityDBException
	 */
	@RequestMapping(value = "{userid}/rating",method = RequestMethod.GET)
	public @ResponseBody RatingResponse  getRatings(@PathVariable String userid, @RequestParam List<String> events) throws ActivityDBException {

		RatingResponse ratingResponse = new RatingResponse();
		RatingDetails ratingDetails;

		//TODO: Place holders. Replace with original values
		ratingResponse.setStatus(200);

		if(events != null){
			for(String eventId : events){
				ratingDetails = new RatingDetails();

				ratingDetails.setEventId(eventId);
				ratingDetails.setRating("IMDB Rating " + new Random().nextInt(10) );

				ratingResponse.addRatingDetails(ratingDetails);
			}
		}


		return ratingResponse;
	}

	@RequestMapping(value="/{userId}", produces="application/json" ,method=RequestMethod.GET)
	public @ResponseBody EventsResponse getAllEvents(@PathVariable String userId, @RequestParam Long start, @RequestParam Long end) {

		//Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use cookie.
		logger.debug("finding all events for user"+userId);

		EventsResponse eventsResponse = new EventsResponse();
		List<UserEvent> events =  eventsService.getMyActivities(userId, start, end);
		List<EventDto> eventList=new ArrayList<EventDto>();

		for(UserEvent event: events){
			//TODO: We might need to convert only few properties. check with mobile team.
			eventList.add(EventMapper.convertToEventDto(event));
		}

		logger.debug("number of events found :"+eventList.size());

		eventsResponse.setStatus(200);
		eventsResponse.setEvents(eventList);

		return eventsResponse;
	}

	@RequestMapping(value = "/{userid}", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody EventDetailsResponse createUserEvent(@PathVariable String userid, @RequestBody EventDto dto) throws InterruptedException, ExecutionException {

		UserAccount acct = userAccountRepository.getUserAccount(userid);
		String author = acct.getFacebookId() == null ? userid : acct.getFacebookId();
		UserEvent event = EventMapper.toCreateUserEventObject(dto, profileService, StringUtils.isEmpty(acct.getFacebookId())?"APP":"FB", author);
		event = eventsService.createUserEvent(event);

		if(!StringUtils.isEmpty(acct.getFacebookId())){
			FacebookEventCreateCommand createCommand = new FacebookEventCreateCommand(event, acct.getFacebookId());
			createCommand.setConnectionFactoryLocator(connectionFactoryLocator);
			createCommand.setEventsService(eventsService);
			createCommand.setUserSocialConnectionService(userSocialConnectionService);
			createCommand.executeFacebookEventFeed();
		}   

		EventDetailsResponse eventDetailsResponse = new EventDetailsResponse();
		eventDetailsResponse.setStatus(200);
		dto.setId(event.getId());
		eventDetailsResponse.setEventDto(dto);

		return eventDetailsResponse;
	}


	@RequestMapping(value = "/createdraftevent/{userid}", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody EventDetailsResponse createUserDraftEvent(@PathVariable String userid, @RequestBody EventDto dto) throws InterruptedException, ExecutionException {

		UserAccount acct = userAccountRepository.getUserAccount(userid);
		String author = acct.getFacebookId() == null ? userid : acct.getFacebookId();

		UserDraftEvent event=EventMapper.toCreateUserDraftEventObject(dto, profileService, StringUtils.isEmpty(acct.getFacebookId())?"APP":"FB",
				author);

		event = eventsService.createUserDraftEvent(event);

		// Add a message with URL to our app.

		Comment comment= new Comment();
		UserProfile pf=profileService.getByUserId(author);

		comment.setFromId(dto.getFrom());
		comment.setFromAuthorName(pf.getName());
		comment.setMessage("For details on the event: " + env.getProperty("application.url") + "/activities/" + userid + "/" + event.getId());

		event.addComment(comment);

		eventsService.updateUserDraftEvent(event);

		EventDetailsResponse eventDetailsResponse = new EventDetailsResponse();
		eventDetailsResponse.setStatus(200);
		dto.setId(event.getId());
		eventDetailsResponse.setEventDto(dto);

		return eventDetailsResponse;
	}

	@RequestMapping(value = "/addInvitees/{draftEventId}/{userid}", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody EventDetailsResponse addInvitees(@PathVariable String userid, @PathVariable String draftEventId, @RequestParam String toAppUsers,
			@RequestParam String toFBUsers) throws InterruptedException, ExecutionException {

		EventDetailsResponse eventDetailsResponse = new EventDetailsResponse();

		UserDraftEvent draftEvent = eventsService.getUserDraftEvent(draftEventId);
		
		if(draftEvent == null){
			eventDetailsResponse.setMessage("Draft event not found with id " + draftEventId);
		} else {
			draftEvent = EventMapper.addInviteesToDraftEvent(profileService, draftEvent, toFBUsers, toAppUsers);
			eventsService.updateUserDraftEvent(draftEvent);
			eventDetailsResponse.setMessage("Added invitees to draft event");
			eventDetailsResponse.setStatus(200);
		}

		return eventDetailsResponse;
	}
	
	@RequestMapping(value = "/lookingViewable/{draftEventId}/{userid}", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody EventDetailsResponse addLookingViewable(@PathVariable String userid, @PathVariable String draftEventId, @RequestParam String lookingViewable) throws InterruptedException, ExecutionException {

		EventDetailsResponse eventDetailsResponse = new EventDetailsResponse();

		UserDraftEvent draftEvent = eventsService.getUserDraftEvent(draftEventId);
		
		if(draftEvent == null){
			eventDetailsResponse.setMessage("Draft event not found with id " + draftEventId);
		} else {
			draftEvent = EventMapper.addLookingViewable(draftEvent, lookingViewable);
			eventsService.updateUserDraftEvent(draftEvent);
			eventDetailsResponse.setMessage("Looking viewable updated successfully");
			eventDetailsResponse.setStatus(200);
			//eventDetailsResponse.setEventDto(draftEvent);
		}

		return eventDetailsResponse;
	}
	
	@RequestMapping(value = "/createEventFromDraft/{draftEventId}/{userid}", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody EventDetailsResponse createEventFromDraft(@PathVariable String userid, @PathVariable String draftEventId) throws InterruptedException, ExecutionException {

		EventDetailsResponse eventDetailsResponse = new EventDetailsResponse();

		UserAccount acct=userAccountRepository.getUserAccount(userid);
		UserDraftEvent draftEvent = eventsService.getUserDraftEvent(draftEventId);
		
		if(draftEvent == null){
			eventDetailsResponse.setMessage("Draft event not found with id " + draftEventId);
		} else {
			UserEvent event = new UserEvent();
			String[] ignoreProperties = {"id"};
			
			BeanUtils.copyProperties(draftEvent, event, ignoreProperties);
			
			event = eventsService.createUserEvent(event);
			
			if(!StringUtils.isEmpty(acct.getFacebookId())){
				FacebookEventCreateCommand createCommand = new FacebookEventCreateCommand(event, acct.getFacebookId());
				createCommand.setConnectionFactoryLocator(connectionFactoryLocator);
				createCommand.setEventsService(eventsService);
				createCommand.setUserSocialConnectionService(userSocialConnectionService);
				createCommand.executeFacebookEventFeed();
			} 
			
			eventDetailsResponse.setMessage("User Event Successfully created.");
			eventDetailsResponse.setEventDto(EventMapper.convertToEventDto(event));

		}
		
		return eventDetailsResponse;
	}


	/**
	 * http://localhost:8080/store/calendarevents/publicevents?state=CA
	 * http://localhost:8080/store/calendarevents/user581/invitedevents
	 * @param state
	 * @return
	 */

	@RequestMapping(value="/publicevents", produces="application/json" ,method=RequestMethod.GET)
	public @ResponseBody EventsResponse  getAllPublicEvents(@RequestParam String state) {
		//Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use cookie.
		logger.debug("finding all public events for user" + state);

		List<UserEvent> events =  eventsService.getActivitiesByState(state);
		List<EventDto> eventList = new ArrayList<EventDto>();

		for(UserEvent event: events){
			eventList.add(EventMapper.convertToEventDto(event));
		}

		logger.debug("number of events found :"+eventList.size());

		//model.addAttribute("events", eventList);
		//model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		EventsResponse eventsResponse = new EventsResponse();
		eventsResponse.setStatus(200);
		eventsResponse.setEvents(eventList);

		return eventsResponse;
	}

	@RequestMapping(value="/{userId}/invitedevents", produces="application/json" ,method=RequestMethod.GET)
	public @ResponseBody EventsResponse  getEventsIAmInvited(@PathVariable String userId) {
		//Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use cookie.
		logger.debug("finding all invited events for user" + userId);
		UserAccount userAccount = userAccountRepository.getUserAccount(userId);
		List<String> userIds = new ArrayList<String>();

		if(userAccount == null){
			userAccount = userAccountRepository.getUserAccountByUserName(userId);

			if(userAccount == null){
				return createNotLoggedInEventsRespones();
			}
		}

		if(!StringUtils.isEmpty(userAccount.getFacebookId())){
			userIds.add(userAccount.getFacebookId());
		}

		userIds.add(userId);

		List<UserEvent> events=eventsService.getInvitedActivities(userIds);
		List<EventDto> eventList=new ArrayList<EventDto>();
		for(UserEvent event: events){
			eventList.add(EventMapper.convertToEventDto(event));
		}

		logger.debug("number of events found :"+eventList.size());

		EventsResponse eventsResponse = new EventsResponse();
		eventsResponse.setStatus(200);
		eventsResponse.setEvents(eventList);

		//model.addAttribute("events", eventList);
		//model.addAttribute("authname", userId);

		return eventsResponse;
	}

	@RequestMapping(value="/{userId}/{eventId}/postFBMessage", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody Comment postFeedToFacebook(@PathVariable String userId, @PathVariable String eventId, @RequestParam String message) throws Exception {
		UserAccount userAccount = userAccountService.getUserAccountByUserName(userId);
		if(userAccount == null){
			userAccount = userAccountService.getUserAccount(userId);
			if(userAccount == null){
				return null;
			}
		}
		UserProfile pf=profileService.getByUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId());

		UserEvent event=eventsService.getActivity(eventId);
		Comment comment= new Comment();
		comment.setFromId(userId);
		comment.setMessage(message);
		comment.setFromAuthorName(pf.getFirstName() + " " + pf.getLastName());
		comment.setCreatedTime(System.currentTimeMillis());
		event.addComment(comment);
		//Instead of passing the FB ID pass the Participant information.

		FacebookEventFeedCommand fbPostCommand = new FacebookEventFeedCommand(event, userId,userAccount.getFacebookId(), comment);
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
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{userId}/{eventId}/joinEvent", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody AjaxResponse joinEvent(@PathVariable String userId, @PathVariable String eventId,@RequestParam String userType) throws Exception {

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
		p.setUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId());
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
	public @ResponseBody AjaxResponse rejectEvent(@PathVariable String userId, @PathVariable String eventId) throws Exception {
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
		p.setUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId());
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
		Future<HystrixSocialResult> result = command.executeFacebookJoinEvent();
		HystrixSocialResult result1 = result.get();
		System.out.println(result1.getMessage());;
		//We will update the result later. 
		return new AjaxResponse("User reject Successful");
	}

	@RequestMapping(value="/{userId}/{eventId}/maybeEvent", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody AjaxResponse maybeEvent(@PathVariable String userId, @PathVariable String eventId) throws Exception {

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
		p.setUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId());
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
	public @ResponseBody AjaxResponse acceptedEvent(@PathVariable String userId, @PathVariable String eventId) throws Exception {

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
		p.setUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId());
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
	public @ResponseBody AjaxResponse deleteEvent(@PathVariable String authname, @PathVariable String eventId) throws Exception {
		try{
			eventsService.deleteUserEvent(eventId);
		}catch(Exception ex){
			logger.error("Error while deleting event", ex);
		}		

		AjaxResponse ajaxResponse = new AjaxResponse("Event Deleted Successfully.");

		return ajaxResponse;
	}

	private EventDetailsResponse createNotLoggedInEventRespones() {
		EventDetailsResponse eventDetailsResponse = new EventDetailsResponse();

		eventDetailsResponse.setStatus(404);
		eventDetailsResponse.setMessage("User does not exists/logged in response");

		return eventDetailsResponse;
	}

	private EventsResponse createNotLoggedInEventsRespones() {
		EventsResponse eventsResponse = new EventsResponse();
		eventsResponse.setStatus(403);
		eventsResponse.setMessage("User not exists/not logged in.");

		return eventsResponse;
	}

}
