package com.yatrix.activity.store.utils;

import java.util.Arrays;
import java.util.List;

public enum Activities {
	
	RUNNING("RUNNING"), CYCLING("CYCLING"), SWIMMING("SWIMMING"), TRIATHLONS("TRIATHLONS"), DUATHLONS("DUATHLONS"), 
	HIKING("HIKING"), WALKING("WALKING"), MOUNTAIN_BIKING("MOUNTAIN BIKING"), JOGGING("JOGGING"), ROCK_CLIMBING("ROCK CLIMBING"), 
	CANYONEERING("CANYONEERING");
	
	private Activities(String pActivity){
		activity = pActivity;
	}
	
	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public static List<Activities> getAllActivities(){

		return Arrays.asList(Activities.values());
	}

	String activity;

}
