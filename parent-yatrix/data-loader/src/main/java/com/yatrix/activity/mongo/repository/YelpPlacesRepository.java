package com.yatrix.activity.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.yatrix.activity.ext.domain.yelp.PlaceDetails;

public interface YelpPlacesRepository extends MongoRepository<PlaceDetails, String> {

}
