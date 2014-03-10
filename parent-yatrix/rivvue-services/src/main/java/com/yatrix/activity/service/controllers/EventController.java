package com.yatrix.activity.service.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.yatrix.activity.hystrix.command.HystrixSocialResult;
import com.yatrix.activity.hystrix.command.IFacebookPostEventFeedCommand;

import com.yatrix.activity.service.mongo.dto.EventDto;
import com.yatrix.activity.store.fb.domain.FacebookReference;
import com.yatrix.activity.store.mongo.domain.ActivityComment;
import com.yatrix.activity.store.mongo.domain.Category;

import com.yatrix.activity.store.mongo.domain.PostMessage;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;
import com.yatrix.activity.store.mongo.service.impl.ActivityCatalogService;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;
@Controller
@RequestMapping("/calendarevents")
public class EventController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);
	
	
	@Autowired
	private IUserActivityCatalogService usercatalogService;
	
	@Autowired
	private ActivityCatalogService activityCatalogService;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	private ProfileService profileService;

	@Autowired
	private IFacebookPostEventFeedCommand postFeedCommand;
	
	@RequestMapping(value="/{username}", produces="application/json" ,method=RequestMethod.GET)
	public String  getAllEvents(@PathVariable String username,ModelMap model) {
		//Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use cookie.
		logger.debug("finding all events for user"+username);
		List<UserActivity> list =  usercatalogService.findUserEventsByUser(username);
		List<EventDto> eventList = new ArrayList<EventDto>();
		EventDto dto = null;
		for(UserActivity event : list) {
			dto = new EventDto();
			dto.setId(event.getId());
			Category category = activityCatalogService.findCategory(event.getCategoryId());
			dto.setCategoryName(category.getDisplayName());
			dto.setSubCategoryId(event.getSubCategory());
			dto.setStart(event.getStartTime().toString());
			dto.setEnd(event.getEndTime().toString());
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
			eventList.add(dto);
		}
		logger.debug("number of events found :"+eventList.size());
		model.addAttribute("events", eventList);
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		return "calendarevents";
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
		List<EventDto> eventList = new ArrayList<EventDto>();
		EventDto dto = null;
		
		for(UserActivity event : list) {
			dto = new EventDto();
			dto.setId(event.getId());
			Category category = activityCatalogService.findCategory(event.getCategoryId());
			dto.setCategoryName(category.getDisplayName());
			dto.setSubCategoryId(event.getSubCategory());
			dto.setStart(event.getStartTime().toString());
			dto.setEnd(event.getEndTime().toString());
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
			eventList.add(dto);
		}
		logger.debug("number of events found :"+eventList.size());
		model.addAttribute("events", eventList);
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		return "calendarevents";
	}

	@RequestMapping(value="/{username}/invitedevents", produces="application/json" ,method=RequestMethod.GET)
	public String  getEventsIAmInvited(@PathVariable String username, ModelMap model) {
		//Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use cookie.
		logger.debug("finding all invited events for user" + username);
		
		UserAccount userAccount = userAccountRepository.findByUserId(username);
		
		List<UserActivity> list =  usercatalogService.findEventsIAmInvited(username, userAccount.getFacebookId());
		List<EventDto> eventList = new ArrayList<EventDto>();
		EventDto dto = null;
		
		for(UserActivity event : list) {
			dto = new EventDto();
			dto.setId(event.getId());
			Category category = activityCatalogService.findCategory(event.getCategoryId());
			dto.setCategoryName(category.getDisplayName());
			dto.setSubCategoryId(event.getSubCategory());
			dto.setStart(event.getStartTime().toString());
			dto.setEnd(event.getEndTime().toString());
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
			eventList.add(dto);
		}
		logger.debug("number of events found :"+eventList.size());
		model.addAttribute("events", eventList);
		model.addAttribute("authname", username);
		return "calendarevents";
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
	
}
