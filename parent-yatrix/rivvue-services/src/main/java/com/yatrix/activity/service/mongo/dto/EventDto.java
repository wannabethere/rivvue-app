package com.yatrix.activity.service.mongo.dto;

public class EventDto {
	private String id;
	private String categoryId;
	private String categoryName;
	private String subCategoryId;
	private String start;
	private String end;
	private String location;
	private String from;
	private String to;
	private String toAppUsers;
	private String message;
	private String access;
	private String place;
	private String formattedAddress;
	private String locationLat;
	private String locationLng;
	
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(String subCategory) {
		this.subCategoryId = subCategory;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String endTime) {
		this.end = endTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String startTime) {
		this.start = startTime;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getFormattedAddress() {
		return formattedAddress;
	}
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
	public String getLocationLat() {
		return locationLat;
	}
	public void setLocationLat(String locationLat) {
		this.locationLat = locationLat;
	}
	public String getLocationLng() {
		return locationLng;
	}
	public void setLocationLng(String locationLng) {
		this.locationLng = locationLng;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getToAppUsers() {
		return toAppUsers;
	}
	public void setToAppUsers(String toAppUsers) {
		this.toAppUsers = toAppUsers;
	}
}
