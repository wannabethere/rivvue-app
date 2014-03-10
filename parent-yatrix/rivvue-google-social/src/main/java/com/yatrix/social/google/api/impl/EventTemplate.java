package com.yatrix.social.google.api.impl;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.social.MissingAuthorizationException;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.yatrix.social.google.api.EventOperations;
import com.yatrix.social.google.api.EventProfile;
import com.yatrix.social.google.api.Google;
import com.yatrix.social.google.api.GoogleEventAttendees;
import com.yatrix.social.google.api.GoogleEventDate;

public class EventTemplate implements EventOperations {
	  private static final LinkedMultiValueMap<String, String> EMPTY_PARAMETERS = new LinkedMultiValueMap<String, String>();
	 
	  private final RestTemplate template;
	  private final boolean authorized;
	 
	  public EventTemplate(final RestTemplate template, final boolean authorized) {
	    this.template = template;
	    this.authorized = authorized;
	  }
	 
	  public EventProfile getEventProfile() {
	    requireAuthorization();
	    return template.getForObject(buildUri("userinfo"), EventProfile.class);
	  }
	  
	  public String createEvent(String startDate, String endDate, List<String> attendees, String location, String message) {
		    requireAuthorization();
		    
			GoogleEventDate start = new GoogleEventDate();
			java.util.Calendar cal = java.util.Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			try{
				cal.setTime(format.parse(startDate));
			}catch(ParseException pe){
				cal.setTime(new Date());
			}
			start.setDateTime(cal.getTime());
			start.setTimeZone("GMT");
			
			GoogleEventDate end = new GoogleEventDate();
			try{
				cal.setTime(format.parse(endDate));
				
			}catch(ParseException pe){
				cal.add(java.util.Calendar.HOUR, 2);
			}
			end.setDateTime(cal.getTime());
			end.setTimeZone("GMT");
			
			/*GoogleReminders reminders = new GoogleReminders();
			GoogleOverrides overrides = new GoogleOverrides();
			overrides.setMethod("email");
			overrides.setMinutes(30);
			List<GoogleOverrides> overrideList = new ArrayList<GoogleOverrides>();
			overrideList.add(overrides);
			
			reminders.setOverrides(overrideList);*/
			
			List<GoogleEventAttendees> eventAttendees = new ArrayList<GoogleEventAttendees>();
			GoogleEventAttendees attendee = null;
			for (String attendeeStr : attendees) {
				attendee = new GoogleEventAttendees();
				attendee.setEmail(attendeeStr);
				eventAttendees.add(attendee);
			}
		    
		    //JsonNode json = template.getForObject(URIBuilder.fromUri(Google.API_URL_CALENDAR + "/users/me/calendarList").build(), JsonNode.class);
		    //System.out.println("JSON: " + json.toString());
		    
		    final Map<String, Object> parameters = new HashMap<String, Object>();
		    parameters.put("start", start);
		    parameters.put("end", end);
		    parameters.put("attendees", eventAttendees.toArray(new GoogleEventAttendees[0]));
		    parameters.put("location", location);
		    parameters.put("description", message);
		   // parameters.put("reminders", reminders);
		    
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
		    HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String,Object>>(parameters, headers);
		    
		    URI uri = URIBuilder.fromUri(Google.API_URL_CALENDAR + "/calendars/primary/events" ).build();

		    @SuppressWarnings("unchecked")
			Map<String, Object> eventAttributes =  template.postForObject(uri, request, Map.class);
		    		    
		    return (String)eventAttributes.get("id");
		  }
	 
	  protected void requireAuthorization() {
	    if (!authorized) {
	      throw new MissingAuthorizationException("google");
	    }
	  }
	 
	  protected URI buildUri(final String path) {
	    return buildUri(path, EMPTY_PARAMETERS);
	  }
	 
	  protected URI buildUri(final String path, final String parameterName, final String parameterValue) {
	    final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
	    parameters.set(parameterName, parameterValue);
	    return buildUri(path, parameters);
	  }
	 
	  protected URI buildUri(final String path, final MultiValueMap<String, String> parameters) {
	    return URIBuilder.fromUri(Google.API_URL_BASE + path).queryParams(parameters).build();
	  }
	}