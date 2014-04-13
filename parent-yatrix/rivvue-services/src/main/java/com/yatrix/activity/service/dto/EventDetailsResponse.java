package com.yatrix.activity.service.dto;

import java.util.List;

import com.yatrix.activity.store.mongo.domain.Comment;

public class EventDetailsResponse {
	
	private Integer status;
	
	private String message;
	
	private EventDto eventDto;
	
	/**
	 * TODO: Check with mobile team and they feel it too  heavy, create another response.
	 */
	private ProfileListDto friendsList;

	private List<Comment> comments;
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public EventDto getEventDto() {
		return eventDto;
	}

	public void setEventDto(EventDto eventDto) {
		this.eventDto = eventDto;
	}

	public ProfileListDto getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(ProfileListDto friendsList) {
		this.friendsList = friendsList;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
