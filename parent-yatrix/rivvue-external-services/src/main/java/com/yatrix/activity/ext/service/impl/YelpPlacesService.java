package com.yatrix.activity.ext.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yatrix.activity.ext.domain.yelp.PlaceDetails;
import com.yatrix.activity.ext.domain.yelp.PlaceDetailsResponse;
import com.yatrix.activity.ext.domain.yelp.PlacesException;
import com.yatrix.activity.ext.service.IYelpPlacesService;
import com.yatrix.activity.ext.service.utils.YelpApi2;


import java.util.List;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class YelpPlacesService
  implements IYelpPlacesService
{

  public final static String dummyAccessToken = "CAAIlWa556u8BAP1O1bcsUonbt89hZBmEivNAb0RiekdQn7B1eMcP5ycdn402hzvOmfBdzRLPV980MryuxKnpYOAIUGZBCpsMQgODnnnDNYjlEj8UhW5fl79MZB5339t1oyNujolQg3uWAcZBkNPN";
  public static final String PLACE_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?reference={searchId}&sensor=false&key={key}";
  public static final String YELP_PLACE_DETAILS_URL = "http://api.yelp.com/v2/search";
  public static final String YELP_PLACE_REVIEWS_URL = "http://api.yelp.com/v2/business";
  public static final String PLACE_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query={query}&sensor=false&key={key}";
  public final static String consumerKey = "wpmjL79LNj6KvQ6HCEll2A";
  public final static String consumerSecret = "11hnqzFdFI0S6hnalC1QukvSRfg";
  public final static String token = "904eP0Me0CH1DVtLygVPErZp7WOucoot";
  public final static String tokenSecret = "vBRiX_Dt8OL-h0w9DcJckkBmU7Y";

  //TODO: Fix the above properties.
/*  @SuppressWarnings("unused")
  @Autowired
  private RestTemplate restTemplate;

  // Set the API key From a different.
  @SuppressWarnings("unused")
  private String apiKey;*/
  

  private OAuthService service;

  private Token accessToken;

  /**
   * Setup the Yelp API OAuth credentials. OAuth credentials are available from the developer site,
   * under Manage API access (version 2 API).
   * 
   * @param consumerKey
   *          Consumer key
   * @param consumerSecret
   *          Consumer secret
   * @param token
   *          Token
   * @param tokenSecret
   *          Token secret
   */
  public YelpPlacesService() {
    this.service = new ServiceBuilder().provider(YelpApi2.class).apiKey(consumerKey)
      .apiSecret(consumerSecret).build();
    this.accessToken = new Token(token, tokenSecret);
  }

  /**
   * Search with term and location.
   * 
   * @param term
   *          Search term
   * @param latitude
   *          Latitude
   * @param longitude
   *          Longitude
   * @return JSON string response
   */
  @Override
  @Cacheable(
      value = "yelpReviews")
  public PlaceDetails loadPlaceDetails(String term, String location) throws PlacesException {

    OAuthRequest request = new OAuthRequest(Verb.GET, YELP_PLACE_DETAILS_URL);
    request.addQuerystringParameter("term", term);
    request.addQuerystringParameter("location", location);
    // request.addQuerystringParameter("cll", latitude + "," + longitude);
    this.service.signRequest(this.accessToken, request);
    Response response = request.send();
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(Include.NON_NULL);

    PlaceDetailsResponse detailsResponse = null;
    @SuppressWarnings("unused")
    List<PlaceDetails> details = null;
    try {
      detailsResponse = mapper.readValue(response.getBody(), PlaceDetailsResponse.class);
    } catch (java.io.IOException ioe) {
      System.out.println("error :" + ioe.getMessage());
    }
    System.out.println("**************************" + response.getBody());
    List<PlaceDetails> places = null;
    PlaceDetails returnPlace = new PlaceDetails();
    if (detailsResponse != null) {
      places = detailsResponse.getResult();
      for (PlaceDetails place : places) {

        if (place.getName().equals(term)) {
          System.out.println(" ***********************888************************id "
            + place.getId());
          request = new OAuthRequest(Verb.GET, YELP_PLACE_REVIEWS_URL + "/" + place.getId());
          this.service.signRequest(this.accessToken, request);
          response = request.send();
          try {
            returnPlace = mapper.readValue(response.getBody(), PlaceDetails.class);

          } catch (java.io.IOException ioe) {
            System.out.println("error :" + ioe.getMessage());
          }

        }
      }
      //
    } else {
      throw new PlacesException("Unable to find details for reference");
    }

    return returnPlace;
  }

}
