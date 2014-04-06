package com.yatrix.activity.service.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.util.Joiner;
import com.yatrix.activity.hystrix.command.HystrixSocialResult;
import com.yatrix.activity.hystrix.command.IFacebookJoinEventCommand;
import com.yatrix.activity.hystrix.command.IFacebookPostEventFeedCommand;
import com.yatrix.activity.service.dto.AjaxResponse;
import com.yatrix.activity.service.dto.EventDto;
import com.yatrix.activity.service.facebook.FacebookEventService;
import com.yatrix.activity.store.fb.domain.FacebookReference;
import com.yatrix.activity.store.mongo.domain.ActivityComment;
import com.yatrix.activity.store.mongo.domain.Category;
import com.yatrix.activity.store.mongo.domain.PostMessage;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserActivity;

import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.service.impl.ActivityCatalogService;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;
@Controller
@RequestMapping("/calendarevents")
public class EventController {


	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	// Create an instance of SimpleDateFormat used for formatting 
	// the string representation of date (month/day/year)
	private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	@Autowired
	private IUserActivityCatalogService usercatalogService;

	@Autowired
	private ActivityCatalogService activityCatalogService;

	@Autowired
	private UserAccountService userAccountRepository;
	
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired 
	private FacebookEventService facebookService;

	@Autowired
	private IFacebookJoinEventCommand facebookJoinCommand;
	
	@Autowired
	private IFacebookPostEventFeedCommand postFeedCommand;
	
	@Autowired
	private UserAccountService userAccountService;

	@RequestMapping(value="/{userId}", produces="application/json" ,method=RequestMethod.GET)
	public String  getAllEvents(@PathVariable String userId,ModelMap model) {
		//Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use cookie.
		logger.debug("finding all events for user"+userId);
		List<UserActivity> list =  usercatalogService.findUserEventsByUser(userId);
		List<EventDto> eventList= this.mapUserActivityToEventDtos(list);
		logger.debug("number of events found :"+eventList.size());
		model.addAttribute("events", eventList);
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		return "events/postlogin";
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
		List<UserActivity> list =  usercatalogService.findAllPublicUserEventsByState(state);
		List<EventDto> eventList= this.mapUserActivityToEventDtos(list);
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
		List<UserActivity> list =  usercatalogService.findEventsIAmInvited(userId, userAccount.getFacebookId());
		List<EventDto> eventList= this.mapUserActivityToEventDtos(list);
		logger.debug("number of events found :"+eventList.size());
		model.addAttribute("events", eventList);
		model.addAttribute("authname", userId);
		return "events/postlogin";
	}
	
	

