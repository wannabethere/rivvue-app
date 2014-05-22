package com.yatrix.activity.store.mongo.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yatrix.activity.store.mongo.domain.Message.STATUS;
import com.yatrix.activity.store.mongo.domain.Message.VISIBILITY;
import com.yatrix.activity.store.mongo.domain.Participant.RSVPSTATUS;
import com.yatrix.activity.store.mongo.domain.Participant.TYPE;
import com.yatrix.activity.store.mongo.domain.UserEvent.EVENT_STATUS;

@Document(collection = "UserDraftEvents")
public class UserDraftEvent extends Item{

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 2242068081611181292L;

	private String title;
	private String description;
	private String categoryId;
	private String subCategory;
	private String displayName;
	private String authorName;
	private long startTime;
	private long endTime;
	//Move the below to a class Object
	private Venue location;
	private String publishTo;

	private STATUS processedStatus;
	private VISIBILITY visibility;
	//Move the below the above Object
	
	private EVENT_STATUS status;
	private boolean deleted;
	
	private Long duration;
	
	private String originatorUserId;
	private String originatorProfileUserId;
	
	//Need to move TYPE to a UTIL class.
	private TYPE fromUserType;
	
	
	//Handling participants.
	private List<PostMessage> postedMessage = new ArrayList<PostMessage>();
	private List<Comment> appComments = new ArrayList<Comment>();
	
	public STATUS getProcessedStatus() {
		return processedStatus;
	}

	public void setProcessedStatus(STATUS processedStatus) {
		this.processedStatus = processedStatus;
	}

	public VISIBILITY getVisibility() {
		return visibility;
	}

	public void setVisibility(VISIBILITY visibility) {
		this.visibility = visibility;
	}
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

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	
	public List<PostMessage> getPostedMessage() {
		return postedMessage;
	}

	public void setPostedMessage(List<PostMessage> postedMessage) {
		this.postedMessage = postedMessage;
	}
	
	public void addPostedMessage(PostMessage postedMessage) {
		this.postedMessage.add(postedMessage);
	}
	
	public EVENT_STATUS getStatus() {
		return status;
	}

	public void setStatus(EVENT_STATUS status) {
		this.status = status;
	}
	
	public Venue getLocation() {
		return location;
	}

	public void setLocation(Venue location) {
		this.location = location;
	}

	
	public List<Comment> getAppComments() {
		return appComments;
	}

	public void setAppComments(List<Comment> appComments) {
		this.appComments = appComments;
	}
	
	public void addComment(Comment appComment) {
		if(this.appComments==null){
			this.appComments= new ArrayList<Comment>();
		}
		this.appComments.add(appComment);
	}
	
	public void updateComment(Comment oldComment,Comment newComment) {
		if(this.appComments.contains(oldComment)){
			this.appComments.remove(oldComment);
			//Objects are not the same so replacing the same item will create two objects.
		}
		this.appComments.add(newComment);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDuration(){
		this.duration=((endTime -  startTime)/(1000 * 60 * 60));
	}

	public Long getDuration(){
		this.duration=((endTime -  startTime)/(1000 * 60 * 60));
		return duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getPublishTo() {
		return publishTo;
	}

	public void setPublishTo(String publishTo) {
		this.publishTo = publishTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((postedMessage == null) ? 0 : postedMessage.hashCode());
		
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
		UserDraftEvent other = (UserDraftEvent)obj;
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
		
		if (status != other.status)
			return false;
		if (subCategory == null) {
			if (other.subCategory != null)
				return false;
		} else if (!subCategory.equals(other.subCategory))
			return false;
		return true;
	}

	public String getOriginatorUserId() {
		return originatorUserId;
	}

	public void setOriginatorUserId(String originatorUserId) {
		this.originatorUserId = originatorUserId;
	}

	@Override
	public String toString() {
		return "UserEvent [title=" + title + ", description=" + description
				+ ", categoryId=" + categoryId + ", subCategory=" + subCategory
				+ ", displayName=" + displayName + ", authorName=" + authorName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", location=" + location + ", processedStatus="
				+ processedStatus + ", visibility=" + visibility
				+ ", status=" + status + ", deleted=" + deleted + ", duration="
				+ duration + ", originatorUserId=" + originatorUserId
				+ ", postedMessage=" + postedMessage + ", appComments=" + appComments + "]";
	}

	public TYPE getFromUserType() {
		return fromUserType;
	}

	public void setFromUserType(String fromUserType) {
		if(fromUserType.equalsIgnoreCase("FB")){
			this.fromUserType = TYPE.FB;
		}
		else{
			this.fromUserType=TYPE.APP;
		}
		
	}

	public String getOriginatorProfileUserId() {
		return originatorProfileUserId;
	}

	public void setOriginatorProfileUserId(String originatorProfileUserId) {
		this.originatorProfileUserId = originatorProfileUserId;
	}
	
	

}
