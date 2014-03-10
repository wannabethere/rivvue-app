package com.yatrix.activity.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.ExpiredAuthorizationException;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yatrix.social.google.api.Google;

@Controller
@RequestMapping("/google")
public class GoogleController {
	
	@Autowired
	private ConnectionRepository connectionRepository;
	
	@ModelAttribute("source")
	public String source() {
		return "google";
	}
	
	@RequestMapping(value="/profile")
	public String getProfile(ModelMap model) {
		try {
			Google google = connectionRepository.getPrimaryConnection(Google.class).getApi();
			model.addAttribute("profileLink", google.userOperations().getUserProfile().getLink());
			model.addAttribute("profileInfo", google.userOperations().getUserProfile());
	        model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication()
	                .getName());
			return "google/profile";
		}  catch (NotConnectedException e) {
	        model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication()
	                .getName());
			return "google/connect";
		} catch (ExpiredAuthorizationException e) {
	        model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication()
	                .getName());
			connectionRepository.removeConnection(connectionRepository.getPrimaryConnection(Google.class).getKey());
			return "google/connect";
		}
	}
	
}
