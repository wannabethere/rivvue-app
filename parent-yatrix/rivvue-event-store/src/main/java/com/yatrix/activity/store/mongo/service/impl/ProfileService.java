package com.yatrix.activity.store.mongo.service.impl;

import com.yatrix.activity.store.mongo.domain.Reference;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.Reference.REFERENCETYPE;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;
import com.yatrix.activity.store.mongo.domain.UserProfile.PSTATUS;
import com.yatrix.activity.store.mongo.repository.ProfileRepository;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService.GENDER;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

	/** */
	private Logger log = Logger.getLogger(ProfileService.class.getName());

	@Autowired
	private ProfileRepository userRepository;

	/**
	 * @param profile
	 * @return
	 */
	public UserProfile create(UserProfile profile) {
		log.info("--------------- Inside ProfileService:create() ---------------");
		UserProfile existingUser =userRepository.findByUserId(profile.getUserId());
		//UserProfile already exists so we need to create.
		if(existingUser!=null){
			return existingUser;
		}
		return userRepository.save(profile);
	}
	
	/**
	 * @param profile
	 * @return
	 */
	public UserProfile appUserCreate(UserProfile profile) {
		log.info("--------------- Inside ProfileService:create() ---------------");
		UserProfile existingUser =userRepository.findByUserId(profile.getUserId());
		//UserProfile already exists so we need to create.
		if(existingUser!=null){
			existingUser.setAppAccount(true);
			return userRepository.save(existingUser);
		}
		return userRepository.save(profile);
	}
	

	/**
	 * @param userId
	 * @return
	 */
	public List<UserProfile> getAllByUserId(String userId) {
		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileService:getAllByUserId ---------------");
		info.append("\nParams: ");
		info.append("\nUserId: ");
		info.append(userId);
		log.info(info.toString());
		return userRepository.findByReferenceUserId(userId);
	}

	/**
	 * @param searchName
	 * @param userId
	 * @return
	 */
	public List<UserProfile> searchUsersByName(String searchName, String userId) {

		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileService:searchUsersByName ---------------");
		info.append("\nParams: ");
		info.append("\nSearchName: ");
		info.append(searchName);
		info.append("\nUserId: ");
		info.append(userId);
		log.info(info.toString());
		return userRepository.findByEmailOrFirstNameOrLastNameLike(searchName, userId);
	}

	/**
	 * @param searchName
	 * @param userId
	 * @return
	 */
	public List<UserProfile> searchUsersByName(String searchName) {
		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileService:searchUsersByName ---------------");
		info.append("\nParams: ");
		info.append("\nSearchName: ");
		info.append(searchName);
		log.info(info.toString());

		return userRepository.findByEmailOrFirstNameOrLastNameLike(searchName);
	}

	/**
	 * @param userId
	 * @return
	 */
	public UserProfile getByUserId(String userId) {
		StringBuffer info = new StringBuffer();
		info.append("\n--------------- Inside ProfileService:getByUserId ---------------");
		info.append("\nParams: ");
		info.append("\nUserId: ");
		info.append(userId);
		log.info(info.toString());
		UserProfile existingUser = userRepository.findByUserId(userId);
		if (existingUser == null) {
			throw new UsernameNotFoundException("Didnot find the user");
		}
		return existingUser;
	}

	/**
	 * @param profile
	 * @return
	 */
	public UserProfile update(UserProfile profile) {
		log.info("--------------- Inside ProfileService:update() ---------------");
		UserProfile existingUser = userRepository.findByUserId(profile.getParentId());
		if (existingUser == null) {
			throw new UsernameNotFoundException("Didnot find the user");
		}
		profile.setId(existingUser.getId());
		profile.setSignificantOther(existingUser.getSignificantOther());
		return userRepository.save(profile);
	}

	public void addFriend(String parentId, UserProfile pf){
		UserProfile parentProfile = userRepository.findByParentId(parentId);
		if (parentProfile == null) {
			parentProfile=userRepository.findByUserId(parentId);
			if(parentProfile==null){
				throw new UsernameNotFoundException("Didnot find the user");
			}
		}
		
		Reference ref= new Reference(REFERENCETYPE.CONTACTS, parentId, pf.getId());
		UserProfile existingFriendPf=null;
		parentProfile.getSocialIds().add(ref);
		update(parentProfile);
		//Now we will also update the friends profile to be connected to the parent ref.
		//This way when the user becomes the app user then we have already his friends.
		existingFriendPf=userRepository.findByUserId(pf.getId());
		if(existingFriendPf!=null){
			existingFriendPf.getSocialIds().add(ref);
			update(existingFriendPf);
		}
		else{
			//I didnot find the User Id.
			pf.getSocialIds().add(ref);
			create(pf);
		}
		return;
	}
	
	public void addFriend(String parentId, String pfId){
		UserProfile parentProfile = userRepository.findByParentId(parentId);
		if (parentProfile == null) {
			parentProfile=userRepository.findByUserId(parentId);
			if(parentProfile==null){
				throw new UsernameNotFoundException("Didnot find the user");
			}
		}
		UserProfile existingFriendPf=userRepository.findByUserId(pfId);
		if(existingFriendPf==null){
			throw new UsernameNotFoundException("Didnot find the friend I have to add");
		}
		Reference ref= new Reference(REFERENCETYPE.CONTACTS, parentId, existingFriendPf.getId());
		parentProfile.getSocialIds().add(ref);
		update(parentProfile);
		//Now we will also update the friends profile to be connected to the parent ref.
		//This way when the user becomes the app user then we have already his friends.
		existingFriendPf.getSocialIds().add(ref);
		update(existingFriendPf);
		return;
	}
	
	
	public UserProfile addFriend(UserProfile parentProfile, UserProfile pf){
		
		if(parentProfile==null){
			throw new UsernameNotFoundException("Didnot find the user");
		}
		Reference ref= new Reference(REFERENCETYPE.CONTACTS, parentProfile.getUserId(), pf.getId());
		UserProfile existingFriendPf=null;
		parentProfile.getSocialIds().add(ref);
		update(parentProfile);
		//Now we will also update the friends profile to be connected to the parent ref.
		//This way when the user becomes the app user then we have already his friends.
		existingFriendPf=userRepository.findByUserId(pf.getId());
		if(existingFriendPf!=null){
			existingFriendPf.getSocialIds().add(ref);
			update(existingFriendPf);
		}
		else{
			//I didnot find the User Id.
			pf.getSocialIds().add(ref);
			create(pf);
		}
		return parentProfile;
	}
	
	public UserProfile removeFriend(String parentId, String refId){
		UserProfile parentProfile = userRepository.findByParentId(parentId);
		if (parentProfile == null) {
			parentProfile=userRepository.findByUserId(parentId);
			if(parentProfile==null){
				throw new UsernameNotFoundException("Didnot find the user");
			}
		}
		int index=0;
		for(Reference ref:parentProfile.getSocialIds()){
			if(ref.getId().equals(refId)){
				parentProfile.getSocialIds().remove(index);
			}
			index++;
		}
		return parentProfile;
	}
	
	
	public List<String> getMyContactIds(String parentId){
		UserProfile parentProfile = userRepository.findByUserId(parentId);
		if (parentProfile == null) {
			throw new UsernameNotFoundException("Didnot find the user");
		}
		List<String> ids=new ArrayList<String>();
		for(Reference ref:parentProfile.getSocialIds()){
			ids.add(ref.getId());
		}
		return ids;
	}
	
	/**
	 * This should not be used for the real application.
	 * Ajax call should be used to load the data as it finishes.
	 * @param parentId
	 * @return
	 */
	public List<UserProfile> getMyContacts(String parentId){
		UserProfile parentProfile = userRepository.findByUserId(parentId);
		if (parentProfile == null) {
			throw new UsernameNotFoundException("Didnot find the user");

		}
		List<UserProfile> ids=new ArrayList<UserProfile>();
		for(Reference ref:parentProfile.getSocialIds()){
			ids.add(getByUserId(ref.getId()));
		}
		return ids;
	}
	
	public List<UserProfile> getUserProfilesIn(String[] userIds){
		return userRepository.findByUserIdIn(userIds);
	}
	
	
	 /**
     * Updates user's profile data, like email, display name, etc.
     * SECURITY: Current logged in user must have ROLE_ADMIN.
     * 
     * @param userId
     * @param displayName
     * @param email
     * @param webSite
     * @param imageUrl
     * @return
     * @throws UsernameNotFoundException
     * 
     */
    public UserProfile updateProfile(String userId, String displayName, String email, String webSite, String imageUrl)
        		throws UsernameNotFoundException{
    	UserProfile existingUserProfile=userRepository.findByUserId(userId);
    	if (existingUserProfile == null) {
			throw new UsernameNotFoundException("Didnot find the user");
		}
    	existingUserProfile.setEmail(email);
    	existingUserProfile.setImageUrl(imageUrl);
    	existingUserProfile.getWebsite().add(webSite);
    	return update(existingUserProfile);
    }

    /**
     * Updates user's profile data, like email, display name, etc.
     * SECURITY: Current logged in user must have ROLE_USER.
     * 
     * @param userId
     * @param displayName
     * @param email
     * @param webSite
     * @return
     * @throws UsernameNotFoundException
     */
    public UserProfile updateProfile(String userId, String displayName, String email, String webSite)
            throws UsernameNotFoundException{
    	UserProfile existingUserProfile=userRepository.findByUserId(userId);
    	if (existingUserProfile == null) {
			throw new UsernameNotFoundException("Didnot find the user");
		}
    	existingUserProfile.setEmail(email);
    	existingUserProfile.getWebsite().add(webSite);
    	return update(existingUserProfile);
    }

    /**
     * Updates user's profile photo image URL.
     * SECURITY: Current logged in user must have ROLE_USER.
     * 
     * @param userId
     * @param imageUrl
     * @return
     */
    public UserProfile updateImageUrl(String userId, String imageUrl){
    	UserProfile existingUserProfile=userRepository.findByUserId(userId);
    	if (existingUserProfile == null) {
			throw new UsernameNotFoundException("Didnot find the user");
		}
    	existingUserProfile.setImageUrl(imageUrl);
    	return update(existingUserProfile);
    }
	
}
