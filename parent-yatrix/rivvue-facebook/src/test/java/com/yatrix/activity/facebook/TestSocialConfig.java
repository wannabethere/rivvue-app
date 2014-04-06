package com.yatrix.activity.facebook;

import javax.inject.Inject;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.SocialAuthenticationServiceRegistry;

import com.yatrix.activity.store.mongo.service.impl.UserEventsService;


@Configuration
@ComponentScan(basePackages = "com.yatrix.activity.store")
public class TestSocialConfig {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private UserEventsService userEventsServices;
	
	@Bean
    public ConnectionFactoryLocator connectionFactoryLocator() {
		SocialAuthenticationServiceRegistry socialregistry= new SocialAuthenticationServiceRegistry();
        
		socialregistry.addConnectionFactory(new FacebookConnectionFactory(
				"dummy","dummy"));
		
		
        return socialregistry;
    }
	
	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}

    @Inject
    private Environment env;

}



