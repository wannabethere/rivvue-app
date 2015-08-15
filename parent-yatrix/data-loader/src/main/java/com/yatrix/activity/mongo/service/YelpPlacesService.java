package com.yatrix.activity.mongo.service;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class YelpPlacesService 
{

  public final static String dummyAccessToken = "CAAIlWa556u8BAP1O1bcsUonbt89hZBmEivNAb0RiekdQn7B1eMcP5ycdn402hzvOmfBdzRLPV980MryuxKnpYOAIUGZBCpsMQgODnnnDNYjlEj8UhW5fl79MZB5339t1oyNujolQg3uWAcZBkNPN";
  public static final String YELP_PLACE_DETAILS_URL = "http://api.yelp.com/v2/search";
  public static final String YELP_PLACE_REVIEWS_URL = "http://api.yelp.com/v2/business";
  public final static String consumerKey = "wpmjL79LNj6KvQ6HCEll2A";
  public final static String consumerSecret = "11hnqzFdFI0S6hnalC1QukvSRfg";
  public final static String token = "904eP0Me0CH1DVtLygVPErZp7WOucoot";
  public final static String tokenSecret = "vBRiX_Dt8OL-h0w9DcJckkBmU7Y";

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

  public String loadReviews(String term, Float latitude, Float longitude)  {

    OAuthRequest request = new OAuthRequest(Verb.GET, YELP_PLACE_DETAILS_URL);
    request.addQuerystringParameter("term", term);
    request.addQuerystringParameter("ll", latitude + "," + longitude);
    this.service.signRequest(this.accessToken, request);
    
    Response response = request.send();
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(Include.NON_NULL);

    return response.getBody();
  }

}
