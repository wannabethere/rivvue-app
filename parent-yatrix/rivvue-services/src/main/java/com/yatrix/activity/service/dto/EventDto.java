package com.yatrix.activity.service.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yatrix.activity.store.fb.domain.FacebookInvitee;

public class EventDto {
	private String id;
	private String categoryId;
	private String categoryName;
	private String subCategoryId;
	private String start;
	private String end;
	private String title;
	private String description;
	private String tags;
	private Date startDate;
	private Date endDate;
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
	private long duration;
	private List<FacebookInvitee> facebookAccepted = new ArrayList<FacebookInvitee>();
	
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
	public List<FacebookInvitee> getFacebookAccepted() {
		return facebookAccepted;
	}
	public void setFacebookAccepted(List<FacebookInvitee> facebookAccepted) {
		this.facebookAccepted = facebookAccepted;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public void setDuration(){
		this.duration=((endDate.getTime() -  startDate.getTime())/(1000 * 60 * 60));
	}
	
	public Long getDuration(){
		this.duration=((endDate.getTime() -  startDate.getTime())/(1000 * 60 * 60));
		return duration;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
}
