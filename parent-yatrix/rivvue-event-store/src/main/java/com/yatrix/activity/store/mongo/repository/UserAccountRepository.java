package com.yatrix.activity.store.mongo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.yatrix.activity.store.mongo.domain.UserAccount;
import com.yatrix.activity.store.mongo.domain.UserProfile;


public interface UserAccountRepository extends MongoRepository<UserAccount, String>{
	UserAccount findByUserId(String userId);
	UserAccount findByUsername(String username);
	Page<UserAccount> findAllOrderByUserId(Pageable pageable);
	//@Query("{username: {$in: ?0}}")
	
	List<UserAccount> findByUsernameIn(String[] username);
	
	@Query("{ \"$or\" : [ { \"email\" : { \"$regex\" : ?0, \"$options\" : \"i\"}} , { \"firstName\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}, { \"lastName\" : { \"$regex\" : ?0, \"$options\" : \"i\"}}]} ")
	List<UserAccount> findByEmailOrFirstNameOrLastNameLike(String searchName);
}