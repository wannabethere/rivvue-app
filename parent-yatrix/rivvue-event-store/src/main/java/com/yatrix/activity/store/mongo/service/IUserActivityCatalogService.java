package com.yatrix.activity.store.mongo.service;

import java.util.List;

import com.yatrix.activity.store.exception.ActivityDBException;
import com.yatrix.activity.store.mongo.domain.Activity;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserActivity.EVENT_STATUS;
import com.yatrix.activity.store.mongo.domain.UserToEvents;

public interface IUserActivityCatalogService {

  List<Activity> searchActivities(String categoryId, String query);

  boolean updateActivity(UserActivity activity);

  UserActivity createActivity(String title, String tags,String categoryId, String subCategoryId, String location, 
		  					  String formattedAddress, String locationLat, String locationLng,
                              String from, String to, String toAppUsers, String access, String start, String end,
                              String message, String place) throws ActivityDBException;

  List<UserActivity> findUserEventsByUser(String uuid);

  // UserActivity findUserEvent(String activityId);

  // List<UserActivity> findActivies(String uuid);

  UserActivity findActivity(String activityId);

	Long countActivies(String uuid);

	List<Activity> searchActivities(String categoryId, String query,
			Integer from, Integer limit);

	List<UserActivity> findActivies(EVENT_STATUS eventStatus, Integer skip, Integer limit);

	void save(UserToEvents userToEvent);

	List<UserActivity> findAllPublicUserEventsByState(String string);

	List<UserActivity> findEventsIAmInvited(String username, String facebookId);

}
