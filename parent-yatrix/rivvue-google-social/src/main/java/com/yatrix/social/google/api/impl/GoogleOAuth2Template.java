package com.yatrix.social.google.api.impl;

import org.springframework.social.oauth2.OAuth2Template;

public class GoogleOAuth2Template extends OAuth2Template {
	  private static final String AUTHORIZE_URL = "https://accounts.google.com/o/oauth2/auth";
	  private static final String ACCESS_TOKEN_URL = "https://accounts.google.com/o/oauth2/token";
	 
	  public GoogleOAuth2Template(final String clientId, final String clientSecret) {
	    super(clientId, clientSecret, AUTHORIZE_URL, ACCESS_TOKEN_URL);
	    setUseParametersForClientAuthentication(true);
	  }
	}