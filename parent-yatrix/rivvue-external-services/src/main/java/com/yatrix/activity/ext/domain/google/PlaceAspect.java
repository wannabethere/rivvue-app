package com.yatrix.activity.ext.domain.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceAspect {

	@JsonProperty("rating")
	private int rating;
	
	@JsonProperty("type")
	private String type;
	
	public int getRating(){
		return rating;
	}
	public int setRating(int rating){
		return this.rating=rating;
	}
	public String getType(){
		return type;
	}
	public String setType(String  type){
		return this.type=type;
	}

}