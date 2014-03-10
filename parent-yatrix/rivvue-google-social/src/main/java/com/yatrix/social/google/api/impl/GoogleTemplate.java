package com.yatrix.social.google.api.impl;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yatrix.social.google.api.CalendarOperations;
import com.yatrix.social.google.api.EventOperations;
import com.yatrix.social.google.api.Google;
import com.yatrix.social.google.api.UserOperations;
import com.yatrix.social.google.api.impl.json.GoogleModule;

public class GoogleTemplate extends AbstractOAuth2ApiBinding implements Google {

	private UserOperations userOperations;

	private  CalendarOperations calendarOperations;

	private EventOperations eventOperations;

	public GoogleTemplate() {
		initialize();
	}

	public GoogleTemplate(final String accessToken) {
		super(accessToken);
		initialize();
	}

	public UserOperations userOperations() {
		return userOperations;
	}

	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		final MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new GoogleModule());
		converter.setObjectMapper(objectMapper);
		return converter;
	}

	private void initialize() {
		userOperations = new UserTemplate(getRestTemplate(), isAuthorized());
		calendarOperations = new CalendarTemplate(getRestTemplate(), isAuthorized());
		eventOperations = new EventTemplate(getRestTemplate(), isAuthorized());
	}

	public CalendarOperations calendarOperations() {
		return calendarOperations;
	}


	public EventOperations eventOperations() {
		return eventOperations;
	}
}