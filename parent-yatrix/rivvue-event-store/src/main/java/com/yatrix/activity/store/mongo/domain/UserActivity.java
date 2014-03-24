package com.yatrix.activity.store.mongo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yatrix.activity.store.fb.domain.FacebookInvitee;
import com.yatrix.activity.store.fb.domain.FacebookPost;

@Document(
		collection = "UserEvents")
		public class UserActivity
		extends Message
		{

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 2242068081611181292L;

	public static enum EVENT_STATUS {
		PENDING, PROCESSING, INVITED,ME
	}

	private String title;

	private String categoryId;

	private String subCategory;

	private String displayName;

	private List<String> tags = new ArrayList<String>();
	private List<String> appParticipants = new ArrayList<String>();
	private List<PostMessage> postedMessage = new ArrayList<PostMessage>();
	private List<FacebookInvitee> facebookAccepted = new ArrayList<FacebookInvitee>();
	private List<FacebookInvitee> facebookRejected = new ArrayList<FacebookInvitee>();
	private List<FacebookInvitee> facebookUnsure = new ArrayList<FacebookInvitee>();
	private List<FacebookPost> facebookPosts = new ArrayList<FacebookPost>();
	private List<ActivityComment> appComments = new ArrayList<ActivityComment>();
	private List<ActivityCalendarReference> replies = new ArrayList<ActivityCalendarReference>();
	private Date startTime;
	private Date endTime;
	private String location;
	private String formattedAddress;
	private String locationLat;
	private String locationLng;
	private String facebookEventId;
	private String googleEventId;
	private String facebookFeedId;
	private EVENT_STATUS status;
	private String place;
	private Long duration;

	public void setCategoryId(String id) {
		this.categoryId = id;
	}

	public String getCategoryId() {
		return this.categoryId;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<ActivityCalendarReference> getReplies() {
		return replies;
	}

	public void setReplies(List<ActivityCalendarReference> replies) {
		this.replies = replies;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public List<PostMessage> getPostedMessage() {
		return postedMessage;
	}

	public void setPostedMessage(List<PostMessage> postedMessage) {
		this.postedMessage = postedMessage;
	}

	public String getFacebookEventId() {
		return facebookEventId;
	}

	public String getGoogleEventId() {
		return googleEventId;
	}

	public void setFacebookEventId(String facebookEventId) {
		this.facebookEventId = facebookEventId;
	}

	public void setGoogleEventId(String googleEventId) {
		this.googleEventId = googleEventId;

	}

	public EVENT_STATUS getStatus() {
		return status;
	}

	public void setStatus(EVENT_STATUS status) {
		this.status = status;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPlace() {
		return this.place;
	}

	public List<FacebookInvitee> getFacebookAccepted() {
		return facebookAccepted;
	}

	public void setFacebookAccepted(List<FacebookInvitee> facebookAccepted) {
		this.facebookAccepted = facebookAccepted;
	}

	public List<FacebookInvitee> getFacebookRejected() {
		return facebookRejected;
	}

	public void setFacebookRejected(List<FacebookInvitee> facebookRejected) {
		this.facebookRejected = facebookRejected;
	}

	public List<FacebookInvitee> getFacebookUnsure() {
		return facebookUnsure;
	}

	public void setFacebookUnsure(List<FacebookInvitee> facebookUnsure) {
		this.facebookUnsure = facebookUnsure;
	}

	public List<FacebookPost> getFacebookPosts() {
		return facebookPosts;
	}

	public void setFacebookPosts(List<FacebookPost> facebookPosts) {
		this.facebookPosts = facebookPosts;
	}

	public List<ActivityComment> getAppComments() {
		return appComments;
	}

	public void setAppComments(List<ActivityComment> appComments) {
		this.appComments = appComments;
	}

	public List<String> getAppParticipants() {
		return appParticipants;
	}

	public void setAppParticipants(List<String> appParticipants) {
		this.appParticipants = appParticipants;
	}

	public void setFacebookFeedId(String facebookFeedId) {
		this.facebookFeedId = facebookFeedId;
	}

	public String getFacebookFeedId() {
		return facebookFeedId;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public void setDuration(){
		this.duration=((endTime.getTime() -  startTime.getTime())/(1000 * 60 * 60));
	}
	
	public Long getDuration(){
		this.duration=((endTime.getTime() -  startTime.getTime())/(1000 * 60 * 60));
		return duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((facebookEventId == null) ? 0 : facebookEventId.hashCode());
		result = prime * result + ((googleEventId == null) ? 0 : googleEventId.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((postedMessage == null) ? 0 : postedMessage.hashCode());
		result = prime * result + ((replies == null) ? 0 : replies.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((subCategory == null) ? 0 : subCategory.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserActivity other = (UserActivity)obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (displayName == null) {
			if (other.displayName != null)
				return false;
		} else if (!displayName.equals(other.displayName))
			return false;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (facebookEventId == null) {
			if (other.facebookEventId != null)
				return false;
		} else if (!facebookEventId.equals(other.facebookEventId))
			return false;
		if (googleEventId == null) {
			if (other.googleEventId != null)
				return false;
		} else if (!googleEventId.equals(other.googleEventId))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (postedMessage == null) {
			if (other.postedMessage != null)
				return false;
		} else if (!postedMessage.equals(other.postedMessage))
			return false;
		if (replies == null) {
			if (other.replies != null)
				return false;
		} else if (!replies.equals(other.replies))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		if (status != other.status)
			return false;
		if (subCategory == null) {
			if (other.subCategory != null)
				return false;
		} else if (!subCategory.equals(other.subCategory))
			return false;
		return true;
	}

		}
