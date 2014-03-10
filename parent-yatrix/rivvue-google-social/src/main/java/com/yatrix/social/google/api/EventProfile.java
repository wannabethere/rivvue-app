package com.yatrix.social.google.api;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EventProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6630304302074384118L;
	private String id;
	private Date startDate;
	private Date endDate;
	private List<GoogleEventAttendees> attendees;
	
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<GoogleEventAttendees> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<GoogleEventAttendees> attendees) {
		this.attendees = attendees;
	}

	public GoogleReminders getReminders() {
		return reminders;
	}

	public void setReminders(GoogleReminders reminders) {
		this.reminders = reminders;
	}

	private GoogleReminders reminders;
	
	  public String getId() {
		return id;
	}

	public EventProfile(final String id) {
		    this.id = id;
	  }
	
}
