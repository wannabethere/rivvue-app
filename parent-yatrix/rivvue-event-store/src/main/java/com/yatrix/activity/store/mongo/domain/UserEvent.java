package com.yatrix.activity.store.mongo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yatrix.activity.store.fb.domain.FacebookInvitee;
import com.yatrix.activity.store.fb.domain.FacebookPost;
import com.yatrix.activity.store.mongo.domain.Message.STATUS;
import com.yatrix.activity.store.mongo.domain.Message.VISIBILITY;
import com.yatrix.activity.store.mongo.domain.Participant.RSVPSTATUS;
import com.yatrix.activity.store.mongo.domain.Participant.TYPE;

@Document(collection = "UserEvents2")
public class UserEvent extends Item{

	/**
	 * Serial Version UID.
	 */
	private static final long serialVersionUID = 2242068081611181292L;
	public static enum EVENT_STATUS {
		PENDING, PROCESSING, INVITED,ME
	}

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
	

	private STATUS processedStatus;
	private VISIBILITY visibility;
	//Move the below the above Object
	
	private String facebookEventId;
	private String googleEventId;
	private String facebookFeedId;
	private EVENT_STATUS status;
	private boolean deleted;
	
	private Long duration;
	
	private String originatorUserId;
	private long createdTimeStamp;
	private long lupd;
	//Change to something else if we want to update 
	private long fbLupd;
	
	//Need to move TYPE to a UTIL class.
	private TYPE fromUserType;
	
	
	//Handling participants.
	private List<Participant> invitedIds = new ArrayList<Participant>();
	private List<PostMessage> postedMessage = new ArrayList<PostMessage>();
	private List<Comment> appComments = new ArrayList<Comment>();
	private List<ActivityCalendarReference> replies = new ArrayList<ActivityCalendarReference>();

	
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

	public List<ActivityCalendarReference> getReplies() {
		return replies;
	}

	public void setReplies(List<ActivityCalendarReference> replies) {
		this.replies = replies;
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

	
	public List< Participant> getInvitedIds() {
		return invitedIds;
	}
	
	public List<Participant> getAcceptedIds(){
		List<Participant> accepted=new ArrayList<Participant>();
		for(Participant p: this.invitedIds){
			if(p.getStatus().equals(RSVPSTATUS.ATTENDING)){
				accepted.add(p);
			}
		}
		return accepted;
	}
	public List<Participant> getDeclinedIds(){
		List<Participant> declined=new ArrayList<Participant>();
		for(Participant p: this.invitedIds){
			if(p.getStatus().equals(RSVPSTATUS.DECLINED)){
				declined.add(p);
			}
		}
		return declined;
	}
	public List<Participant> getMayBeIds(){
		List<Participant> maybe=new ArrayList<Participant>();
		for(Participant p: this.invitedIds){
			if(p.getStatus().equals(RSVPSTATUS.MAYBE)){
				maybe.add(p);
			}
		}
		return maybe;
	}
	
	public List<Participant> getNotRepliedIds(){
		List<Participant> notReplied=new ArrayList<Participant>();
		for(Participant p: this.invitedIds){
			if(p.getStatus().equals(RSVPSTATUS.NOT_REPLIED)){
				notReplied.add(p);
			}
		}
		return notReplied;
	}
	
	public void setInvitedIds(List< Participant> invitedIds) {
		this.invitedIds = invitedIds;
	}
	
	//Checks for the existence of the Id and removes it. Simulating an Update.
	//This is not scalable for large number of people in an invite. 
	//We will not be doing this for public events.
	//Incase of public events. We will launch something like social circles.
	public void addInvitedId(Participant p){
		for(Participant curP: this.invitedIds){
			if(curP.getUserId().equalsIgnoreCase(p.getUserId())){
				//then replace the current.
				this.invitedIds.remove(curP);
				break;
			}
		}
		this.invitedIds.add(p);
	}
	
	public void removedInvited(Participant p){
		this.invitedIds.remove(p);
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public long getLupd() {
		return lupd;
	}

	public void setLupd(long lupd) {
		this.lupd = lupd;
	}

	public long getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	public void setCreatedTimeStamp(long createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((displayName == null) ? 0 : displayName.hashCode());
		
		result = prime * result + ((facebookEventId == null) ? 0 : facebookEventId.hashCode());
		result = prime * result + ((googleEventId == null) ? 0 : googleEventId.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((postedMessage == null) ? 0 : postedMessage.hashCode());
		result = prime * result + ((replies == null) ? 0 : replies.hashCode());
		
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
		UserEvent other = (UserEvent)obj;
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

	public long getFbLupd() {
		return fbLupd;
	}

	public void setFbLupd(long fbLupd) {
		this.fbLupd = fbLupd;
	}

	@Override
	public String toString() {
		return "UserEvent [title=" + title + ", description=" + description
				+ ", categoryId=" + categoryId + ", subCategory=" + subCategory
				+ ", displayName=" + displayName + ", authorName=" + authorName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", location=" + location + ", processedStatus="
				+ processedStatus + ", visibility=" + visibility
				+ ", facebookEventId=" + facebookEventId + ", googleEventId="
				+ googleEventId + ", facebookFeedId=" + facebookFeedId
				+ ", status=" + status + ", deleted=" + deleted + ", duration="
				+ duration + ", originatorUserId=" + originatorUserId
				+ ", createdTimeStamp=" + createdTimeStamp + ", lupd=" + lupd
				+ ", invitedIds=" + invitedIds + ", postedMessage="
				+ postedMessage + ", appComments=" + appComments + ", replies="
				+ replies + "]";
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
	
	

}
