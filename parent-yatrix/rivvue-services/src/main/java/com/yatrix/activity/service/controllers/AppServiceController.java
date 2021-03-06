package com.yatrix.activity.service.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yatrix.activity.ext.domain.google.AutocompletionResponse;
import com.yatrix.activity.ext.domain.google.PlacesRequest;
import com.yatrix.activity.ext.domain.google.PlacesSearchResults;
import com.yatrix.activity.ext.service.IGooglePlacesService;
import com.yatrix.activity.ext.service.IYelpPlacesService;
import com.yatrix.activity.hystrix.fb.command.IFacebookCommand;
import com.yatrix.activity.service.dto.EventDto;
import com.yatrix.activity.service.dto.ProfileListDto;
import com.yatrix.activity.service.dto.UserDto;
import com.yatrix.activity.service.utils.UserMapper;
import com.yatrix.activity.store.exception.ActivityDBException;
import com.yatrix.activity.store.fb.domain.FacebookInvitee;
import com.yatrix.activity.store.mongo.domain.Activity;
import com.yatrix.activity.store.mongo.domain.ActivityComment;
import com.yatrix.activity.store.mongo.domain.Category;
import com.yatrix.activity.store.mongo.domain.PostMessage;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;
import com.yatrix.activity.store.mongo.service.impl.ActivityCatalogService;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.social.google.api.Google;

@Controller
@RequestMapping("/app/{uuid}")
public class AppServiceController {

	private static final Logger logger = LoggerFactory.getLogger(AppServiceController.class);

	@Autowired
	private ActivityCatalogService catalogService;

	@Autowired
	private IUserActivityCatalogService usercatalogService;

	@Autowired
	private IGooglePlacesService googlePlacesAPI;

	@Autowired
	private IYelpPlacesService yelpPlacesAPI;

	@Autowired
	private ConnectionRepository connectionRepository;

	@Autowired
	private IFacebookCommand facebookCommand;

	@Autowired
	private UserAccountRepository userRepository;

	@Autowired
	private ProfileService profileService;

	@RequestMapping(
			value = "/categories",
			method = RequestMethod.GET)
	public @ResponseBody
	List<Category> getAllCategories() {
		// Autheniticated User sessionId in cache or cookie Id for tracking purpose. May be we can use
		// cookie.
		return catalogService.findCategories(null);
	}

	@RequestMapping(
			value = "/scategories",
			method = RequestMethod.GET)
	public @ResponseBody
	List<Category> searchCategories(@RequestParam String query) {
		logger.info(" Query Request" + query);

		return catalogService.findCategories(query.toLowerCase());
	}

	@RequestMapping(
			value = "/activities/{categoryId}",
			method = RequestMethod.GET)
	public @ResponseBody
	List<Activity> searchActivity(@PathVariable String categoryId, @RequestParam String query) {
		return catalogService.searchActivities(categoryId, query.toLowerCase());
	}

	@RequestMapping(
			value = "/userevent",
			produces = "application/json",
			method = RequestMethod.POST)
	public String createActivity(@PathVariable String uuid, EventDto dto, ModelMap model)
			throws InterruptedException, ExecutionException {

		try {

			//TODO: Pass DTO instead of passing so many parameters... --- Kishore
			dto.setTitle(dto.getMessage());
			//dto.setCategoryId(dto.getTags());
			dto.setTags(dto.getCategoryId());
			dto.setLocation(dto.getPlace());
			String start = dto.getFromdate() + " " + dto.getFromtime();
			String end = dto.getTodate() + " " + dto.getTotime();
			UserActivity userActivity=usercatalogService.createActivity(dto.getTitle(),dto.getTags(),dto.getCategoryId(), dto.getSubCategoryId(),
					dto.getLocation(), dto.getFormattedAddress(), dto.getLocationLat(), dto.getLocationLng(), uuid, dto.getTo(), dto.getToAppUsers(), dto.getAccess(), start, end,
					dto.getMessage(), dto.getPlace(), dto.getDescription());
			// Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();

			// TODO: Remove this when a processor is implemented and processed event thru Life Cycle of an
			// Event.
			// processEvent(activity.getId());
			// facebookCommand = new FacebookCommand(activity, facebook);
			//      Future<HystrixSocialResult> result = facebookCommand.executeFacebookCommand(activity.getId());
			//
			//      logger.info("Event Id: " + result.get().getEventId());

			//activity.setFacebookEventId(result.get().getEventId());
			//usercatalogService.updateActivity(activity);
			model.addAttribute("activityId",userActivity.getId());
			model.addAttribute("status","Successully Created: ");
		} catch (ActivityDBException ae) {
			model.addAttribute("status", ae.getMessage());

			return "redirect:/calendarevent";
		}
		return "redirect:/calendarevents/"+uuid;
	}

