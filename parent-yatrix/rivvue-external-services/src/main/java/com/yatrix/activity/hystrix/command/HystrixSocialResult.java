package com.yatrix.activity.hystrix.command;

public class HystrixSocialResult {
	
	Boolean success;
	String eventId;
	String message;
	
	public HystrixSocialResult(Boolean success, String eventId, String message) {
		this.success = success;
		this.eventId = eventId;
		this.message = message;
	}

	public Boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public String getEventId() {
		return eventId;
	}
	
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
