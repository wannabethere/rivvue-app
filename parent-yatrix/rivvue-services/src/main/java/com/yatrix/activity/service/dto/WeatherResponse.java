package com.yatrix.activity.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer status;
	
	String message;
	
	List<WeatherDetails> weatherDetails;

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

	public List<WeatherDetails> getWeatherDetails() {
		return weatherDetails;
	}

	public void setWeatherDetails(List<WeatherDetails> weatherDetails) {
		this.weatherDetails = weatherDetails;
	}
	
	public void addWeatherDetails(WeatherDetails weatherDetail){
		if(weatherDetails == null){
			weatherDetails = new ArrayList<WeatherDetails>();
		}
		
		weatherDetails.add(weatherDetail);
	}
	
}
