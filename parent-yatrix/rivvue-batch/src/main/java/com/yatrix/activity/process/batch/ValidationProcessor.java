package com.yatrix.activity.process.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import com.yatrix.activity.store.mongo.domain.UserActivity;


public class ValidationProcessor implements ItemProcessor<UserActivity,UserActivity> {

	private static final Log log = LogFactory.getLog(ValidationProcessor.class);
	
	public UserActivity process(UserActivity partner) throws Exception {
		log.info(partner);
		if (!("m".equals(partner.getEndTime()) || ("w".equals(partner.getEndTime())))){
			throw new Exception("Gender "+partner.getEndTime()+" is unknown!");
		}
		return partner;
	}

}
