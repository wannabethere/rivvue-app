package com.yatrix.activity.service.dto;

public class AjaxResponse {
	String message;
	
	public AjaxResponse(){
		
	}
	
	public AjaxResponse(String pMessage){
		message = pMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
