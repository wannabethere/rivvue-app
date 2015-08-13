package com.yatrix.activity.batch.loader;

import static java.nio.file.StandardOpenOption.*;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.yatrix.activity.mongo.repository.AmpActiveEventReviewsRepository;
import com.yatrix.activity.store.mongo.domain.loader.AmpActiveEventReviews;


@Service
public class AmpActiveWriter implements ItemWriter<List<AmpActiveEventReviews>> {

	@Autowired
	private AmpActiveEventReviewsRepository ampActiveEventReviewRepository;

	@Autowired
	private Environment environment;

	private static final Log log = LogFactory.getLog(AmpActiveWriter.class);

	@Override
	public void write(List<? extends List<AmpActiveEventReviews>> items)
			throws Exception {

		System.out.println("File Path: " + environment.getProperty("amp.active.file.path"));
		
		Path p = Paths.get(environment.getProperty("amp.active.file.path"));

		try (OutputStream out = new BufferedOutputStream(
				Files.newOutputStream(p, CREATE, APPEND))) {
			for(List<AmpActiveEventReviews> item : items){
				for(AmpActiveEventReviews activeEventReview : item){
					out.write(activeEventReview.getAmpActiveEventResponse().getBytes(), 0, activeEventReview.getAmpActiveEventResponse().length());					
				}
			}

		} catch (IOException ex) {
			log.error("Error writing Amp Activity to file...", ex);
		}

		//ampActiveEventReviewRepository.save(allItems);

	}


}
