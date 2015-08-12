package com.yatrix.activity.batch.loader.listener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.SkipListener;

public class LogSkipListener implements SkipListener<Object, Object> {

	private static final Log LOGGER = LogFactory.getLog(LogSkipListener.class);

	public void onSkipInProcess(Object partner, Throwable throwable) {
		LOGGER.info("Partner was skipped in process: "+partner+".",throwable);
	}

	public void onSkipInRead(Throwable arg0) {
	}

	public void onSkipInWrite(Object arg0, Throwable arg1) {
	}

}
