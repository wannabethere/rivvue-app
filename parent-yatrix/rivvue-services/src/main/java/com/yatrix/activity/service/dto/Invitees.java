package com.yatrix.activity.service.dto;

import java.io.Serializable;

import com.yatrix.activity.store.mongo.domain.Participant.TYPE;

public class Invitees implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum RSVPSTATUS {
		ATTENDING, DECLINED, UNSURE, NOT_REPLIED;
	}
	
	private String id;
	private String name;
	private String rsvpStatus;
	private String inviteeType;
	private boolean isConnectedToViewer;
	
	
	public Invitees(){
		
	}
	
	public Invitees(String id, String name, String rsvpStatus, String inviteeType) {
		this.id = id;
		this.name = name;
		this.rsvpStatus = rsvpStatus;
		this.inviteeType=inviteeType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRsvpStatus() {
		return rsvpStatus;
	}

	public void setRsvpStatus(String rsvpStatus) {
		this.rsvpStatus = rsvpStatus;
	}

	public String getInviteeType() {
		return inviteeType;
	}

	public void setInviteeType(String inviteeType) {
		this.inviteeType = inviteeType;
	}

	/**
	 * to show if the user needs to connect to the user.
	 * @param isConnectedToViewer
	 */
	public void setConnectedToViewer(boolean isConnectedToViewer) {
		this.isConnectedToViewer = isConnectedToViewer;
	}

	public boolean isConnectedToViewer() {
		return isConnectedToViewer;
	}	
}
