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

public class FacebookSignInInterceptor implements ProviderSignInInterceptor<Facebook> {

	private Logger log = Logger.getLogger(FacebookConnectInterceptor.class
			.getName());

	private ProfileService service;

	private UserAccountRepository userAccountRepository;

	public FacebookSignInInterceptor(ProfileService pService, UserAccountRepository pUserAccountRepository){
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
		UserAccount loggedInUser = userAccountRepository.findByUserId(authname);
		List<String> existingFriends = loggedInUser.getFacebookFriends();;

		StringBuffer infoProfile = new StringBuffer();
		infoProfile.append("\nGot Facebook Profile.");
		infoProfile.append("\nFacebookId: ");
		infoProfile.append(profile.getId());
		infoProfile.append("\nUsername: ");
		infoProfile.append(profile.getUsername());
		infoProfile.append("\nName: ");
		infoProfile.append(profile.getName());
		infoProfile.append("\nFirstName: ");
		infoProfile.append(profile.getFirstName());
		infoProfile.append("\nLastName: ");
		infoProfile.append(profile.getLastName());
		infoProfile.append("\nGender: ");
		infoProfile.append(profile.getGender());
		log.debug(infoProfile.toString());
		UserProfile userProfile = new UserProfile(profile.getId(), profile.getUsername(), profile.getName(), profile.getFirstName() , profile.getLastName(), profile.getGender(),
				Locale.ENGLISH);
		userProfile.setEmail(profile.getEmail());
		userProfile.setSrcprofileType(PROFILETYPE.FB);

		UserProfile existingUser = service.getByUserId(profile.getId());

		if (existingUser == null) {
			service.create(userProfile);
			log.info("Created User profile for userId: " + userProfile.getUserId());
		}

		loggedInUser.setFacebookId(profile.getId());

		Reference pSignificantOther = new Reference(REFERENCETYPE.CONTACTS,
				authname, null, null, null);

		PagedList<FacebookProfile> friends = facebook.friendOperations()
				.getFriendProfiles();
		for (FacebookProfile friend : friends) {

			StringBuffer infoProfileContact = new StringBuffer();
			infoProfileContact.append("\nGot Facebook Friend.");
			infoProfileContact.append("\nFacebookId: ");
			infoProfileContact.append(friend.getId());
			infoProfileContact.append("\nUsername: ");
			infoProfileContact.append(friend.getUsername());
			infoProfileContact.append("\nName: ");
			infoProfileContact.append(friend.getName());
			infoProfileContact.append("\nFirstName: ");
			infoProfileContact.append(friend.getFirstName());
			infoProfileContact.append("\nLastName: ");
			infoProfileContact.append(friend.getLastName());
			infoProfileContact.append("\nGender: ");
			infoProfileContact.append(friend.getGender());
			log.debug(infoProfileContact.toString());

			UserProfile userProfileContact = new UserProfile(friend.getId(), friend.getUsername(), friend.getName(), friend.getFirstName() , friend.getLastName(), friend.getGender(),
					Locale.ENGLISH);
			userProfileContact.setSignificantOther(pSignificantOther);
			userProfileContact.setEmail(friend.getEmail());
			userProfileContact.setSrcprofileType(PROFILETYPE.FB);

			UserProfile existingUserContact = service.getByUserId(friend.getId());

			if (existingUserContact == null) {		    
				service.create(userProfileContact);
				existingFriends.add(friend.getId());
				log.info("Created User profile  Contact for userId: " + userProfileContact.getUserId());
			} else if(!existingFriends.contains(friend.getId())){
				existingFriends.add(friend.getId());
			}
		}

		loggedInUser.setFacebookFriends(existingFriends);
		userAccountRepository.save(loggedInUser);
	}

}
