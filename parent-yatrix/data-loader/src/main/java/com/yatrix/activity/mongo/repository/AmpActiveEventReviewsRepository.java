package com.yatrix.activity.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yatrix.activity.dataloader.store.mongo.domain.AmpActiveEvent;
import com.yatrix.activity.store.mongo.domain.loader.AmpActiveEventReviews;

public interface AmpActiveEventReviewsRepository extends MongoRepository<AmpActiveEvent, String> {

}
