package com.yatrix.activity.service.signin.utils;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.yatrix.social.google.api.Google;
import com.yatrix.social.google.api.connect.GoogleServiceProvider;

public class GoogleConnectionFactory extends OAuth2ConnectionFactory<Google> {

	/**
	 * Creates a FacebookConnectionFactory for the given application ID and secret.
	 * Using this constructor, no application namespace is set (and therefore Facebook's Open Graph operations cannot be used).
	 * @param appId The application's App ID as assigned by Facebook 
	 * @param appSecret The application's App Secret as assigned by Facebook
	 */
	public GoogleConnectionFactory(String appId, String appSecret) {
		this(appId, appSecret, null);
	}

	/**
	 * Creates a FacebookConnectionFactory for the given application ID, secret, and namespace.
	 * @param appId The application's App ID as assigned by Facebook 
	 * @param appSecret The application's App Secret as assigned by Facebook
	 * @param appNamespace The application's App Namespace as configured with Facebook. Enables use of Open Graph operations.
	 */
	public GoogleConnectionFactory(String appId, String appSecret, String appNamespace) {
		super("google", new GoogleServiceProvider(appId, appSecret), new GoogleAdapter());
	}

}

