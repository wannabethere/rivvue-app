package com.yatrix.activity.process.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yatrix.activity.ext.domain.facebook.FacebookSyncupSocialResult;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;

@Service("facebookSyncupWriter")
public  class FacebookSyncupWriter implements ItemWriter<FacebookSyncupSocialResult> {

	private static final Log log = LogFactory.getLog(FacebookSyncupWriter.class);
	
	@Autowired
	IUserActivityCatalogService catalogService;
	
	/**
	 * @see ItemWriter#write(java.util.List)
	 */
	public void write(List<? extends FacebookSyncupSocialResult> data) throws Exception {
		log.debug(data);
		for(FacebookSyncupSocialResult socialResult : data){
			if(socialResult.isSuccess()){
				catalogService.updateActivity(socialResult.getEvent());
			}
		}
	}

}
