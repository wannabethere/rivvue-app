package com.yatrix.activity.store.fb.domain;

import java.io.Serializable;

public class FacebookReference implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;
	
	public FacebookReference(){
		
	}
	
	public FacebookReference(String pId, String pName){
		this.id = pId;
		this.name = pName;
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
}
