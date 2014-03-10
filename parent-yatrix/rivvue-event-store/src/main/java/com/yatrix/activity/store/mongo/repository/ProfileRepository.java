package com.yatrix.activity.store.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.yatrix.activity.store.mongo.domain.UserProfile;

public interface ProfileRepository extends MongoRepository<UserProfile, String> {

	@Query("{ 'significantOther.userId' : ?0 }")
	List<UserProfile> findByReferenceUserId(String userId);
	
	@Query("{ \"$or\" : [ { \"email\" : { \"$regex\" : ?0, \"$options\" : \"i\"}} , { \"firstName\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}, { \"lastName\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}] , \"significantOther.userId\" : ?1}")
	List<UserProfile> findByEmailOrFirstNameOrLastNameLike(String searchName, String userId);
	
	@Query("{ \"$or\" : [ { \"email\" : { \"$regex\" : ?0, \"$options\" : \"i\"}} , { \"firstName\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}, { \"lastName\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}]} ")
	List<UserProfile> findByEmailOrFirstNameOrLastNameLike(String searchName);

	UserProfile findByUserId(String userId);
	
	List<UserProfile> findByUserIdIn(String[] userIds);
}
