package com.yatrix.activity.process.batch;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.service.impl.UserEventsService;

@Service(value="facebookSyncupReader")
public class FacebookSyncupReader extends AbstractPagingItemReader<UserEvent> 
implements InitializingBean {

//	@Autowired
//	private IUserActivityCatalogService userActivityCatalogService;
	
	@Autowired
	private UserEventsService eventService;
	
	public FacebookSyncupReader(){
		super();
		setPageSize(10);
	}

	@Override
	protected void doReadPage() {
		if (results == null) {
			results = new CopyOnWriteArrayList<UserEvent>();
		}
		else {
			results.clear();
		}
		System.out.println("Records From: " + ((getPage() * getPageSize())+ 1 ));

		results.addAll(eventService.getUserEvents((getPage() * getPageSize()), getPageSize()));

		
	}

	@Override
	protected void doJumpToPage(int itemIndex) {
		// TODO Auto-generated method stub
		
	}
	
}
