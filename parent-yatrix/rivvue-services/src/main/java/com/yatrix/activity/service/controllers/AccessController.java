package com.yatrix.activity.service.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.yatrix.activity.store.mongo.domain.Role;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService.GENDER;
import com.yatrix.activity.store.utils.CounterService;
import com.yatrix.activity.service.dto.EventDto;
import com.yatrix.activity.service.dto.ProfileListDto;
import com.yatrix.activity.service.dto.UserDto;
import com.yatrix.activity.service.utils.UserMapper;

@Controller
@RequestMapping
public class AccessController {

	@Autowired
	private UserAccountService userRepository;

	@Autowired
	private ConnectionRepository connectionRepository;

	@Autowired
	private ProfileService service;
	
	@Autowired
	private CounterService counterService;

	private Logger log = Logger.getLogger(ProfileController.class.getName());

	@RequestMapping("/login")
	public String login() {
		return "access/login";
	}

	@RequestMapping("/denied")
	public String denied(ModelMap model) {
		model.addAttribute("error", "access.denied");
		return "error";
	}

	@RequestMapping("/login/failure")
	public String loginFailure(ModelMap model) {
		model.addAttribute("status", "login.failure");
		return "access/login";
	}

	@RequestMapping("/logout/success")
	public String logoutSuccess(ModelMap model) {
		model.addAttribute("status", "logout.success");
		return "access/login";
	}

	@RequestMapping("/signup")
	public String signup() {
		return "access/signup";
	}
	
	@RequestMapping("/homeevents")
	public String loginHome(ModelMap model) {
		
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
	

	@RequestMapping("/calendarevent")
	public String calendarEvent(@RequestParam(value = "status",required = false) String error, ModelMap model) {
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("AUTH NAME:" + authname);
		try {
			EventDto eventDto = new EventDto();
			model.addAttribute("userid", authname);
			eventDto.setFrom(authname);
			model.addAttribute("authname", authname);
			model.addAttribute("event", eventDto);
			if (error != null && !error.equals("empty")) {
				model.addAttribute("status", error);
			}
		} catch (org.springframework.social.connect.NotConnectedException nce) {
			System.out.println("not connected");
		}

		ProfileListDto userListDto = new ProfileListDto();
		List<UserDto> users = new ArrayList<UserDto>();
		//userListDto.setProfiles(service.getAllByUserId(userId));
		UserAccount user = userRepository.getUserAccount(authname);
		if(user==null){
			user = userRepository.getUserAccountByUserName(authname);
			if(user==null){
				return "access/login";
			}
		}
		//This is the facebook Id only check. Later we have
		List<UserProfile> friends=service.getMyContacts((!StringUtils.isEmpty(user.getFacebookId()))?user.getFacebookId():user.getUserId());
		if(friends!=null){
			users.addAll(UserMapper.mapUserProfile(friends));
		}
		userListDto.setProfiles(users);
		model.put("friends", userListDto);
		return "events/createEvent";
	}

	@RequestMapping("/calendar/{username}")
	public String calendarView(@PathVariable String username, ModelMap model) {
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		return "calendar";
	}

	@RequestMapping(value="/friends/{userId}")
	public String getProfileUsersPage(@PathVariable String userId, ModelMap model) {
		//String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("Retrieving profile friends for " + userId);;
		UserAccount user = userRepository.getUserAccount(userId);
		if(user == null){
			user = userRepository.getUserAccountByUserName(userId);
			
			if(user == null){
				return "access/login";
			}
		}
		ProfileListDto userListDto = new ProfileListDto();
		List<UserDto> users = new ArrayList<UserDto>();
		users.addAll(UserMapper.mapUserProfile(service.getMyContacts((!StringUtils.isEmpty(user.getFacebookId()))?user.getFacebookId():user.getUserId())));
		userListDto.setProfiles(users);
		model.put("friends", userListDto);
		userListDto.setProfiles(users);
		model.addAttribute("users", userListDto);
		model.addAttribute("authname", userId);
		return "profileusers";
	}

	@RequestMapping("/calendarevents")
	public String caledarEvents() {
		return "calendarevents";
	}

	@RequestMapping("/inviteusers")
	public String inviteUsers() {
		return "inviteusers";
	}

	@RequestMapping(
			value = "/signup",
			method = RequestMethod.POST)
			public String createAccount(UserDto dto, ModelMap model) {
		if (userRepository.getUserAccountByUserName(dto.getUsername()) != null) {
			model.addAttribute("status", "signup.invalid.username.duplicate");
			return "access/signup";
		}

		if (dto.getPassword().equals(dto.getRepassword()) == false) {
			model.addAttribute("status", "signup.invalid.password.notmatching");
			return "access/signup";
		}

		UserAccount user = UserMapper.map(dto);
		user.setUserId(counterService.getNextUserIdSequence()+"");
		user.setRoles(new Role[] { Role.ROLE_USER });
		userRepository.createUserAccount(user, GENDER.UNKNOWN, Locale.US);
		return "redirect:/";
	}
}