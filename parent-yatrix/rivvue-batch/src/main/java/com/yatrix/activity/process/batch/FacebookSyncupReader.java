package com.yatrix.activity.process.batch;

import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserActivity.EVENT_STATUS;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;

@Service(value="facebookSyncupReader")
public class FacebookSyncupReader extends AbstractPagingItemReader<UserActivity> 
implements InitializingBean {

	@Autowired
	private IUserActivityCatalogService userActivityCatalogService;
	
	public FacebookSyncupReader(){
		super();
		setPageSize(10);
	}

	@Override
	protected void doReadPage() {
		if (results == null) {
			results = new CopyOnWriteArrayList<UserActivity>();
		}
		else {
			results.clear();
		}
		System.out.println("Records From: " + ((getPage() * getPageSize())+ 1 ));

		results.addAll(userActivityCatalogService.findActivies(EVENT_STATUS.INVITED, (getPage() * getPageSize()), getPageSize()));

		
	}

	@Override
	protected void doJumpToPage(int itemIndex) {
		// TODO Auto-generated method stub
		
	}
	
}
