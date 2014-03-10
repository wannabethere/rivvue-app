package com.yatrix.activity.process.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;



import com.yatrix.activity.ext.domain.facebook.FacebookSyncupSocialResult;
import com.yatrix.activity.hystrix.command.IFacebookSyncupCommand;
import com.yatrix.activity.store.mongo.domain.UserActivity;


public class FacebookSyncupProcessor implements ItemProcessor<UserActivity, FacebookSyncupSocialResult> {

	private static final Log log = LogFactory.getLog(FacebookSyncupProcessor.class);
	
	@Autowired
	private IFacebookSyncupCommand facebookSyncupCommand;
	
	public FacebookSyncupSocialResult process(UserActivity item) throws Exception {

		log.info(item);
		System.out.println("Processing Item: " + item);
		
		FacebookSyncupSocialResult result = facebookSyncupCommand.executeFacebookSyncupCommand(item).get();
		
		return result;
	}

}