	@SuppressWarnings("unused")
	private void processEvent(String activityId) {
		try {
			UserActivity activity = usercatalogService.findActivity(activityId);

			// 1. Update event that we have started processing event/activity.
			activity.setStatus(UserActivity.EVENT_STATUS.PROCESSING);
			usercatalogService.updateActivity(activity);

			// 2 a) Process event for creating FB event and sending invitees to facebook event.
			Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();
			Google google = connectionRepository.getPrimaryConnection(Google.class).getApi();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);

			String startTime = format.format(activity.getStartTime());
			String endTime = format.format(activity.getEndTime());

			String eventId = facebook.eventOperations().createEvent(
					activity.getMessageposted().getMessage(), startTime, endTime);

			// System.out.println(dto.getStart() + " : " + dto.getEnd());
			String[] invitees = activity.getParticipants().toArray(new String[0]);
			facebook.eventOperations().sendInvitation(eventId, invitees);
			activity.setFacebookEventId(eventId);

			// TODO hard coded invite list.
			// 2 b) Process google event for creating event and sending invitees to facebook event.
			List<String> attendees = new ArrayList<String>();
			attendees.add("sameer.mangalampalli@gmail.com");
			attendees.add("iamravinag@gmail.com");
			attendees.add("vharnath@gmail.com");

			String googleEventId = google.eventOperations().createEvent(startTime, endTime, attendees,
					activity.getLocation(), activity.getMessageposted().getMessage());
			activity.setGoogleEventId(googleEventId);

			// 3. Update activity with INVITED status, google event id and facebook event id.
			activity.setStatus(UserActivity.EVENT_STATUS.INVITED);

			usercatalogService.updateActivity(activity);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * Given useractvityId, search the db and return
	 * @Param
	 */
	@RequestMapping(
			value = "/userevent/{useractivityId}",
			produces = "application/json",
			method = RequestMethod.GET)
	public @ResponseBody
	UserActivity getActivity(@PathVariable String useractivityId) throws ActivityDBException {
		UserActivity event = usercatalogService.findActivity(useractivityId);
		if (event == null) {
			ActivityDBException noactivity = new ActivityDBException("no user event found ");
			throw noactivity;
		}
		return event;
	}

	/*
	 * Given useractvityId, search the db and return
	 * @Param
	 */
	@RequestMapping(
			value = "/event/{useractivityId}",
			method = RequestMethod.GET)
	public String getUserActivity(@PathVariable String uuid, @PathVariable String useractivityId, ModelMap model) throws ActivityDBException {
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		UserActivity event = usercatalogService.findActivity(useractivityId);
		List<ActivityComment> appCommentsNotPosted = new ArrayList<ActivityComment>();
		UserAccount userAccount = null;
		boolean displayJoinButton = true;
		boolean isInviteeToEvent = false;

		if (event == null) {
			ActivityDBException noactivity = new ActivityDBException("no user event found ");
			throw noactivity;
		}

		try {
			userAccount = userRepository.findByUserId(event.getOriginatorUserId());
			UserProfile pf=profileService.getByUserId(StringUtils.isEmpty(userAccount.getFacebookId())?userAccount.getUserId():userAccount.getFacebookId()); 
			event.setDisplayName(userAccount.getUserId());
			event.setAuthorName(pf.getFirstName() + " " + pf.getLastName());

			if(event.getAppComments() != null && event.getAppComments().size() > 0){
				for(ActivityComment comment : event.getAppComments()){
					if("-1".equalsIgnoreCase(comment.getId())){
						appCommentsNotPosted.add(comment);
					}
				}
			}
		} catch(Exception e) {
			logger.error(e.getMessage());
			event.setDisplayName(event.getOriginatorUserId()); 
		}

		if(event.getOriginatorUserId().equalsIgnoreCase(uuid) || event.getAppAccepted().contains(uuid) 
				|| isJoinThruFacebook(uuid, event)){
			displayJoinButton = false;
		}

		ProfileListDto userListDto = new ProfileListDto();
		List<UserDto> users = new ArrayList<UserDto>();
		UserAccount user = userRepository.findByUserId(authname);
		if(user==null){
			user = userRepository.findByUsername(authname);
			if(user==null){
				return "access/login";
			}
		}
		//This is the facebook Id only check. Later we have
		List<UserProfile> friends=profileService.getMyContacts((!StringUtils.isEmpty(user.getFacebookId()))?user.getFacebookId():user.getUserId());
		if(friends!=null){
			users.addAll(UserMapper.mapUserProfile(friends));
		}

		if(isUserInvitedForThisEvent(user, event)){
			isInviteeToEvent = true;
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

	private boolean isUserInvitedForThisEvent(UserAccount user, UserActivity event){
		if((event.getAppParticipants() != null && event.getAppParticipants().contains(user.getUserId())) ||
				(event.getParticipants() != null && event.getParticipants().contains(user.getFacebookId()))){
			return true;
		} 

		return false;
	}

	private boolean isJoinThruFacebook(String userId, UserActivity event) {

		UserAccount userAccount = userRepository.findByUserId(userId);

		if(userAccount == null){
			userAccount = userRepository.findByUsername(userId);
		}

		System.out.println("User Account: " + userAccount);
		System.out.println("Event: " + event);

		if(userAccount.getFacebookId() == null || event.getFacebookAccepted() == null){
			return false;
		}

		for(FacebookInvitee facebookAccepted : event.getFacebookAccepted()){
			if(userAccount.getFacebookId().equalsIgnoreCase(facebookAccepted.getId())){
				return true;
			}
		}

		return false;
	}

	private EventDto convertToEventDto(UserActivity event){
		EventDto dto = new EventDto();
		dto.setId(event.getId());
		Category category = catalogService.findCategory(event.getCategoryId());
		dto.setCategoryName(category.getDisplayName());
		dto.setSubCategoryId(event.getSubCategory());
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

	@RequestMapping(
			value = "/userevent/{useractivityId}",
			produces = "application/json",
			method = RequestMethod.PUT)
	public @ResponseBody
	Activity updateActivity(@PathVariable String uuid, @PathVariable String activityId,
			@RequestParam String message) {
		return null;

	}

	@RequestMapping(
			value = "/userevents",
			produces = "application/json",
			method = RequestMethod.GET)
	public @ResponseBody
	List<UserActivity> getUserEvents(@PathVariable String uuid) {
		System.out.println("user id is " + uuid);
		return usercatalogService.findUserEventsByUser(uuid);
	}

	@RequestMapping(
			value = "/userActivities",
			method = RequestMethod.GET)
	public @ResponseBody
	PlacesSearchResults searchUserActivities(@PathVariable String uuid,
			@RequestParam String activity, @RequestParam String query)
					throws Exception {
		// Authenticate the UUID for the user in this case. As we dont want to support this call for non
		// authenticated USERS.
		// May be we can use a cookie. However we need to make sure the cookie and path are set.
		StringBuilder gquery = new StringBuilder(activity.toLowerCase());
		gquery.append(" ");
		gquery.append(query.toLowerCase());
		PlacesRequest request = new PlacesRequest(gquery.toString());
		return googlePlacesAPI.findActivityPlace(request);
	}

	@RequestMapping(
			value = "/placeGoogleDetails",
			produces = "application/json",
			method = RequestMethod.GET)
	public @ResponseBody
	com.yatrix.activity.ext.domain.google.PlaceDetails getGooglePlaceDetails(@RequestParam String searchString)
			throws Exception {

		return googlePlacesAPI.loadPlaceDetails(searchString);

	}

	@RequestMapping(
			value = "/placeYelpDetails",
			produces = "application/json",
			method = RequestMethod.GET)
	public @ResponseBody
	com.yatrix.activity.ext.domain.yelp.PlaceDetails getYelpPlaceDetails(@RequestParam String searchString,
			@RequestParam String location)
					throws Exception {

		return yelpPlacesAPI.loadPlaceDetails(searchString, location);
	}

	@RequestMapping(
			value = "/citiAutocomplete",
			produces = "application/json",
			method = RequestMethod.GET)
	public @ResponseBody
	AutocompletionResponse autoCompleteCityName(@RequestParam String searchString)
			throws Exception {

		return googlePlacesAPI.autoCompleteCities(searchString);

	}

	@RequestMapping(
			value = "/activityAutocomplete",
			produces = "application/json",
			method = RequestMethod.GET)
	public @ResponseBody
	List<Activity> activityAutocomplete(@RequestParam String searchString)
			throws Exception {

		return catalogService.searchActivities(searchString);

	}

}
