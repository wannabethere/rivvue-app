package com.yatrix.activity.batch.loader;

import java.util.ArrayList;
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
				
				request = "http://api.amp.active.com/v2/search?query="
						+ activity.getActivity().toLowerCase() +"&category=" + category.getCategory().toLowerCase() +"&start_date="
						+ "2014-06-20" //TODO: Get today's date here.
						+ "..&near="
						+  item.getPrimaryCity() +","+ item.getState() + "," + item.getCountry()//"San%20Diego,CA,US"
						+ "&radius=50&api_key=nannnszrab8pjhkey5j6vpc8";

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
