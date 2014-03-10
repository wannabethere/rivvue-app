package com.yatrix.activity.ext.service;

import com.yatrix.activity.ext.domain.yelp.PlaceDetails;
import com.yatrix.activity.ext.domain.yelp.PlacesException;

public interface IYelpPlacesService {
	
	public PlaceDetails loadPlaceDetails(String term,String location) throws PlacesException;

}
