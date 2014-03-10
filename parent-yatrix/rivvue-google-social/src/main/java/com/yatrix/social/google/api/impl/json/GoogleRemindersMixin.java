package com.yatrix.social.google.api.impl.json;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yatrix.social.google.api.GoogleOverrides;

@JsonIgnoreProperties
public abstract class GoogleRemindersMixin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -22318417851297904L;

	@JsonCreator
	public GoogleRemindersMixin() {
	}
	
	@SuppressWarnings("unused")
	@JsonProperty("overrides")
	private List<GoogleOverrides> overrides;

}
