package com.yatrix.activity.store.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import com.yatrix.activity.store.mongo.domain.UserActivity.EVENT_STATUS;

@Document(collection = "UserToEvents")
public class UserToEvents extends Item {

	private static final long serialVersionUID = 1L;
	
	String eventId;
	
	String invitee;
	
	EVENT_STATUS status;
	
	public UserToEvents(){
		
	}
	
	public UserToEvents(String pEventId, String pInvitee, EVENT_STATUS pStatus){
		eventId = pEventId;
		invitee = pInvitee;
		status = pStatus;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getInvitee() {
		return invitee;
	}

	public void setInvitee(String invitee) {
		this.invitee = invitee;
	}

	public EVENT_STATUS getStatus() {
		return status;
	}

	public void setStatus(EVENT_STATUS status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((invitee == null) ? 0 : invitee.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserToEvents other = (UserToEvents) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (invitee == null) {
			if (other.invitee != null)
				return false;
		} else if (!invitee.equals(other.invitee))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserToEvents [eventId=" + eventId + ", invitee=" + invitee
				+ ", status=" + status + "]";
	}

}
