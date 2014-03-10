package com.yatrix.activity.hystrix.command;

import java.util.concurrent.Future;

import com.yatrix.activity.store.mongo.domain.UserActivity;

public interface IFacebookCommand {
	Future<HystrixSocialResult> executeFacebookCommand(String userActivityId);

	Future<HystrixSocialResult> executeFacebookCommand(
			UserActivity pUserActivity);
}
