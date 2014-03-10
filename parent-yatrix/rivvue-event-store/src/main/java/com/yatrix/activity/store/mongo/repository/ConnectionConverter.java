/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yatrix.activity.store.mongo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.stereotype.Component;

import com.yatrix.activity.store.mongo.domain.UserSocialConnection;

/**
 * A converter class between Mongo document and
 * Spring social connection.
 * 
 * 
 */
@Component
public class ConnectionConverter {
	
	@Autowired
	private ConnectionFactoryLocator connectionFactoryLocator;
	@Autowired
	private TextEncryptor textEncryptor;

	
	public ConnectionConverter(){
		
	}
	/*public ConnectionConverter(ConnectionFactoryLocator connectionFactoryLocator,
		TextEncryptor textEncryptor) {
	
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;
	}*/
	
	
	
	public Connection<?> convert(UserSocialConnection cnn) {
		if (cnn==null) return null;
		
		ConnectionData connectionData = fillConnectionData(cnn);
		ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId());
		return connectionFactory.createConnection(connectionData);
	}
	
	private ConnectionData fillConnectionData(UserSocialConnection uc) {
		return new ConnectionData(uc.getProviderId(),
			uc.getProviderUserId(),
			uc.getDisplayName(),
			uc.getProfileUrl(),
			uc.getImageUrl(),
			decrypt(uc.getAccessToken()),
			decrypt(uc.getSecret()),
			decrypt(uc.getRefreshToken()),
			uc.getExpireTime());
	}
	
	public UserSocialConnection convert(Connection<?> cnn) {
		ConnectionData data = cnn.createData();
		
		UserSocialConnection userConn = new UserSocialConnection();
		userConn.setProviderId(data.getProviderId());
		userConn.setProviderUserId(data.getProviderUserId());
		userConn.setDisplayName(data.getDisplayName());
		userConn.setProfileUrl(data.getProfileUrl());
		userConn.setImageUrl(data.getImageUrl());
		userConn.setAccessToken(encrypt(data.getAccessToken()));
		userConn.setSecret(encrypt(data.getSecret()));
		userConn.setRefreshToken(encrypt(data.getRefreshToken()));
		userConn.setExpireTime(data.getExpireTime());
		return userConn;
	}
	
	public ConnectionFactoryLocator getConnectionFactoryLocator() {
		return connectionFactoryLocator;
	}

	
	public TextEncryptor getTextEncryptor() {
		return textEncryptor;
	}

	
	
	// helper methods
	
	private String decrypt(String encryptedText) {
		return encryptedText != null ? textEncryptor.decrypt(encryptedText) : encryptedText;
	}

	private String encrypt(String text) {
		return text != null ? textEncryptor.encrypt(text) : text;
	}



	public void setConnectionFactoryLocator(
			ConnectionFactoryLocator connectionFactoryLocator) {
		this.connectionFactoryLocator = connectionFactoryLocator;
	}



	public void setTextEncryptor(TextEncryptor textEncryptor) {
		this.textEncryptor = textEncryptor;
	}
}
