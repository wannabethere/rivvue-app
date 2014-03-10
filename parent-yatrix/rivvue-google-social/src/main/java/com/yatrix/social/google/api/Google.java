package com.yatrix.social.google.api;


import org.springframework.social.ApiBinding;

public interface Google extends ApiBinding {
	String API_URL_BASE = "https://www.googleapis.com/oauth2/v3/";
	String API_URL_CALENDAR = "https://www.googleapis.com/calendar/v3";
	 
	  UserOperations userOperations();
	  
	  CalendarOperations calendarOperations();
	  
	  EventOperations eventOperations();
}