	@RequestMapping(value="/{username}/{eventId}/postFBMessage", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody ActivityComment postFeedToFacebook(@PathVariable String username, @PathVariable String eventId, @RequestParam String message, ModelMap model) throws Exception {

		UserActivity activity = usercatalogService.findActivity(eventId);
		System.out.println("Message in controller - - - - - -- - - - - -" + message);
		HystrixSocialResult result = postFeedCommand.executeFacebookPostEventFeed(activity, username, message).get();
		ActivityComment comment = new ActivityComment();
		String id = "-1";
		if(result.isSuccess()){
			id = result.getEventId();
		}
		FacebookReference reference = new FacebookReference(username, username);
		//TODO: Actually it's a feed id. HystrixSocialResult need to be refactored to modify it as id rather than event id.
		comment = new ActivityComment(id, message, new Date(), reference);
		activity.getAppComments().add(comment);
		usercatalogService.updateActivity(activity);
		return comment;
	}
	
	@RequestMapping(value="/{authname}/{eventId}/joinEvent", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody AjaxResponse joinEvent(@PathVariable String authname, @PathVariable String eventId, ModelMap model) throws Exception {

		UserActivity activity = usercatalogService.findActivity(eventId);
		//System.out.println("Message in controller - - - - - -- - - - - -" + message);
		UserAccount userAccount = userAccountService.getUserAccountByUserName(authname);
		if(userAccount == null){
			userAccount = userAccountService.getUserAccount(authname);
		}
		
		if(userAccount.getFacebookId() == null){
			List<String> appAccepted = activity.getAppAccepted();
			appAccepted.add(userAccount.getUserId());
			activity.setAppAccepted(appAccepted);
			usercatalogService.updateActivity(activity);
		} else{
			facebookJoinCommand.executeFacebookJoinEvent(activity, userAccount.getUserId());	
		}
		
		
		return new AjaxResponse("User join successful");
	}
	
	@RequestMapping(value="/{authname}/{eventId}/rejectEvent", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody AjaxResponse rejectEvent(@PathVariable String authname, @PathVariable String eventId, ModelMap model) throws Exception {

		
		return new AjaxResponse("User rejected successful");
	}
	
	@RequestMapping(value="/{authname}/{eventId}/maybeEvent", produces="application/json", method=RequestMethod.GET)
	public @ResponseBody AjaxResponse maybeEvent(@PathVariable String authname, @PathVariable String eventId, ModelMap model) throws Exception {

		
		return new AjaxResponse("Tentatively accepted");
	}
	
	@RequestMapping(value="/{authname}/{eventId}/deleteEvent", produces="application/json", method=RequestMethod.GET)
	public String deleteEvent(@PathVariable String authname, @PathVariable String eventId, ModelMap model) throws Exception {

		UserActivity activity = usercatalogService.findActivity(eventId);
		
		try{
			usercatalogService.delete(activity, authname);
		}catch(Exception ex){
			logger.error("Error while deleting event", ex);
		}
		
		return "redirect:/calendarevents/" + authname;
	}
	
	
	public @ResponseBody UserActivity addUserToEvent(@PathVariable String userId, @PathVariable String eventId, @RequestParam String message, @RequestParam String requestorId ){
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		Log.info("Auth Name: logged Security issue: " + authname);
		//Authenticate the user.
		UserActivity activity = usercatalogService.findActivity(eventId);
		if(activity!=null){
			ActivityComment comment=new ActivityComment();
			comment.setCreatedTime(new java.util.Date());
			comment.setMessage(message);
			comment.setId(requestorId);
			//UserAccount acct=userAccountRepository.getUserAccount(requestorId);
			activity.getAppComments().add(comment);
			activity.getAppParticipants().add(requestorId);
			usercatalogService.updateActivity(activity);
			return activity;
		}
		else{
			return null;
		}
		
	}
	
	
	
	private  List<EventDto> mapUserActivityToEventDtos(List<UserActivity> activityList){
		List<EventDto> eventList = new ArrayList<EventDto>();
		EventDto dto = null;
		for(UserActivity event : activityList) {
			//TODO: Write a mapping method to transfer UserActivity to EventDto.
			dto = new EventDto();
			dto.setId(event.getId());
			Category category = activityCatalogService.findCategory(event.getCategoryId());
			dto.setCategoryName(category.getDisplayName());
			dto.setSubCategoryId(event.getSubCategory());
			dto.setTitle(event.getTitle());
			dto.setTags(Joiner.on(',').join(event.getTags()));
			dto.setStartDate(event.getStartTime());
			dto.setEndDate(event.getEndTime());
			dto.setDuration();
			dto.setStart(df.format(event.getStartTime()));
			dto.setEnd(df.format(event.getEndTime()));
			dto.setLocation(event.getLocation()); 
			dto.setFormattedAddress(event.getFormattedAddress());
			dto.setLocationLat(event.getLocationLat());
			dto.setLocationLng(event.getLocationLng()); 
			if(event.getMessageposted()!=null) {
				PostMessage message = event.getMessageposted();
				dto.setMessage(message.getMessage());
			}
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
			dto.setFacebookAccepted(event.getFacebookAccepted());
			eventList.add(dto);
		}

		return eventList;
	}


}
