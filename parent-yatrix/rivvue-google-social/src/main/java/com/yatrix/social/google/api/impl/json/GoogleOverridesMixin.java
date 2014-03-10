package com.yatrix.social.google.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public abstract class GoogleOverridesMixin{
	
	@JsonCreator
	GoogleOverridesMixin(){
		
	}
	
	@SuppressWarnings("unused")
	@JsonProperty("method")
	private String method;
	
	@SuppressWarnings("unused")
	@JsonProperty("minutes")
	private Integer minutes;	
}
