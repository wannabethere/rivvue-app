package com.yatrix.activity.process.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.yatrix.activity.store.mongo.domain.ActivityAndUserToEvents;
import com.yatrix.activity.store.mongo.domain.AmpActiveEventReviews;
import com.yatrix.activity.store.mongo.domain.ZipCodes;


public class AmpActiveProcessor implements ItemProcessor<ZipCodes, AmpActiveEventReviews> {

	private static final Log log = LogFactory.getLog(AmpActiveProcessor.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	public AmpActiveEventReviews process(ZipCodes item) throws Exception {
		
		log.info(" ---------------------------------------------------- Amp Active Processor --------------------------------------------"  + item.getPrimaryCity());
		
		String request = "http://api.amp.active.com/v2/search?query="
				+ "running&category=event&start_date="
				+ "2014-06-20" //TODO: Get today's date here.
				+ "..&near="
				+  item.getPrimaryCity() +","+ item.getState() + "," + item.getCountry()//"San%20Diego,CA,US"
				+ "&radius=50&api_key=sqq35zvx6a8rgmxhy9csm8qj";
		
		//TODO: Make rest call to get the events and pass to the following constructor
		
		return new AmpActiveEventReviews(request, null);
	}

}
