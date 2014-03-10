package com.yatrix.social.google.api;


import java.util.List;

public interface EventOperations {
	
	EventProfile getEventProfile();
	
	String createEvent(String startDate, String endDate, List<String> attendees, String location, String message);

}
