package com.yatrix.activity.store.mongo.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.yatrix.activity.store.mongo.domain.RememberMeToken;


public interface RememberMeTokenRepository extends MongoRepository<RememberMeToken, String>{
    RememberMeToken findBySeries(String series);
    List<RememberMeToken> findByUsername(String username);
}