package com.yatrix.activity.service.commands;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.service.utils.FacebookConnectInterceptor;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;

public class LoginUpdateFBFriendsCommand extends HystrixCommand<UserAccount>  {

	private static final Logger log = Logger.getLogger(FacebookConnectInterceptor.class
			.getName());
	
	private ProfileService profileService;
	
	private UserAccountService userAccountRepository;
	
	private final FacebookProfile profile;
	private final PagedList<FacebookProfile> friendsProfile;
	private final String authName;
	
	public LoginUpdateFBFriendsCommand(String authname,FacebookProfile profile, PagedList<FacebookProfile> friends, ProfileService pservice, UserAccountService service ) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
			      .andCommandKey(HystrixCommandKey.Factory.asKey("UpdateFriends"))
			      .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FacebookThread"))
			      .andCommandPropertiesDefaults(
			      // we default to a one minute timeout for primary
			        HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		this.profile=profile;
		this.friendsProfile=friends;
		this.authName=authname;
		this.profileService=pservice;
		this.userAccountRepository=service;
		
		
	}

	@Override
	protected UserAccount run() throws Exception {
		log.info("Testing FB Update");
		if(userAccountRepository==null){
			log.debug("UserAccountRepository is not Autowired");
			return null;
		}
		if(profileService==null){
			log.debug("ProfileprofileService is not Autowired");
			return null;
		}
		
		UserAccount loggedInUser = userAccountRepository.getUserAccount(authName);
		if(loggedInUser==null){
			loggedInUser = userAccountRepository.getUserAccountByUserName(authName);
		}
		UserProfile pf=createUserProfile(profile);
		
		profileService.appUserCreate(pf);
		
		
		log.info("PF"+ pf.toString());
		log.info("USERACCOUNT"+ loggedInUser.toString());
		loggedInUser.setFacebookId(profile.getId());
		UserProfile friendProfile= null;
		List<String> idsAlreadyThere=profileService.getMyContactIds(pf.getUserId());
		
		long lastUpdatedTimeStamp=0;
		if(loggedInUser!=null && loggedInUser.getUpdatedTimeStamp()!=null ){
			lastUpdatedTimeStamp=loggedInUser.getUpdatedTimeStamp();
		}
		
		loggedInUser.setUpdatedTimeStamp(System.currentTimeMillis());
		if(lastUpdatedTimeStamp==0 || System.currentTimeMillis()-lastUpdatedTimeStamp>21600){
			for (FacebookProfile friend : this.friendsProfile) {
				log.debug("Friend Profile"+friend.getId()+" " + friend.toString());
				if(idsAlreadyThere!=null && idsAlreadyThere.contains(friend.getId())){
					continue;
				}
				friendProfile = createUserProfile(friend);
				
				profileService.addFriend(pf, friendProfile);	
			}
		}
		UserAccountService.GENDER gender= UserAccountService.GENDER.UNKNOWN;
		if(profile.getGender().toLowerCase().contains("male")){
			gender=UserAccountService.GENDER.MALE;
		}
		else if(profile.getGender().toLowerCase().contains("female")){
			gender=UserAccountService.GENDER.FEMALE;
		}
		
		userAccountRepository.createUserAccount(loggedInUser, gender, profile.getLocale());
		
		return loggedInUser;
	}
	
	
	private UserProfile createUserProfile(FacebookProfile profile){
		UserProfile userProfile = new UserProfile(profile.getId(), profile.getUsername(), profile.getName(), profile.getFirstName() , profile.getLastName(), profile.getGender(),
				Locale.ENGLISH);
		userProfile.setEmail(profile.getEmail());
		userProfile.setSrcprofileType(PROFILETYPE.FB);
		userProfile.setMiddleName(profile.getMiddleName());
		userProfile.setImageUrl(profile.)
		return userProfile;
		
	}
}
