package com.yatrix.activity.store.mongo.domain;

public class Participant {

	public static enum TYPE{
		FB,APP;
	}
	public static enum RSVPSTATUS {
		ATTENDING("$name is attending",""), DECLINED("$name is attending",""), MAYBE("$name is attending",""), NOT_REPLIED("$name is attending","");
		private String message;
		private String joinedMessage;
		private RSVPSTATUS(String message, String joinedMessage) {
			this.message=message;
			this.joinedMessage=joinedMessage;
		}
		public String getMessage() {
			return message;
		}
		
		public String getJoinedMessage() {
			return joinedMessage;
		}
	}
	
	private TYPE userType;
	//TODO: Later change the String userId to integer.
	private String userId;
	private RSVPSTATUS status;
	private String inviteeName;
	private long lupd;
	
	public TYPE getUserType() {
		return userType;
	}
	public void setUserType(TYPE userType) {
		this.userType = userType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public RSVPSTATUS getStatus() {
		return status;
	}
	public void setStatus(RSVPSTATUS status) {
		this.status = status;
	}
	public String getInviteeName() {
		return inviteeName;
	}
	public void setInviteeName(String inviteeName) {
		this.inviteeName = inviteeName;
	}
	public long getLupd() {
		return lupd;
	}
	public void setLupd(long lupd) {
		this.lupd = lupd;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((inviteeName == null) ? 0 : inviteeName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((userType == null) ? 0 : userType.hashCode());
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
		Participant other = (Participant) obj;
		if (inviteeName == null) {
			if (other.inviteeName != null)
				return false;
		} else if (!inviteeName.equals(other.inviteeName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userType != other.userType)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Participant [userType=" + userType + ", userId=" + userId
				+ ", status=" + status + ", inviteeName=" + inviteeName
				+ ", lupd=" + lupd + "]";
	}
	
	
	
}
