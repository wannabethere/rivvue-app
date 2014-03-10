package com.yatrix.activity.store.mongo.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.yatrix.activity.store.mongo.domain.Activity;
import com.yatrix.activity.store.mongo.domain.Category;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ActivityCatalogService {

  private static final Logger logger = LoggerFactory.getLogger(ActivityCatalogService.class);

  @Autowired
  private MongoTemplate mongoTemplate;

  public ActivityCatalogService() {

  }

  /**
   * Just Returns all the categories if query String is null or empty. or search for the category
   * with a name.
   * 
   * @return
   */
  public List<Category> findCategories(String query) {

    logger.debug("Getting Categories");
    List<Category> dbCategories = null;
    if (StringUtils.isEmpty(query)) {
      dbCategories = mongoTemplate.findAll(Category.class);
    } else {
      Query q = query(where("displayName").regex(query));
      dbCategories = mongoTemplate.find(q, Category.class);
    }
    logger.debug("Getting Categories Done");
    if (dbCategories.size() == 0) {
      // throw new MongoOperationException();
      logger.error("Server Not initialized and Please fix the exception");

    }
    return dbCategories;
  }
  
  /**
   * Just Returns all the categories if query String is null or empty. or search for the category
   * with a name.
   * 
   * @return
   */
  public Category findCategory(String categoryId) {

    logger.debug("Getting Categories");
    List<Category> dbCategories = null;
    if (!StringUtils.isEmpty(categoryId)) {
    	Query q = query(where("categoryId").is(categoryId));
        dbCategories = mongoTemplate.find(q, Category.class);
      
    } 
    logger.debug("Getting Categories Done");
    if (dbCategories.size() == 0) {
      // throw new MongoOperationException();
      //Bad fix need to really do something about the initialization piece.
      dbCategories = mongoTemplate.findAll(Category.class);
      logger.error("Server Not initialized and Please fix the exception");
    }
    return dbCategories.get(0);
  }

  /**
   * Given a String categoryId and query we search the db and return all the activities.
   * 
   * @param categoryId
   * @param query
   * @return
   */
  public List<Activity> searchActivities(String categoryId, String query) {
    logger.debug("Searching Activity Categories");
    Query q = query(where("categoryId").is(categoryId).and("displayName").regex(query));
    List<Activity> dbActivities = mongoTemplate.find(q, Activity.class);
    logger.debug("Searching Categories Done");
    if (dbActivities.size() == 0) {
      logger
        .error("Server Not initialized and Please fix the exception or the query is not working please check it.");
      logger.info("returning all activities of category");
      q = query(where("categoryId").is(categoryId));
      dbActivities = mongoTemplate.find(q, Activity.class);
    }
    return dbActivities;
  }
}
