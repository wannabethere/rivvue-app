package com.yatrix.activity.service.dto;

public class WeatherDetails {
	
	String eventId;
	
	//TODO: Do we build Weather response per event? or per zip?
	Integer zipcode;
	
	//TODO: Confirm with Sameer on weather it is Float or String. Believe this contains F or C, so defining this as String
	String weather;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	
}
