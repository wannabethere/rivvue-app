package com.yatrix.activity.store.mongo.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.yatrix.activity.store.mongo.domain.ZipCodes;

public interface ZipCodeRepository extends MongoRepository<ZipCodes, Serializable>{

	Page<ZipCodes> findAll(Pageable pageable);
	
	long count();
}
