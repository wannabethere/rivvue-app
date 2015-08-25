package com.yatrix.activity.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yatrix.activity.dataloader.store.mongo.AmpActiveEvent;


public interface AmpActiveEventReviewsRepository extends MongoRepository<AmpActiveEvent, String> {

}
