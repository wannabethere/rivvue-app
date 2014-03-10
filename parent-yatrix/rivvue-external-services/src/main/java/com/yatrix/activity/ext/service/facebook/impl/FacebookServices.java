package com.yatrix.activity.ext.service.facebook.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

@Service
public class FacebookServices {

	@Autowired
	private ConnectionRepository connectionRepository;
	
	public Facebook getFBAPI(){
		Facebook facebook = connectionRepository.getPrimaryConnection(Facebook.class).getApi();
		return facebook;
	}
	
	public void getFriends(){
		
	}
	
	
	
}
