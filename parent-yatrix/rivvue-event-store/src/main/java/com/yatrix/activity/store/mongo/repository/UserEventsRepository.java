package com.yatrix.activity.store.mongo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserEvent;


public interface UserEventsRepository extends MongoRepository<UserEvent, String>{
	UserEvent findById(String activityId);
	List<UserEvent> findByoriginatorUserId(String profileUserId);
	@Query("{ \"$or\" : [  { \"place\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}, { \"title\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}]},{ \"tags\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}]} ")
	List<UserEvent> findByFieldSearchPlaceorTitleorTags(String search);
	
	//TODO: Implement the and Condition for the Search.
	//@Query("{ \"$and\" : [  { \"place\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}, { \"title\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}]},{ \"tags\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}]} ")
	//List<UserEvent> findByPlaceAndTime(String city, String startTime, String endTime);
	//TODO: More queries for Finding Events.
	
}