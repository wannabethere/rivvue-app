package com.yatrix.activity.store.mongo.service.impl;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;
import com.yatrix.activity.store.mongo.domain.UserProfile.PSTATUS;
import com.yatrix.activity.store.mongo.repository.UserAccountRepository;

@Service
public class UserAccountService {

	/** */
	private final static Logger log = Logger.getLogger(UserAccountService.class.getName());
	
	public static enum GENDER{
		MALE("male"),FEMALE("female"),UNKNOWN("unknown");
		private String gender;
		GENDER(String gender){
			this.gender=gender;
		}
		public String getGender(){
			return gender;
		}
	}
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private UserAccountRepository userAccountRepository;
	
	public UserAccount getUserAccount(String userId){
		return userAccountRepository.findByUserId(userId);
	}
	
	public UserAccount getUserAccountByUserName(String userName){
		return userAccountRepository.findByUsername(userName);
	}
	
	public List<UserAccount> searchUserAccount(String searchString){
		return userAccountRepository.findByEmailOrFirstNameOrLastNameLike(searchString);
	}
	
	public void createUserAccount(UserAccount account, GENDER gender, Locale locale){
		//Create a first cut for the User Profile as well.
		//Eventually we will use email as the UserName.
		UserProfile pf=createUserProfileObject(account, gender, locale, true);
		pf.setSrcprofileType(PROFILETYPE.APP);
		profileService.create(pf);
		account.getAvatars().add(pf);
		userAccountRepository.save(account);
		log.info(account.getId()+ "Succesfully Created");
		return;
	}
	
	public void updateUserAccount(UserAccount account){
		UserAccount existingUser = userAccountRepository.findByUserId(account.getUserId());
	    if (existingUser == null) {
	      throw new IllegalStateException("Failed to find the User");
	    }
	    account.setId(existingUser.getId());
	    //Typically there is only one profile. But Providing more just in case.
	    account.getAvatars().addAll(existingUser.getAvatars());
	    account.getConnections().addAll(existingUser.getConnections());
	    userAccountRepository.save(account);
	    return;
	}
	
	public static UserProfile createUserProfileObject(UserAccount account, GENDER gender, Locale locale, boolean hasAppAccount){
		UserProfile pf=new UserProfile(account.getId(), account.getEmail(), account.getDisplayName(), account.getFirstName(),
				account.getLastName(), gender.getGender(), locale);
		pf.setAppAccount(hasAppAccount);
		pf.setStatus(PSTATUS.ACTIVE);
		return pf;
	}

	public static UserProfile createUserProfileObject(String userId, String username, String name, String firstName,
			String lastName, GENDER gender, Locale locale, boolean hasAppAccount){
		UserProfile pf=new UserProfile(userId,username,name, firstName,lastName, gender.getGender(), locale);
		pf.setAppAccount(hasAppAccount);
		pf.setStatus(PSTATUS.ACTIVE);
		return pf;
	}

	
	
}
