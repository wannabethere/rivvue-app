package com.yatrix.activity.ext.domain.yelp;

public class PlacesException extends Exception {

	private static final long serialVersionUID = 433629765655711368L;

	public PlacesException(String message) {
		super(message);
	}

	public PlacesException(String message, Throwable cause) {
		super(message, cause);
	}
}
