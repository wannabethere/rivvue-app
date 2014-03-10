package com.yatrix.activity.store.fb.domain;

import java.io.Serializable;

public class FacebookInvitee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum FacebookRsvpStatus {
		ATTENDING, DECLINED, UNSURE, NOT_REPLIED;
	}
	
	private String id;
	
	private String name;
	
	private FacebookRsvpStatus rsvpStatus;
	
	public FacebookInvitee(){
		
	}
	
	public FacebookInvitee(String id, String name, FacebookRsvpStatus rsvpStatus) {
		this.id = id;
		this.name = name;
		this.rsvpStatus = rsvpStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FacebookRsvpStatus getRsvpStatus() {
		return rsvpStatus;
	}

	public void setRsvpStatus(FacebookRsvpStatus rsvpStatus) {
		this.rsvpStatus = rsvpStatus;
	}	
}
