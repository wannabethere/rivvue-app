package com.yatrix.activity.service.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;

@Controller
@RequestMapping("/")
public class MediatorController {

	@Autowired
	IUserActivityCatalogService userActivityCatalogService;
	
	@RequestMapping
	public String getHomePage(ModelMap model) {

		//TODO: Place correct type of events.
		List<UserActivity> publicActivities = new ArrayList<UserActivity>();//userActivityCatalogService.findAllPublicUserEventsByState("CA");
		
		model.put("events", publicActivities);
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		
		return "events/postlogin";
	}
}