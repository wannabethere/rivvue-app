package com.yatrix.activity.service.utils;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

import com.yatrix.activity.store.mongo.domain.Reference;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.Reference.REFERENCETYPE;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;

public class FacebookConnectInterceptor implements ConnectInterceptor<Facebook> {

	/** */
	private Logger log = Logger.getLogger(FacebookConnectInterceptor.class
			.getName());

	private ProfileService service;

	private UserAccountService userAccountRepository;

	@Inject
	public FacebookConnectInterceptor(ProfileService service, UserAccountService pUserAccountRepository) {
		this.service = service;
		userAccountRepository = pUserAccountRepository;
	}

	public void postConnect(Connection<Facebook> connection, WebRequest request) {


		log.info("Facebook Post Connect !!!");
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		Facebook facebook = connection.getApi();
		FacebookProfile profile = facebook.userOperations().getUserProfile();
		UserAccount loggedInUser = userAccountRepository.getUserAccount(authname);
		if(loggedInUser==null){
			loggedInUser = userAccountRepository.getUserAccountByUserName(authname);
		}
		
		UserProfile pf=service.appUserCreate(createUserProfile(profile));
		loggedInUser.setFacebookId(profile.getId());
		UserProfile friendProfile= null;
		PagedList<FacebookProfile> friends = facebook.friendOperations()
				.getFriendProfiles();
		for (FacebookProfile friend : friends) {
			log.debug(friend.toString());
			friendProfile = createUserProfile(friend);
			service.addFriend(pf, friendProfile);	
		}
	    UserAccountService.GENDER gender= UserAccountService.GENDER.UNKNOWN;
		if(profile.getGender().toLowerCase().contains("male")){
			gender=UserAccountService.GENDER.MALE;
		}
		else if(profile.getGender().toLowerCase().contains("female")){
			gender=UserAccountService.GENDER.FEMALE;
		}
		
		userAccountRepository.createUserAccount(loggedInUser, gender, profile.getLocale());
	}

	public void preConnect(ConnectionFactory<Facebook> connectionFactory,
			MultiValueMap<String, String> parameters, WebRequest request) {
		log.debug("Facebook Pre Connect !!!");
	}
	
	private UserProfile createUserProfile(FacebookProfile profile){
		UserProfile userProfile = new UserProfile(profile.getId(), profile.getUsername(), profile.getName(), profile.getFirstName() , profile.getLastName(), profile.getGender(),
				Locale.ENGLISH);
		userProfile.setEmail(profile.getEmail());
		userProfile.setSrcprofileType(PROFILETYPE.FB);
		return userProfile;
		
	}
}