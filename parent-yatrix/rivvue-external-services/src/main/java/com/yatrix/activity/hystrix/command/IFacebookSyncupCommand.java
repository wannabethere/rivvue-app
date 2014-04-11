package com.yatrix.activity.hystrix.command;

import java.util.concurrent.Future;

import com.yatrix.activity.ext.domain.facebook.FacebookSyncupSocialResult;
import com.yatrix.activity.store.mongo.domain.UserEvent;

public interface IFacebookSyncupCommand {
	Future<FacebookSyncupSocialResult> executeFacebookSyncupCommand(String userActivityId);

	Future<FacebookSyncupSocialResult> executeFacebookSyncupCommand(
			UserEvent pUserActivity);
}
