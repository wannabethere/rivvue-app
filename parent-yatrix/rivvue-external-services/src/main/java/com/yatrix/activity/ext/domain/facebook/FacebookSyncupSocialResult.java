package com.yatrix.activity.ext.domain.facebook;

import com.yatrix.activity.store.mongo.domain.UserActivity;



public class FacebookSyncupSocialResult {
	
	private Boolean success;
	private UserActivity event;
	private String message;
	
	public FacebookSyncupSocialResult(Boolean success, UserActivity event, String message) {
		this.success = success;
		this.event = event;
		this.message = message;
	}

	public Boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public UserActivity getEvent() {
		return event;
	}
	
	public void setEvent(UserActivity event) {
		this.event = event;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
