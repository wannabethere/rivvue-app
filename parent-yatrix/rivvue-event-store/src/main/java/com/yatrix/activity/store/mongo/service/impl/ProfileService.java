package com.yatrix.activity.store.mongo.service.impl;

import com.yatrix.activity.store.mongo.domain.Reference;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserConnections;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.Reference.REFERENCETYPE;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;
import com.yatrix.activity.store.mongo.domain.UserProfile.PSTATUS;
import com.yatrix.activity.store.mongo.repository.AppConnectionsRepository;
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
	
	@Autowired
	private AppConnectionsRepository connectionsRepository;

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
		UserProfile existingUser = userRepository.findByUserId(profile.getUserId());
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
		this.addFriend(parentProfile, pf);
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
		this.addFriend(parentProfile, existingFriendPf);
		return;
	}
	
	
	public UserProfile addFriend(UserProfile parentProfile, UserProfile pf){
		if(parentProfile==null){
			throw new UsernameNotFoundException("Didnot find the user");
		}
		pf.setParentId(parentProfile.getUserId());
		Reference ref= new Reference(REFERENCETYPE.CONTACTS, pf.getUserId(), parentProfile.getUserId());
		UserProfile existingFriendPf=null;
		addConnections(parentProfile, ref);
		//update(parentProfile);
		//Now we will also update the friends profile to be connected to the parent ref.
		//This way when the user becomes the app user then we have already his friends.
		existingFriendPf=userRepository.findByUserId(pf.getId());
		ref= new Reference(REFERENCETYPE.CONTACTS, parentProfile.getUserId(), pf.getUserId());
		if(existingFriendPf!=null){
			addConnections(parentProfile, ref);
			//update(existingFriendPf);
		}
		else{
			//I didnot find the User Id.
			create(pf);
			addConnections(pf, ref);
			//pf.getSocialIds().add(ref);
		}
		return parentProfile;
	}
	
	
	private void addConnections(UserProfile userProfile,Reference ref){
		UserConnections parentConnection=connectionsRepository.findByProfileUserId(userProfile.getUserId());
		if(parentConnection==null){
			parentConnection=new UserConnections();
			parentConnection.setProfileUserId(userProfile.getUserId());
		}
		parentConnection.addReference(ref);
		connectionsRepository.save(parentConnection);
	}
	
	private void removeFriendConnection(String profileUserId, String refId){
		UserConnections parentConnection=connectionsRepository.findByProfileUserId(profileUserId);
		parentConnection.removeReference(refId);
	}
	
	public UserProfile removeFriend(String parentId, String refId){
		UserProfile parentProfile = userRepository.findByParentId(parentId);
		if (parentProfile == null) {
			parentProfile=userRepository.findByUserId(parentId);
			if(parentProfile==null){
				throw new UsernameNotFoundException("Didnot find the user");
			}
		}
		removeFriendConnection(parentProfile.getUserId(), refId);
		return parentProfile;
	}
	
	/**
	 * TODO: Query only the values u are looking for.
	 * Hopefully this can be done as a query to improve performance.
	 * @param parentId
	 * @return
	 */
	public List<String> getMyContactIds(String parentId){
		UserProfile parentProfile = userRepository.findByUserId(parentId);
		if (parentProfile == null) {
			throw new UsernameNotFoundException("Didnot find the user");
		}
		List<String> ids=new ArrayList<String>();
		UserConnections parentConnection=connectionsRepository.findByProfileUserId(parentProfile.getUserId());
		if(parentConnection==null){
			return ids;
		}
		for(String key: parentConnection.getConnections().keySet()){
			ids.add(key);
		}
		return ids;
	}
	
	/**
	 * 
	 * This should not be used for the real application.
	 * Ajax call should be used to load the data as it finishes.
	 * @param parentId
	 * @return
	 */
	public List<UserProfile> getMyContacts(String parentId){
		List<String> ids=getMyContactIds(parentId);
		String[] sids= ids.toArray(new String[ids.size()]);
		return getUserProfilesIn(sids);
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
