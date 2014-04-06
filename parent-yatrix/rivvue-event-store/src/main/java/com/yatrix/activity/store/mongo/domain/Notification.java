package com.yatrix.activity.store.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "UserNotification")
public class Notification extends Item{

	
	public static enum EVENT_STATUS {
		PENDING, PROCESSING, INVITED,ME
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 2566568343394931885L;

}
