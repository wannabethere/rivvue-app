package com.yatrix.social.google.api;

import java.io.Serializable;

public class GoogleEventAttendees implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1850567323026300889L;
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
