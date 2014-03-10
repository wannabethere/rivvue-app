package com.yatrix.activity.store.mongo.repository;

import com.yatrix.activity.store.mongo.domain.Reference;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReferenceRepository
  extends MongoRepository<Reference, String>
{
}
