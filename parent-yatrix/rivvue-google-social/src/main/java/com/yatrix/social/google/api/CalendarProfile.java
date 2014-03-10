package com.yatrix.social.google.api;

import java.io.Serializable;

public class CalendarProfile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7786230107071707812L;
	private final String id;
	
	  public String getId() {
		return id;
	}

	public CalendarProfile(final String id) {
		    this.id = id;
	  }

}
