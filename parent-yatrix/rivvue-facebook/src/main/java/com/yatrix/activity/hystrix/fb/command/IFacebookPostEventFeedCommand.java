package com.yatrix.activity.hystrix.fb.command;

import java.util.concurrent.Future;
import com.yatrix.activity.store.mongo.domain.UserActivity;

public interface IFacebookPostEventFeedCommand {
	
	Future<HystrixSocialResult> executeFacebookPostEventFeed(String pEventId, String pUserId, String pMessage);
	
	Future<HystrixSocialResult> executeFacebookPostEventFeed(UserActivity pUserActivity, String pUserId, String pMessage); 

}
