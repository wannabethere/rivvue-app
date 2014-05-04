package com.yatrix.activity.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RatingResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	
	private String message;
	
	private List<RatingDetails> ratings;

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

	public List<RatingDetails> getRatings() {
		return ratings;
	}

	public void setRatings(List<RatingDetails> ratings) {
		this.ratings = ratings;
	}

	public void addRatingDetails(RatingDetails ratingDetails) {
		if(ratings == null){
			ratings = new ArrayList<RatingDetails>();
		}
		
		ratings.add(ratingDetails);
		
	}
	
}
