package com.yatrix.social.google.api;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

public class UserProfile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5960561725448350948L;
	private final String id;
	  private String name;
	  private String givenName;
	  private String familyName;
	  private String gender;
	  private Locale locale;
	  private String link;
	  private String pictureUrl;
	  private String email;
	  private Boolean emailVerified;
	  private Date birthDate;
	  private String sub;
	  
	  public String getEmail() {
		return email;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public String getSub() {
		return sub;
	}
	  public UserProfile(final String id) {
	    this.id = id;
	  }
	 
	  public String getId() {
	    return id;
	  }
	 
	  public String getName() {
	    return name;
	  }
	 
	  public String getGivenName() {
	    return givenName;
	  }
	 
	  public String getFamilyName() {
	    return familyName;
	  }
	 
	  public String getGender() {
	    return gender;
	  }
	 
	  public Locale getLocale() {
	    return locale;
	  }
	 
	  public String getLink() {
	    return link;
	  }
	 
	  public String getPictureUrl() {
	    return pictureUrl;
	  }
}
