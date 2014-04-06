package com.yatrix.activity.hystrix.fb.command;

import java.util.concurrent.Future;
import com.yatrix.activity.store.mongo.domain.UserActivity;

public interface IFacebookSyncupCommand {
	Future<FacebookSyncupSocialResult> executeFacebookSyncupCommand(String userActivityId);

	Future<FacebookSyncupSocialResult> executeFacebookSyncupCommand(
			UserActivity pUserActivity);
}
