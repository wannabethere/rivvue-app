package com.yatrix.activity.service.commands;

import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.PagedList;
import org.thymeleaf.util.StringUtils;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.thoughtworks.xstream.InitializationException;
import com.yatrix.activity.service.dto.UserDto;
import com.yatrix.activity.service.utils.FacebookConnectInterceptor;
import com.yatrix.activity.service.utils.UserMapper;
import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.UserProfile.PROFILETYPE;
import com.yatrix.activity.store.mongo.service.impl.ProfileService;
import com.yatrix.activity.store.mongo.service.impl.UserAccountService;

public class GetMyAppFriendsCommand extends HystrixCommand<List<UserDto>>  {

	private static final Logger log = Logger.getLogger(FacebookConnectInterceptor.class
			.getName());
	@Autowired
	private ProfileService profileService;
	@Autowired
	private UserAccountService userAccountRepository;
	
	private final UserAccount user;
	
	public GetMyAppFriendsCommand(UserAccount user) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
			      .andCommandKey(HystrixCommandKey.Factory.asKey("GetMyFBFriends"))
			      .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FacebookThread"))
			      .andCommandPropertiesDefaults(
			      // we default to a one minute timeout for primary
			        HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
		this.user=user;
		
		
	}

	@Override
	protected List<UserDto> run() throws Exception {
		if(user==null || profileService==null || userAccountRepository==null){
			throw new InitializationException("Failed to set the user");
		}
		return UserMapper.mapUserProfile(profileService.getMyContacts(user.getFacebookId()));	
	}
	
	
}
