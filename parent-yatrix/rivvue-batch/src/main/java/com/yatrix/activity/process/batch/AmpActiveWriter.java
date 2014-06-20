package com.yatrix.activity.process.batch;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yatrix.activity.store.mongo.domain.AmpActiveEventReviews;
import com.yatrix.activity.store.mongo.repository.AmpActiveEventReviewsRepository;

@Service
public class AmpActiveWriter implements ItemWriter<AmpActiveEventReviews> {

	@Autowired
	private AmpActiveEventReviewsRepository ampActiveEventReviewRepository;

	private static final Log log = LogFactory.getLog(AmpActiveWriter.class);

	@Override
	public void write(List<? extends AmpActiveEventReviews> items)
			throws Exception {

		ampActiveEventReviewRepository.save(items);

	}

}
