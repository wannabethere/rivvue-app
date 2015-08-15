package com.yatrix.activity.batch.loader;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.yatrix.activity.batch.data.loader.util.Activities;
import com.yatrix.activity.batch.data.loader.util.Categories;
import com.yatrix.activity.store.mongo.domain.ZipCodes;
import com.yatrix.activity.store.mongo.domain.loader.AmpActiveEventReviews;


public class AmpActiveProcessor implements ItemProcessor<ZipCodes, List<AmpActiveEventReviews>> {

	private static final Log log = LogFactory.getLog(AmpActiveProcessor.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment environment;
	
	public List<AmpActiveEventReviews> process(ZipCodes item) throws Exception {
		
		log.info(" ---------------------------------------------------- Amp Active Processor --------------------------------------------"  + item.getPrimaryCity());
		List<AmpActiveEventReviews> eventReviewList = new ArrayList<AmpActiveEventReviews>();
		AmpActiveEventReviews eventReview;
		String request;
		
		for(Categories category : Categories.getAllCategories()){
			
			int breakThreshold = 0;
			
			for(Activities activity : Activities.getAllActivities()){
				
				breakThreshold++;
				
				request = environment.getProperty("amp.active.event.search.url");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = dateFormat.format(new Date(System.currentTimeMillis()));
				String apiKey = environment.getProperty("amp.active.event.search.api_key");
				
				request = MessageFormat.format(request, activity.getActivity().toLowerCase(), category.getCategory().toLowerCase(),
						date, item.getPrimaryCity(), item.getState(), item.getCountry(), 50, apiKey);
				
				System.out.println("Request: " + request);

				eventReview = new AmpActiveEventReviews(request, null);
				
				Object ampActiveEventResponse = restTemplate.getForObject(request, Object.class); //AmpActiveEventResponse.class);
				log.info("Returned: " + ampActiveEventResponse == null ? "Nothing returned" : ampActiveEventResponse.toString());
				
				eventReview.setAmpActiveEventResponse(ampActiveEventResponse.toString());
				
				eventReviewList.add(eventReview);
				
				//TODO: Better refactor the reader to pass in only a url. 
				//TODO: When refactored properly remove this.
				if(breakThreshold > 2){
					// For now we will read only 2 activities for testing
					break;
				}
			}
			break;
		}
		
		
		return eventReviewList;
	}

}
