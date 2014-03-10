package com.yatrix.activity.store.mongo.service;

import java.util.List;

import com.yatrix.activity.store.mongo.domain.UserActivity;

public interface IUserToEventsService {
	
	public List<UserActivity> getUserActivitiesForUser();

}
