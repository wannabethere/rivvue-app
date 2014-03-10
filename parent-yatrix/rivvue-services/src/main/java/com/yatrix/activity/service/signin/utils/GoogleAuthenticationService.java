package com.yatrix.activity.service.signin.utils;

import org.springframework.social.security.provider.OAuth2AuthenticationService;
import com.yatrix.social.google.api.Google;


public class GoogleAuthenticationService extends OAuth2AuthenticationService<Google> {

	public GoogleAuthenticationService(String apiKey, String appSecret) {
		super(new GoogleConnectionFactory(apiKey, appSecret));
	}

}