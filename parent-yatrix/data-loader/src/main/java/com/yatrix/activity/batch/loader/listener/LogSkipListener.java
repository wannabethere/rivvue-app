package com.yatrix.activity.batch.loader.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.SkipListener;

import com.yatrix.activity.store.mongo.domain.UserActivity;

public class LogSkipListener implements SkipListener<UserActivity, UserActivity> {

	private static final Log LOGGER = LogFactory.getLog(LogSkipListener.class);

	public void onSkipInProcess(UserActivity partner, Throwable throwable) {
		LOGGER.info("Partner was skipped in process: "+partner+".",throwable);
	}

	public void onSkipInRead(Throwable arg0) {
	}

	public void onSkipInWrite(UserActivity arg0, Throwable arg1) {
	}

}
