package com.yatrix.activity.store.fb.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.yatrix.activity.store.mongo.domain.ActivityComment;

public class FacebookPost implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private  String id;

	private FacebookReference from;

	private Date createdTime;

	private Date updatedTime;
	
	private String message;
	
	private List<ActivityComment> comments;
	
	public FacebookPost(){
		
	}
	
	public FacebookPost(String pId, FacebookReference pFrom, Date pCreatedDate, Date pUpdatedTime, String pMessage, List<ActivityComment> pComments){
		this.id = pId;
		this.from = pFrom;
		this.createdTime = pCreatedDate;
		this.updatedTime = pUpdatedTime;
		this.message = pMessage;
		this.comments = pComments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FacebookReference getFrom() {
		return from;
	}

	public void setFrom(FacebookReference from) {
		this.from = from;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<ActivityComment> getComments() {
		return comments;
	}

	public void setComments(List<ActivityComment> comments) {
		this.comments = comments;
	}

}
