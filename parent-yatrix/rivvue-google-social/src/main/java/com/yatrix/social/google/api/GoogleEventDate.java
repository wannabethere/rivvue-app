package com.yatrix.social.google.api;

import java.util.Date;


public class GoogleEventDate {
	
	public GoogleEventDate() {
	}
	
	private Date dateTime;
	
	private String timeZone;

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date date) {
		this.dateTime = date;
	}

}
