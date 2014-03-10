package com.yatrix.social.google.api.impl.json;

import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("unused")
@JsonIgnoreProperties
abstract class UserProfileMixin {
	 @JsonCreator
	 UserProfileMixin(
	      @JsonProperty("id") String id) {
	  }
	 
	@JsonProperty("name")
	  private String name;
	 
	  @JsonProperty("given_name")
	  private String givenName;
	 
	  @JsonProperty("family_name")
	  private String familyName;
	 
	  @JsonProperty("gender")
	  private String gender;
	 
	  @JsonProperty("locale")
	  private Locale locale;
	 
	  @JsonProperty("profile")
	  private String link;
	 
	  @JsonProperty("picture")
	  private String pictureUrl;
	  
	  @JsonProperty("email")
	  private String email;
	  
	  @JsonProperty("email_verified")
	  private Boolean emailVerified;
	  
	  @JsonProperty("sub")
	  private String sub;
	  
	  @JsonProperty("birthdate")
	  private Date birthDate;
}
