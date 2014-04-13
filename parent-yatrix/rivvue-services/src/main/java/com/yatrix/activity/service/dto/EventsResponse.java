package com.yatrix.activity.service.dto;

import java.util.List;

public class EventsResponse {
	
	private Integer status;
	
	private String message;
	
	private List<EventDto> events;

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

	public List<EventDto> getEvents() {
		return events;
	}

	public void setEvents(List<EventDto> events) {
		this.events = events;
	}
}
