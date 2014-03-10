package com.yatrix.activity.store.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.yatrix.activity.store.mongo.domain.UserToEvents;

public interface UserToEventsRepository extends MongoRepository<UserToEvents, String> {
	
	@Query("{ \"$or\" : [ { \"email\" : { \"$regex\" : ?0, \"$options\" : \"i\"}} , { \"firstName\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}, { \"lastName\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}] , \"significantOther.userId\" : ?1}")
	List<UserToEvents> findByEventStatus(String searchName, String userId);

}
