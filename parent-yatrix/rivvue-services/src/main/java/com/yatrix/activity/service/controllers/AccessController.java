package com.yatrix.activity.service.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.yatrix.activity.store.mongo.domain.Role;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.service.dto.EventDto;
import com.yatrix.activity.service.dto.ProfileListDto;
import com.yatrix.activity.service.dto.UserDto;
import com.yatrix.activity.service.utils.UserMapper;

@Controller
@RequestMapping
public class AccessController {

	@Autowired
	private UserAccountRepository userRepository;

	@Autowired
	private ConnectionRepository connectionRepository;

	@Autowired
	private ProfileService service;

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

	@RequestMapping("/calendarevent")
	public String calendarEvent(@RequestParam(
			value = "status",
			required = false) String error, ModelMap model) {

		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
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

		UserAccount user = userRepository.findByUserId(authname);

		if(user.getAppFriends() != null && user.getAppFriends().size() > 0){
			List<UserAccount> appFriends = userRepository.findByUsernameIn(user.getAppFriends().toArray(new String[0]));
			users = UserMapper.map(appFriends);

		}

		if(user.getFacebookFriends() != null && user.getFacebookFriends().size() > 0){
			users.addAll(UserMapper.mapUserProfile(service.getUserProfilesIn(user.getFacebookFriends().toArray(new String[0]))));

		}

		userListDto.setProfiles(users);

		model.put("friends", userListDto);

		return "events/createEvent";
	}

	@RequestMapping("/calendar/{username}")
	public String calendarView(@PathVariable String username, ModelMap model) {
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		return "calendar";
	}

	@RequestMapping(value="/friends/{username}")
	public String getProfileUsersPage(@PathVariable String username, ModelMap model) {
		//String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		log.info("Retrieving profile friends for " + username);;

		UserAccount user = userRepository.findByUserId(username);
		List<String> fbFriends = user.getFacebookFriends();
		List<UserDto> users = new ArrayList<UserDto>();			
		ProfileListDto userListDto = new ProfileListDto();

		if(user.getAppFriends() != null && user.getAppFriends().size() > 0){
			List<UserAccount> appFriends = userRepository.findByUsernameIn(user.getAppFriends().toArray(new String[0]));
			users = UserMapper.map(appFriends);
		}

		if(fbFriends != null && fbFriends.size() > 0){
			List<UserProfile> userProfiles = service.getUserProfilesIn(fbFriends.toArray(new String[0]));
			users.addAll(UserMapper.mapUserProfile(userProfiles));
		}

		//userListDto.setProfiles(userProfiles);
		userListDto.setProfiles(users);
		model.addAttribute("users", userListDto);
		model.addAttribute("authname", username);
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
		if (userRepository.findByUserId(dto.getUsername()) != null) {
			model.addAttribute("status", "signup.invalid.username.duplicate");
			return "access/signup";
		}

		if (dto.getPassword().equals(dto.getRepassword()) == false) {
			model.addAttribute("status", "signup.invalid.password.notmatching");
			return "access/signup";
		}

		UserAccount user = UserMapper.map(dto);
		user.setRoles(new Role[] { Role.ROLE_USER });
		user = userRepository.save(user);
		return "redirect:/";
	}
}