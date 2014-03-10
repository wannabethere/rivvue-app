package com.yatrix.social.google.api.impl.json;

import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
abstract class CalendarProfileMixin {
	 @JsonCreator
	 CalendarProfileMixin(
	      @JsonProperty("id") String id) {
	  }
	 
}
