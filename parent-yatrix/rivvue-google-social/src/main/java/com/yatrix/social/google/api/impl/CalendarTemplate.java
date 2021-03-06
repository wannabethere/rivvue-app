package com.yatrix.social.google.api.impl;

import java.net.URI;

import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.yatrix.social.google.api.CalendarOperations;
import com.yatrix.social.google.api.CalendarProfile;
import com.yatrix.social.google.api.Google;

public class CalendarTemplate implements CalendarOperations {
	  private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();
	 
	  private final RestTemplate template;
	  private final boolean authorized;
	 
	  public CalendarTemplate(final RestTemplate template, final boolean authorized) {
	    this.template = template;
	    this.authorized = authorized;
	  }
	 
	  public CalendarProfile getCalendarProfile() {
	    requireAuthorization();
	    return template.getForObject(buildUri("userinfo"), CalendarProfile.class);
	  }
	 
	  protected void requireAuthorization() {
	    if (!authorized) {
	      throw new MissingAuthorizationException("google");
	    }
	  }
	 
	  protected URI buildUri(final String path) {
	    return buildUri(path, EMPTY_PARAMETERS);
	  }
	 
	  protected URI buildUri(final String path, final String parameterName, final String parameterValue) {
	    final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
	    parameters.set(parameterName, parameterValue);
	    return buildUri(path, parameters);
	  }
	 
	  protected URI buildUri(final String path, final MultiValueMap<String, String> parameters) {
	    return URIBuilder.fromUri(Google.API_URL_BASE + path).queryParams(parameters).build();
	  }
	}