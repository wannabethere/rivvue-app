package com.yatrix.activity.store.mongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.yatrix.activity.store.mongo.domain.UserActivity;


public interface UserActivityRepository extends MongoRepository<UserActivity, String>{
	UserActivity findById(String activityId);
}