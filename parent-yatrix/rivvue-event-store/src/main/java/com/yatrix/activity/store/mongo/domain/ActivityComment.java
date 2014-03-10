package com.yatrix.activity.store.mongo.domain;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yatrix.activity.store.fb.domain.FacebookReference;

@JsonIgnoreProperties(
	    ignoreUnknown = true)
public class ActivityComment implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String message;
	
	private Date createdTime;
	
	private FacebookReference from;
	
	public ActivityComment(){
		
	}
	
	public ActivityComment(String pId, String pMessage, Date pCreatedDate, FacebookReference pFrom){
		this.id = pId;
		this.message = pMessage;
		this.createdTime = pCreatedDate;
		this.from = pFrom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public FacebookReference getFrom() {
		return from;
	}

	public void setFrom(FacebookReference from) {
		this.from = from;
	}
	
}
