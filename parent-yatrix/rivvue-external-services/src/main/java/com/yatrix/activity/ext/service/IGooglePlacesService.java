package com.yatrix.activity.ext.service;

import org.springframework.stereotype.Service;

import com.yatrix.activity.ext.domain.google.AutocompletionResponse;
import com.yatrix.activity.ext.domain.google.PlaceDetails;
import com.yatrix.activity.ext.domain.google.PlacesRequest;
import com.yatrix.activity.ext.domain.google.PlacesSearchResults;

@Service
public interface IGooglePlacesService {
	
	PlaceDetails loadPlaceDetails(String searchId) throws Exception;
	
	PlacesSearchResults findActivityPlace(PlacesRequest request) throws Exception;

	AutocompletionResponse autoCompleteCities(String cityName) throws Exception;

}
