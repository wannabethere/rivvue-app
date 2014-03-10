package com.yatrix.social.google.api.impl.json;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yatrix.social.google.api.JsonDateSerializer;

abstract class GoogleEventDateMixin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5695699814166388010L;

	GoogleEventDateMixin(){
	 }
	
	 @SuppressWarnings("unused")
	@JsonProperty("dateTime")
	 @JsonSerialize(using=JsonDateSerializer.class)
	private Date dateTime;
	 
	 @SuppressWarnings("unused")
	@JsonProperty("timeZone")
	private String timeZone;

}

