package com.yatrix.activity.service.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yatrix.activity.service.dto.EventDto;
import com.yatrix.activity.store.mongo.domain.Category;
import com.yatrix.activity.store.mongo.domain.PostMessage;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;

@Controller
@RequestMapping("/")
public class MediatorController {

	@Autowired
	IUserActivityCatalogService userActivityCatalogService;
	@Autowired
	private UserAccountService userRepository;

	@RequestMapping
	public String getHomePage(ModelMap model) {

		String userId = SecurityContextHolder.getContext().getAuthentication().getName();

		List<UserActivity> myActivities = userActivityCatalogService.findUserEventsByUser(userId);//findAllPublicUserEventsByState("CA");

		//TODO: Write a mapping method to transfer UserActivity to EventDto.
		List<EventDto> eventList = new ArrayList<EventDto>();
		EventDto dto = null;
		for(UserActivity event : myActivities) {
			dto = new EventDto();
			dto.setId(event.getId());
			//Category category = activityCatalogService.findCategory(event.getCategoryId());
			//dto.setCategoryName(category.getDisplayName());
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
			eventList.add(dto);
		}
		model.addAttribute("events", eventList);
		model.addAttribute("authusername", SecurityContextHolder.getContext().getAuthentication().getName());
		String authName=SecurityContextHolder.getContext().getAuthentication().getName();
		UserAccount user = userRepository.getUserAccount(authName);
		if(user==null){
			user = userRepository.getUserAccountByUserName(authName);
			
			if(user==null){
				model.addAttribute("status", "logout.success");
				return "access/login";
			}
		}
		model.addAttribute("authname", user.getUserId());

		return "events/postlogin";
	}
}