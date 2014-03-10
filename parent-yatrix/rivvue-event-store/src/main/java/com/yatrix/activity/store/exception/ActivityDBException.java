package com.yatrix.activity.store.exception;


public class ActivityDBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ActivityDBException(String message) {
		super(message);
	}

	public ActivityDBException(String message, Throwable cause) {
		super(message, cause);
	}
   
}

