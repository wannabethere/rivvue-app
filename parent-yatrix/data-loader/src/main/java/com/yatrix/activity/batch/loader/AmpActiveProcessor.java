package com.yatrix.activity.batch.loader;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.yatrix.activity.batch.data.loader.util.Activities;
import com.yatrix.activity.batch.data.loader.util.Categories;
import com.yatrix.activity.dataloader.store.mongo.domain.AmpActiveEventResponse;
import com.yatrix.activity.mongo.service.YelpPlacesService;
import com.yatrix.activity.store.mongo.domain.ZipCodes;
import com.yatrix.activity.store.mongo.domain.loader.DataLoaderResponse;


public class AmpActiveProcessor implements ItemProcessor<ZipCodes, DataLoaderResponse> {

	private static final Log log = LogFactory.getLog(AmpActiveProcessor.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private YelpPlacesService yelpService;
	
	public DataLoaderResponse process(ZipCodes item) throws Exception {
		
		log.info(" ---------------------------------------------------- Amp Active Processor --------------------------------------------"  + item.getPrimaryCity());
		DataLoaderResponse response = new DataLoaderResponse();
		
		for(Activities activity : Activities.getAllActivities()){
			
			int breakThreshold = 0;
			
			for(Categories category : Categories.getAllCategories()){
				
				breakThreshold++;
				
				response = setAmpActiveEvents(item, category, activity, response);
				
				//TODO: Better refactor the reader to pass in only a url. 
				//TODO: When refactored properly remove this.
				if(breakThreshold > 2){
					// For now we will read only 2 activities for testing
					break;
				}
			}
			
			response.setYelpResponse(yelpService.loadReviews(activity.getActivity().toLowerCase(), 
					item.getLatitude(), item.getLongitude()));
			break;
		}
		
		
		return response;
	}

	private DataLoaderResponse setAmpActiveEvents(ZipCodes item,
			Categories category, Activities activity, DataLoaderResponse response) {
		String request;
		request = environment.getProperty("amp.active.event.search.url");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(new Date(System.currentTimeMillis()));
		String apiKey = environment.getProperty("amp.active.event.search.api_key");
		
		request = MessageFormat.format(request, activity.getActivity().toLowerCase(), category.getCategory().toLowerCase(),
				date, item.getLatitude(), item.getLongitude(), 50, apiKey);
		
		System.out.println("Request: " + request);

		String ampActiveEventResponse = restTemplate.getForObject(request, String.class); //AmpActiveEventResponse.class);
		log.info("Returned: " + ampActiveEventResponse == null ? "Nothing returned" : ampActiveEventResponse);
		
		response.addAmpResponse(ampActiveEventResponse);
		
		return response;
	}

}
