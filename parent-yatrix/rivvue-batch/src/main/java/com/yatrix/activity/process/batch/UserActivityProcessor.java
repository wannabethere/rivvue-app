package com.yatrix.activity.process.batch;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;

import com.yatrix.activity.store.mongo.domain.ActivityAndUserToEvents;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserToEvents;
import com.yatrix.activity.store.mongo.domain.UserActivity.EVENT_STATUS;


public class UserActivityProcessor implements ItemProcessor<UserActivity, ActivityAndUserToEvents> {

	private static final Log log = LogFactory.getLog(UserActivityProcessor.class);
	
	public ActivityAndUserToEvents process(UserActivity item) throws Exception {
		List<UserToEvents> userToEventsList = new ArrayList<UserToEvents>();

		log.info(item);
		System.out.println("Processing Item: " + item);

		UserToEvents userToEvent;
		
		for(String participant : item.getParticipants()){
			userToEvent = new UserToEvents(item.getId(), participant, EVENT_STATUS.INVITED);
			userToEventsList.add(userToEvent);
			log.info("User To Event: " + userToEvent);
		}
		
		return new ActivityAndUserToEvents(item, userToEventsList);
	}

}
