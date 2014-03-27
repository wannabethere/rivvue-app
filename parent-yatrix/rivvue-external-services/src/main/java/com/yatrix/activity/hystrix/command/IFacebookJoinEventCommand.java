package com.yatrix.activity.hystrix.command;

import java.util.concurrent.Future;

import com.yatrix.activity.store.mongo.domain.UserActivity;

public interface IFacebookJoinEventCommand {

	Future<HystrixSocialResult> executeFacebookJoinEvent(
			UserActivity pUserActivity, String pUserId);

}
