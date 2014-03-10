package com.yatrix.activity.ext.service.impl;

import com.yatrix.activity.ext.domain.google.AutocompletionResponse;
import com.yatrix.activity.ext.domain.google.PlaceDetails;
import com.yatrix.activity.ext.domain.google.PlaceDetailsResponse;
import com.yatrix.activity.ext.domain.google.PlacesException;
import com.yatrix.activity.ext.domain.google.PlacesRequest;
import com.yatrix.activity.ext.domain.google.PlacesSearchResults;
import com.yatrix.activity.ext.domain.google.PlacesSearchResults.STATUSRESULT;
import com.yatrix.activity.ext.service.IGooglePlacesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GooglePlacesService implements IGooglePlacesService{

	private static final Logger logger = LoggerFactory.getLogger(GooglePlacesService.class);
	public final static String dummyAccessToken = "CAAIlWa556u8BAP1O1bcsUonbt89hZBmEivNAb0RiekdQn7B1eMcP5ycdn402hzvOmfBdzRLPV980MryuxKnpYOAIUGZBCpsMQgODnnnDNYjlEj8UhW5fl79MZB5339t1oyNujolQg3uWAcZBkNPN";
	public static final String PLACE_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?reference={searchId}&sensor=false&key={key}";
	public static final String PLACE_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query={query}&sensor=false&key={key}";
	public static final String CITY_AUTO_COMPLETE_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json?input={query}&types=(cities)&sensor=false&key={key}";

	@Autowired
	private RestTemplate restTemplate;

	// Set the API key From a different.
	@SuppressWarnings("unused")
	private String apiKey;

	@Cacheable(value = "googlePlaces")
	@Override
	public PlaceDetails loadPlaceDetails(String searchId) throws Exception {
		PlaceDetailsResponse response = restTemplate.getForObject(PLACE_DETAILS_URL,
				PlaceDetailsResponse.class, searchId, "AIzaSyCxvCnxIHYcXxw0QvhVzP9fkIVIvBz1nZI");
		if (response.getResult() != null) {
			logger.info("google response**********************************" + response.getResult());
			return response.getResult();
		} else {
			throw new PlacesException("Unable to find details for reference: " + searchId);
		}
	}

	@Cacheable(value = "googlePlaces")
	@Override
	public PlacesSearchResults findActivityPlace(PlacesRequest request) throws Exception {
		// At this point not bothered about types. But will consider it as an enhancement.
		PlacesSearchResults response = restTemplate.getForObject(PLACE_SEARCH_URL,
				PlacesSearchResults.class, request.getQuery(), "AIzaSyCxvCnxIHYcXxw0QvhVzP9fkIVIvBz1nZI");
		if (response.getStatus() == STATUSRESULT.OK) {
			return response;
		} else {
			throw new PlacesException("Unable to find details for reference: " + request.getQuery());
		}

	}

	@Cacheable(value = "googlePlaces")
	@Override
	public AutocompletionResponse autoCompleteCities(String cityName) throws Exception {
		AutocompletionResponse response = restTemplate.getForObject(CITY_AUTO_COMPLETE_URL,
				AutocompletionResponse.class, cityName, "AIzaSyCxvCnxIHYcXxw0QvhVzP9fkIVIvBz1nZI");
		if (response.getStatus() == AutocompletionResponse.STATUSRESULT.OK) {
			return response;
		} else {
			throw new PlacesException("Unable to find details for reference: " + cityName);
		}

	}
}
