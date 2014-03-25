package com.yatrix.activity.store.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.yatrix.activity.store.mongo.domain.UserConnections;

public interface AppConnectionsRepository extends MongoRepository<UserConnections, String>{
	public UserConnections findByProfileUserId(String profileUserId);
}