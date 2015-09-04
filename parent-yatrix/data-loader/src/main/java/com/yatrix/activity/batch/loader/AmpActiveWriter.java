package com.yatrix.activity.batch.loader;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yatrix.activity.dataloader.store.mongo.domain.AmpActiveEvent;
import com.yatrix.activity.dataloader.store.mongo.domain.AmpActiveEventResponse;
import com.yatrix.activity.ext.domain.yelp.PlaceDetailsResponse;
import com.yatrix.activity.mongo.repository.AmpActiveEventReviewsRepository;
import com.yatrix.activity.mongo.repository.YelpPlacesRepository;
import com.yatrix.activity.store.mongo.domain.loader.DataLoaderResponse;


@Service
public class AmpActiveWriter implements ItemWriter<DataLoaderResponse> {

	@Autowired
	private AmpActiveEventReviewsRepository ampActiveEventReviewRepository;
	
	@Autowired
	private YelpPlacesRepository yelpPlacesRepository;

	@Autowired
	private Environment environment;

	private static final Log log = LogFactory.getLog(AmpActiveWriter.class);

	@Override
	public void write(List<? extends DataLoaderResponse> items)
			throws Exception {
		
		writeToFile(items);
		//persist(items);

	}

	private void persist(List<? extends DataLoaderResponse> items) {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);

		PlaceDetailsResponse detailsResponse = null;
		AmpActiveEventResponse ampActiveEventResponse = null;
		
		for(DataLoaderResponse response  : items){
			try {
				System.out.println("Saving yelp reviews...");
				detailsResponse = mapper.readValue(response.getYelpResponse(), PlaceDetailsResponse.class);
				
				yelpPlacesRepository.save(detailsResponse.getResult());
				System.out.println("Yelp saved...");
				
				for(String eventResponse: response.getAmpResponse()){
					System.out.println("Event Response..." );
					ampActiveEventResponse = mapper.readValue(eventResponse, AmpActiveEventResponse.class);
					System.out.println("Response: " + eventResponse);
					
					AmpActiveEvent ampActiveEvent = ampActiveEventResponse.getResults().get(0);
					
					com.yatrix.activity.dataloader.store.mongo.AmpActiveEvent persistencyEvent = new com.yatrix.activity.dataloader.store.mongo.AmpActiveEvent();
					
					BeanUtils.copyProperties(ampActiveEvent, persistencyEvent);
					
					System.out.println(persistencyEvent);
					
					ampActiveEventReviewRepository.save(persistencyEvent);
					System.out.println("Saved");
				}
				
				
				System.out.println("Saved yelp reviews...");
			} catch (java.io.IOException ioe) {
				System.out.println("error :" + ioe);
			}
			
		}
		
	}

	private void writeToFile(List<? extends DataLoaderResponse> items) {
		System.out.println("File Path: " + environment.getProperty("amp.active.file.path"));

		Path ampActiveFilepath = Paths.get(environment.getProperty("amp.active.file.path"));
		Path yelpFilepath = Paths.get(environment.getProperty("yelp.file.path"));

		try (OutputStream ampActiveFile = new BufferedOutputStream(
				Files.newOutputStream(ampActiveFilepath, CREATE, APPEND));
				OutputStream yelpReviewFile = new BufferedOutputStream(
						Files.newOutputStream(yelpFilepath, CREATE, APPEND))) {
			
			for(DataLoaderResponse response : items){
				for(String ampResponse : response.getAmpResponse()){
					ampActiveFile.write((ampResponse + "\n" ).getBytes(), 0, (ampResponse + "\n" ).length());	
				}
				
				yelpReviewFile.write((response.getYelpResponse() + "\n" ).getBytes(), 0, (response.getYelpResponse() + "\n" ).length());
				
			}

		} catch (Exception ex) {
			log.error("Error writing Amp Activity to file...", ex);
		}
	}


}
