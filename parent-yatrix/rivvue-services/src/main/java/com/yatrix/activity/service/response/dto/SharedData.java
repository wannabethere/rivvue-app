package com.yatrix.activity.service.response.dto;

import java.util.ArrayList;
import java.util.List;

public class SharedData {

	private List<String> friendsIds= new ArrayList<String>();

	public List<String> getFriendsIds() {
		return friendsIds;
	}

	public void setFriendsIds(List<String> friendsIds) {
		this.friendsIds = friendsIds;
	} 
	
}
