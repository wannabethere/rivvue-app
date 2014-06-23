package com.yatrix.activity.store.utils;

import java.util.Arrays;
import java.util.List;

public enum Categories {

	CLASSES("CLASSES"),
	EVENT("EVENT"),
	TOURNAMENTS("TOURNAMENTS"),
	CAMPS("CAMPS"),
	RACES("RACES"),
	TRAIL_HEADS("TRAIL HEADS"),
	LEAGUES("LEAGUES"),
	ARTICLES("ARTICLES"),
	MEMBERSHIPS("MEMBERSHIPS"),
	CLINICS("CLINICS"),
	CONFERENCES("CONFERENCES"),
	LESSONS("LESSONS"),
	EXPERTS("EXPERTS"),
	PROGRAMS("PROGRAMS"),
	WORKSHOPS("WORKSHOPS"),
	MEETINGS("MEETINGS"),
	SCHOOLS("SCHOOLS"),
	PLACE("PLACE"),
	TRAINING_PLANS("TRAINING PLANS"),
	MEETUPS("MEETUPS"),
	CREATIVE_WORK("CREATIVE WORK"),
	TICKETS("TICKETS"),
	VIRTUAL_EVENTS("VIRTUAL EVENTS"),
	CLUBS("CLUBS");

	private Categories(String pCategory){
		category = pCategory;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public static List<Categories> getAllCategories(){
		return Arrays.asList(Categories.values());
	}
	
	String category;

}
