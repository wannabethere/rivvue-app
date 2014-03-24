package com.yatrix.activity.store.mongo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.service.IUserToEventsService;


public class UserToEventsService implements IUserToEventsService {

	@Override
	public List<UserActivity> getUserActivitiesForUser() {
		List<UserActivity> eventList = new ArrayList<UserActivity>();
		return eventList;
	}

}
