package com.yatrix.activity.store.mongo.service.impl;

import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.repository.ProfileRepository;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
      return null;
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
      return null;
    }

    profile.setId(existingUser.getId());
    profile.setSignificantOther(existingUser.getSignificantOther());

    return userRepository.save(profile);
  }
  
  public List<UserProfile> getUserProfilesIn(String[] userIds){
	  return userRepository.findByUserIdIn(userIds);
  }
}
