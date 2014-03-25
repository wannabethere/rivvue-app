package com.yatrix.activity.service.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.yatrix.activity.store.mongo.domain.Reference;
import com.yatrix.activity.store.mongo.domain.Reference.REFERENCETYPE;
import com.yatrix.activity.store.mongo.domain.Role;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;
import com.yatrix.activity.service.dto.ProfileListDto;
import com.yatrix.activity.service.dto.UserDto;
import com.yatrix.activity.service.utils.UserMapper;

@Controller
@RequestMapping("/profile")
public class ProfileController {

	/** */
	private Logger log = Logger.getLogger(ProfileController.class.getName());

	@Autowired
	private ProfileService service;

	@Autowired
	private ConnectionRepository connectionRepository;

	@Autowired
	private UserAccountService userRepository;
	
	
	

	@RequestMapping(value = "/{userId}",method = RequestMethod.POST)
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
			value = "/{refUserId}/createContact",
			method = RequestMethod.POST)
			public @ResponseBody
			UserProfile createContact(@PathVariable String refUserId, @RequestParam String userId, @RequestParam String username, @RequestParam String firstName,
					@RequestParam String lastName, @RequestParam String gender) {
		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileController:Create Contact ---------------");
		info.append("\nParams:");
		info.append("\nUserId: ");
		info.append(userId);
		info.append("\nReference UserId: ");
		info.append(refUserId);
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
		service.addFriend(userId,profile);
		return profile;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(
			value = "/{refUserId}/createRefContact",
			method = RequestMethod.POST)
			public String createRefContact(@PathVariable String refUserId, UserDto dto,ModelMap model) {

		UserDto userDto = null;
		List<UserDto> users = new ArrayList<UserDto>();

		if(dto != null) {
			StringBuffer info = new StringBuffer();
			info.append("\n--------------- Inside ProfileController:Create Contact ---------------");
			info.append("\nParams:");
			info.append("\nUserId: ");
			info.append(dto.getUsername());
			info.append("\nReference UserId: ");
			info.append(refUserId);
			info.append("\nUsername: ");
			info.append(dto.getUsername());
			info.append("\nFirstName: ");
			info.append(dto.getFirstName());
			info.append("\nLastName: ");
			info.append(dto.getLastName());
			log.info(info.toString());


			String name = null;
			if (!isEmpty(dto.getFirstName())) {
				name = dto.getFirstName();

				if (!isEmpty(dto.getLastName())) {
					name = name + " " + dto.getLastName();
				}
			}

			UserAccount user = new UserAccount();

			user.setUserId(dto.getUsername());
			user.setUsername(dto.getUsername());
			user.setFirstName(dto.getFirstName());
			user.setLastName(dto.getLastName());
			user.setEmail(dto.getEmail());

			PasswordEncoder encoder = new Md5PasswordEncoder();
			user.setPassword(encoder.encodePassword("activity", null));

			user.setAccountLocked(false);

			user.setRoles(new Role[] { Role.ROLE_USER });
			userRepository.createUserAccount(user,UserAccountService.GENDER.UNKNOWN, null);
			
			
		}
		String userId = SecurityContextHolder.getContext().getAuthentication().getName();
		List<UserProfile> userProfiles = service.getAllByUserId(userId);

		users.addAll(UserMapper.mapUserProfile(userProfiles));
		ProfileListDto userListDto = new ProfileListDto();
		userListDto.setProfiles(users);

		model.addAttribute("users", userListDto);
		model.addAttribute("authname", SecurityContextHolder.getContext().getAuthentication().getName());
		return "profileusers";
	}

	@RequestMapping(
			value = "/get")
			public @ResponseBody
			UserProfile get(@RequestParam String userId) {
		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileController:get ---------------");
		info.append("\nParams:");
		info.append("\nUserId: ");
		info.append(userId);
		log.info(info.toString());

		return service.getByUserId(userId);
	}

	@RequestMapping(value = "/getAllByUserId/{userId}")
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
		if(user!=null){
			userListDto.setProfiles(UserMapper.mapUserProfile(service.getMyContacts((!StringUtils.isEmpty(user.getFacebookId()))?user.getFacebookId():user.getUserId())));
			return userListDto;
		}
		return null;
	}

	@RequestMapping(value = "/searchByUserId/{userId}")
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
		//userListDto.setProfiles(service.searchUsersByName(query, userId));

		List<UserDto> users = new ArrayList<UserDto>();

		users = UserMapper.mapUserProfile(service.searchUsersByName(query, userId));
		userListDto.setProfiles(users);

		return userListDto;
	}


	@RequestMapping(value = "/search/{username}")
	public String search(@PathVariable String username, ModelMap model, @RequestParam String query) {
		//String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		List<UserProfile> userProfiles = service.searchUsersByName(query);
		List<UserAccount> userAccounts = userRepository.searchUserAccount(query);
		ProfileListDto userListDto = new ProfileListDto();
		List<UserDto> users = new ArrayList<UserDto>();
		users = UserMapper.mapUserProfile(userProfiles);
		users.addAll(UserMapper.map(userAccounts));
		userListDto.setProfiles(users);
		//userListDto.setProfiles(userProfiles);
		model.addAttribute("users", userListDto);
		model.addAttribute("authname", username);
		return "profileusers";
	}

	@RequestMapping(value = "/create/{username}")
	public String createContact(@PathVariable String username, ModelMap model) {
		model.addAttribute("authname", username);
		model.addAttribute("commanduser", new UserDto());
		model.addAttribute("usertype", "new");
		return "user";
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

	@RequestMapping(value = "/{userId}/update", method = RequestMethod.POST)
	public @ResponseBody
	UserProfile update(@PathVariable String userId,
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

	@RequestMapping(value = "/{userId}/addFriend", method = RequestMethod.POST)
	public @ResponseBody
	String addFriend(@PathVariable String userId,
			@RequestParam String profileType, @RequestParam String id) {
		UserAccount user = userRepository.getUserAccount(userId);
		service.addFriend(userId, id);
		return "contacts";
	}
}
