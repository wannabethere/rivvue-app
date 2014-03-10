package com.yatrix.social.google.api;

import java.io.Serializable;

public class GoogleOverrides implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6329459853911425313L;
	private String method;
	private Integer minutes;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Integer getMinutes() {
		return minutes;
	}
	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}
	
	
}
