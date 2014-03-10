package com.yatrix.social.google.api.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.yatrix.social.google.api.Google;
import com.yatrix.social.google.api.impl.GoogleOAuth2Template;
import com.yatrix.social.google.api.impl.GoogleTemplate;

public class GoogleServiceProvider extends AbstractOAuth2ServiceProvider<Google> {
	 
	  public GoogleServiceProvider(final String clientId, final String clientSecret) {
	    super(new GoogleOAuth2Template(clientId, clientSecret));
	  }
	 
	  @Override
	  public Google getApi(final String accessToken) {
	    return new GoogleTemplate(accessToken);
	  }
	}