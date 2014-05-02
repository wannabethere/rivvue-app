package com.yatrix.activity.service.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.yatrix.activity.service.dto.AjaxResponse;
import com.yatrix.activity.service.dto.ContactDto;
import com.yatrix.activity.service.dto.ProfileListDto;
import com.yatrix.activity.service.dto.UserDto;
import com.yatrix.activity.service.utils.UserMapper;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;
import com.yatrix.activity.store.utils.CounterService;

@Controller
@RequestMapping("/rest/profile")
public class RestProfileController {

	/** */
	private Logger log = Logger.getLogger(RestProfileController.class.getName());

	@Autowired
	private ProfileService service;

	@Autowired
	private ConnectionRepository connectionRepository;

	@Autowired
	private UserAccountService userRepository;
	
	@Autowired
	private CounterService counterService;

	
	@RequestMapping(value = "/{userId}",method = RequestMethod.POST, produces = "application/json" )
	public @ResponseBody UserProfile create(@PathVariable String userId, @RequestParam String username, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String gender) {

		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileController:Create User ---------------");
		info.append("\nParams:");
		info.append("\nUserId: ");
		info.append(userId);
		info.append("\nUsername: ");
		info.append(username);
		info.append("\nFirstName: ");
		info.append(firstName);
		info.append("\nLastName: ");
		info.append(lastName);
		info.append("\nGender: ");
		info.append(gender);
		log.info(info.toString());

		String name = null;
		if (!isEmpty(firstName)) {
			name = firstName;

			if (!isEmpty(lastName)) {
				name = name + " " + lastName;
			}
		}

		UserProfile profile = new UserProfile(userId, username, name, firstName, lastName, gender,
				Locale.ENGLISH);

		return service.create(profile);
	}

	@RequestMapping(
			value = "/{userId}/contact",
			method = RequestMethod.POST, produces = "application/json" )
			public @ResponseBody
			UserProfile createContact(@PathVariable String userId,  ContactDto contact) {
		
		UserProfile profile=UserMapper.createContact(contact, counterService.getNextUserIdSequence()+"");
		service.addFriend(userId,profile);
		return profile;
	}


	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json" )
		public @ResponseBody
			UserDto get(@RequestParam String userId) {
		
		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileController:get ---------------");
		info.append("\nParams:");
		info.append("\nUserId: ");
		info.append(userId);
		log.info(info.toString());

		return UserMapper.mapUserProfile(service.getByUserId(userId));
	}

	@RequestMapping(value = "/getAllByUserId/{userId}", method = RequestMethod.GET, produces = "application/json" )
	public @ResponseBody
	ProfileListDto getProfiles(@PathVariable String userId) {
		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileController:getProfiles ---------------");
		info.append("\nParams:");
		info.append("\nUserId: ");
		info.append(userId);
		log.info(info.toString());

		ProfileListDto userListDto = new ProfileListDto();
		UserAccount user = userRepository.getUserAccount(userId);
		userListDto.setStatus(200);
		
		if(user!=null){
			userListDto.setProfiles(UserMapper.mapUserProfile(service.getMyContacts((!StringUtils.isEmpty(user.getFacebookId()))?user.getFacebookId():user.getUserId())));
			return userListDto;
		}
		
		return userListDto;
	}

	@RequestMapping(value = "/searchByUserId/{userId}", method = RequestMethod.GET, produces = "application/json" )
	public @ResponseBody
	ProfileListDto searchUsers(@PathVariable String userId, @RequestParam String query) {
		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileController:search ---------------");
		info.append("\nParams:");
		info.append("\nQuery: ");
		info.append(query);
		info.append("\nUserId: ");
		info.append(userId);
		log.info(info.toString());

		ProfileListDto userListDto = new ProfileListDto();

		List<UserDto> users = new ArrayList<UserDto>();

		users = UserMapper.mapUserProfile(service.searchUsersByName(query, userId));
		userListDto.setProfiles(users);

		return userListDto;
	}


	@RequestMapping(value = "/search/{username}", method = RequestMethod.GET, produces = "application/json" )
	public @ResponseBody ProfileListDto search(@PathVariable String username, @RequestParam String query) {
		
		List<UserProfile> userProfiles = service.searchUsersByName(query);
		List<UserAccount> userAccounts = userRepository.searchUserAccount(query);
		ProfileListDto userListDto = new ProfileListDto();
		List<UserDto> users = new ArrayList<UserDto>();
		users = UserMapper.mapUserProfile(userProfiles);
		users.addAll(UserMapper.map(userAccounts));
		userListDto.setProfiles(users);
		
		return userListDto;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private boolean isEmpty(String data) {
		if (data != null && data.trim().length() > 0) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST, produces = "application/json" )
	public @ResponseBody UserProfile update(@PathVariable String userId,
			@RequestParam String username, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String gender) {

		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileController:update User ---------------");
		info.append("\nParams:");
		info.append("\nUserId: ");
		info.append(userId);
		info.append("\nUsername: ");
		info.append(username);
		info.append("\nFirstName: ");
		info.append(firstName);
		info.append("\nLastName: ");
		info.append(lastName);
		info.append("\nGender: ");
		info.append(gender);
		log.info(info.toString());

		String name = null;
		if (!isEmpty(firstName)) {
			name = firstName;

			if (!isEmpty(lastName)) {
				name = name + " " + lastName;
			}
		}
		UserProfile profile = new UserProfile(userId, username, name,
				firstName, lastName, gender, Locale.ENGLISH);
		return service.update(profile);
	}

	@RequestMapping(value = "/{userId}/addFriend", method = RequestMethod.GET, produces = "application/json" )
	public @ResponseBody AjaxResponse addFriend(@PathVariable String userId,
			@RequestParam String profileType, @RequestParam String id) {
		
		log.info("User Id:" + userId);
		UserAccount user = userRepository.getUserAccount(userId);
		log.info("User Id found:" + user);
		service.addFriend(user.getFacebookId() == null ? user.getUserId() : user.getFacebookId(), id);
		
		return new AjaxResponse("Contact added successfully");
	}
	
	@RequestMapping(value = "/details/{loggedInUser}/{friendId}", method = RequestMethod.GET, produces = "application/json" )
	public 	@ResponseBody UserDto getProfileDetails(@PathVariable String loggedInUser,
			@PathVariable String friendId, ModelMap model ) {
		
		UserProfile profile = service.getByUserId(friendId);
		
		return UserMapper.mapUserProfile(profile);
	}
}
