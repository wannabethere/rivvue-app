package com.yatrix.activity.store.mongo.repository;

import com.yatrix.activity.store.mongo.domain.Counter;
import com.yatrix.activity.store.utils.CounterService;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CounterServiceImpl
  implements CounterService
{

  public static final String USER_ID_SEQUENCE_NAME = "user_id";

  private final MongoTemplate mongoTemplate;

  @Inject
  public CounterServiceImpl(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
    if (mongoTemplate != null && !mongoTemplate.collectionExists(Counter.class)) {
      mongoTemplate.createCollection(Counter.class);
      Counter ctr = new Counter();
      ctr.setName("user_id");
      ctr.setSequence(1);
      ctr.setUuid(UUID.randomUUID().toString());
      mongoTemplate.save(ctr);
    }

  }

  //
  private long increaseCounter(String counterName) {
    Query query = new Query(Criteria.where("name").is(counterName));
    @SuppressWarnings("boxing")
    Update update = new Update().inc("sequence", 1);
    Counter counter = mongoTemplate.findAndModify(query, update, Counter.class); // return old
                                                                                 // Counter object
    return counter.getSequence();
  }

  @Override
  public long getNextUserIdSequence() {
    return increaseCounter(USER_ID_SEQUENCE_NAME);
  }
}