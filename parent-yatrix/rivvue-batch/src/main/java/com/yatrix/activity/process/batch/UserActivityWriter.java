package com.yatrix.activity.process.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yatrix.activity.hystrix.command.HystrixSocialResult;
import com.yatrix.activity.hystrix.command.IFacebookCommand;
import com.yatrix.activity.store.mongo.domain.ActivityAndUserToEvents;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserActivity.EVENT_STATUS;
import com.yatrix.activity.store.mongo.domain.UserToEvents;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;

@Service("userActivityWriter")
public class UserActivityWriter implements ItemWriter<ActivityAndUserToEvents> {

	
	@Autowired
	private IUserActivityCatalogService userActivityCatalogService;
	
	@Autowired
	IFacebookCommand facebookCommand;
	
	@Override
	public void write(List<? extends ActivityAndUserToEvents> items) throws Exception {
		
		for(ActivityAndUserToEvents activityAndUserToEvents : items){
			System.out.println("Writing Activity: " + activityAndUserToEvents);
			UserActivity activity = activityAndUserToEvents.getUserActivity();
			
			HystrixSocialResult result = facebookCommand.executeFacebookCommand(activity).get();
			activity.setFacebookEventId(result.getEventId());
			activity.setStatus(EVENT_STATUS.INVITED);
			
			userActivityCatalogService.updateActivity(activity);
			
			for(UserToEvents userToEvent : activityAndUserToEvents.getUserToEventsList()){
				System.out.println("User To Event: " + userToEvent);
				//TODO: Save User To event here.
				userActivityCatalogService.save(userToEvent);
			}
		}
		
	}

}
