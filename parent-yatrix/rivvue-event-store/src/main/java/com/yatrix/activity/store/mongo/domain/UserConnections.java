package com.yatrix.activity.store.mongo.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AppConnections")
public class UserConnections extends Item{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8476654349197182051L;

	private String profileUserId;
	private Map<String,Reference> connections= new HashMap<String,Reference>();
	public String getProfileUserId() {
		return profileUserId;
	}
	public void setProfileUserId(String profileUserId) {
		this.profileUserId = profileUserId;
	}
	public Map<String, Reference> getConnections() {
		return connections;
	}
	public void setConnections(Map<String, Reference> connections) {
		this.connections = connections;
	}
	
	public void addReference(Reference connection){
		this.connections.put(connection.getUserId(), connection);
	}
	public Reference removeReference(Reference connection){
		return this.connections.remove(connection.getUserId());
	}
	public Reference removeReference(String userId){
		return this.connections.remove(userId);
	}
	
	
}
