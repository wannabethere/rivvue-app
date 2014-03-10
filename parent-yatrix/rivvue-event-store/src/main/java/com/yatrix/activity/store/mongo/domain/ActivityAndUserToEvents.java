package com.yatrix.activity.store.mongo.domain;

import java.util.List;

public class ActivityAndUserToEvents {
	
	private UserActivity userActivity;
	private List<UserToEvents> userToEventsList;
	
	public ActivityAndUserToEvents(){
		
	}
	
	public ActivityAndUserToEvents(UserActivity pUserActivity, List<UserToEvents> pUserToEventsList){
		this.userActivity = pUserActivity;
		this.userToEventsList = pUserToEventsList;
	}

	public UserActivity getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(UserActivity userActivity) {
		this.userActivity = userActivity;
	}

	public List<UserToEvents> getUserToEventsList() {
		return userToEventsList;
	}

	public void setUserToEventsList(List<UserToEvents> userToEventsList) {
		this.userToEventsList = userToEventsList;
	}

	
}
