package com.yatrix.activity.hystrix.fb.command;

import com.yatrix.activity.store.mongo.domain.UserEvent;

public class FacebookSyncupSocialResult {
	
	private Boolean success;
	private UserEvent event;
	private String message;
	
	public FacebookSyncupSocialResult(Boolean success, UserEvent event, String message) {
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
	
	public UserEvent getEvent() {
		return event;
	}
	
	public void setEvent(UserEvent event) {
		this.event = event;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
