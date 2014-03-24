package com.yatrix.activity.service.utils;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ProviderSignInInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

import com.yatrix.activity.store.mongo.domain.Reference;
import com.yatrix.activity.store.mongo.domain.Reference.REFERENCETYPE;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService.GENDER;

public class FacebookSignInInterceptor implements ProviderSignInInterceptor<Facebook> {

	private Logger log = Logger.getLogger(FacebookConnectInterceptor.class
			.getName());

	private ProfileService service;

	private UserAccountService userAccountRepository;

	public FacebookSignInInterceptor(ProfileService pService, UserAccountService pUserAccountRepository){
		service = pService;
		userAccountRepository = pUserAccountRepository;
	}


	public void preSignIn(ConnectionFactory<Facebook> connectionFactory,
			MultiValueMap<String, String> parameters, WebRequest request) {
		// TODO Auto-generated method stub
		//Do nothing for preSignIn.

	}

	@Override
	public void postSignIn(Connection<Facebook> connection, WebRequest request) {

		log.info("Facebook Post Connect !!!");
		String authname = SecurityContextHolder.getContext().getAuthentication().getName();
		Facebook facebook = connection.getApi();
		FacebookProfile profile = facebook.userOperations().getUserProfile();
		log.info("PF"+ profile.toString());
		
		UserAccount loggedInUser = userAccountRepository.getUserAccount(authname);
		if(loggedInUser==null){
			loggedInUser = userAccountRepository.getUserAccountByUserName(authname);
		}
		
		UserProfile pf=createUserProfile(profile);
		
		service.appUserCreate(pf);
		
		log.info("PF"+ pf.toString());
		log.info("USERACCOUNT"+ loggedInUser.toString());
		
		
		loggedInUser.setFacebookId(profile.getId());
		UserProfile friendProfile= null;
		PagedList<FacebookProfile> friends = facebook.friendOperations()
				.getFriendProfiles();
		for (FacebookProfile friend : friends) {
			log.debug(friend.toString());
			friendProfile = createUserProfile(friend);
			if(pf==null){
				log.info("Empty PF Object:"+ pf);
			}
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

	private UserProfile createUserProfile(FacebookProfile profile){
		UserProfile userProfile = new UserProfile(profile.getId(), profile.getUsername(), profile.getName(), profile.getFirstName() , profile.getLastName(), profile.getGender(),
				Locale.ENGLISH);
		userProfile.setEmail(profile.getEmail());
		userProfile.setSrcprofileType(PROFILETYPE.FB);
		return userProfile;
		
	}
	
}
