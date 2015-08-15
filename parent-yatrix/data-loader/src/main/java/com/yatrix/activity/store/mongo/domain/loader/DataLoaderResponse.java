package com.yatrix.activity.store.mongo.domain.loader;

import java.util.ArrayList;
import java.util.List;

public class DataLoaderResponse {

	List<String> ampResponse;
	
	String yelpResponse;

	public List<String> getAmpResponse() {
		return ampResponse;
	}

	public void setAmpResponse(List<String> ampResponse) {
		this.ampResponse = ampResponse;
	}

	public String getYelpResponse() {
		return yelpResponse;
	}

	public void setYelpResponse(String yelpResponse) {
		this.yelpResponse = yelpResponse;
	}
	
	public void addAmpResponse(String response){
		if(ampResponse == null){
			ampResponse = new ArrayList<String>();
		}
		
		ampResponse.add(response);
	}
	
}
