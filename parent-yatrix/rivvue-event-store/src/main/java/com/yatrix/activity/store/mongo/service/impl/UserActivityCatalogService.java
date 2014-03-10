package com.yatrix.activity.store.mongo.service.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;


import com.yatrix.activity.store.exception.ActivityDBException;
import com.yatrix.activity.store.mongo.domain.Activity;
import com.yatrix.activity.store.mongo.domain.Message;
import com.yatrix.activity.store.mongo.domain.Message.VISIBILITY;
import com.yatrix.activity.store.mongo.domain.PostMessage;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.domain.UserActivity.EVENT_STATUS;
import com.yatrix.activity.store.mongo.domain.UserToEvents;
import com.yatrix.activity.store.mongo.repository.UserActivityRepository;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserActivityCatalogService
  implements IUserActivityCatalogService
{

  private static final Logger logger = LoggerFactory.getLogger(UserActivityCatalogService.class);

  @Autowired
  private MongoTemplate mongoTemplate;

  @Inject
  private UserActivityRepository useractivityRepository;

  public UserActivityCatalogService() {

  }

  /**
   * Given a String categoryId and query we search the db and return all the activities.
   * 
   * @param categoryId
   * @param query
   * @return
   */
  @Override
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

  /**
   * Given a String activityId ,search the db and return the activity.
   * 
   * @param activityId
   * @return
   */
  @Override
  @Cacheable(
      value = "activityCache",
      key = "#activityId")
  public UserActivity findActivity(String activityId) {
    logger.debug("Searching Activities..");
    Query q = query(where("uuid").is(activityId));
    List<UserActivity> dbActivities = mongoTemplate.find(q, UserActivity.class);
    logger.debug("Searching Activities Done");
    if (dbActivities.size() == 0) {
      logger.info("no activity found with id " + activityId);
    }
    return dbActivities.get(0);
  }

  /**
   * Given a String activityId ,search the db and return the activity.
   * 
   * @param activityId
   * @return
   */
  /*
   * @Override
   * @Cacheable( value = "activityCache", key = "#uuid") public List<UserActivity>
   * findActivies(String uuid) { logger.debug("Searching Activities.."); Query q =
   * query(where("originatorUserId").is(uuid)); List<UserActivity> dbActivities =
   * mongoTemplate.find(q, UserActivity.class); logger.debug("Searching Activities Done"); if
   * (dbActivities.size() == 0) { logger.info("no activity found for user " + uuid); } return
   * dbActivities; }
   */

  /**
   * Given a String activityId ,search the db and return the activity.
   * 
   * @param activityId
   * @return
   */
  /*
   * @Override public UserActivity findUserEvent(String activityId) {
   * logger.debug("Searching Activities.."); Query q = query(where("uuid").is(activityId));
   * List<UserActivity> dbActivities = mongoTemplate.find(q, UserActivity.class);
   * logger.debug("Searching Activities Done"); if (dbActivities.size() == 0) {
   * logger.info("no activity found with id " + activityId); return null; } else { return
   * dbActivities.get(0); } }
   */

  /**
   * Given a String uuid ,search the db and return the activity.
   * 
   * @param activityId
   * @return
   */
  @Override
  @Cacheable(
      value = "activityCache",
      key = "#uuid")
  public List<UserActivity> findUserEventsByUser(String uuid) {
    logger.debug("Searching Activities..");
    Query q = query(where("originatorUserId").is(uuid));
    List<UserActivity> dbActivities = mongoTemplate.find(q, UserActivity.class);
    logger.debug("Searching Activities Done");
    if (dbActivities.size() == 0) {
      logger.info("no activity found for user " + uuid);
    }
    return dbActivities;
  }

  @Override
  @Cacheable(
	      value = "activityCache",
	      key = "#state")
	  public List<UserActivity> findAllPublicUserEventsByState(String state) {
	    logger.debug("Searching Activities..");
	    Query q = query(where("place").regex(", " + state + ",").and("visibility").is(VISIBILITY.PUBLIC.toString()));
	    List<UserActivity> dbActivities = mongoTemplate.find(q, UserActivity.class);
	    logger.debug("Searching Activities Done");
	    if (dbActivities.size() == 0) {
	      logger.info("no activity found for user " + state);
	    }
	    return dbActivities;
	  }

  
  /**
   * Creates an activity and returns the same.
   * 
   * @return
   * @throws ActivityDBException
   */
  @Override
  @CacheEvict(
      value = "activityCache",
      key = "#from")
  public UserActivity createActivity(String categoryId, String subCategoryId, String location,
		  							 String formattedAddress, String locationLat, String locationLng,
                                     String from, String to, String toAppUsers, String access, String start,
                                     String end, String message, String place) throws ActivityDBException {
    logger.debug("creating Activity");
    isvalidActivity(categoryId, subCategoryId, location, from, to, toAppUsers, access, start, end, message);

    UserActivity a = new UserActivity();
    a.setCreatedTimeStamp(Calendar.getInstance().getTime());
    if (access.equalsIgnoreCase(Message.VISIBILITY.PRIVATE.toString())) {
      a.setVisibility(Message.VISIBILITY.PRIVATE);
    } else if (access.equalsIgnoreCase(Message.VISIBILITY.PUBLIC.toString())) {
      a.setVisibility(Message.VISIBILITY.PUBLIC);
    } else if (access.equalsIgnoreCase(Message.VISIBILITY.FRIENDSONLY.toString())) {
      a.setVisibility(Message.VISIBILITY.FRIENDSONLY);
    }
    
    a.setStatus(EVENT_STATUS.PENDING);
    
    a.setOriginatorUserId(from);
    a.setLocation(location);
    a.setFormattedAddress(formattedAddress);
    a.setLocationLat(locationLat);
    a.setLocationLng(locationLng); 
    a.setCategoryId(categoryId);
    a.setSubCategory(subCategoryId);
    
    a.setPlace(place);

    // split the invitee list
    List<String> participants = new ArrayList<String>();
    participants = Arrays.asList(to.split(","));
    
    List<String> appParticipants = new ArrayList<String>();
    appParticipants = Arrays.asList(toAppUsers.split(","));
    
    Date startTime = null;
    Date endTime = null;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
    try {
      startTime = format.parse(start);
      endTime = format.parse(end);
    } catch (ParseException e) {
      logger.error("Event start/end date format is incorrect");

      throw new ActivityDBException("Activity start/end date format is incorrect");
    }
    a.setParticipants(participants);
    a.setAppParticipants(appParticipants);
    a.setStartTime(startTime);
    a.setEndTime(endTime);

    PostMessage messageposted = new PostMessage();
    messageposted.setAuthorId(from);
    messageposted.setMessage(message);
    a.setMessageposted(messageposted);

    UserActivity created = useractivityRepository.save(a);
    logger.debug("activity created");
    return created;
  }

  private boolean isvalidActivity(String categoryId, String subCategoryId, String location,
                                  String from, String to, String toAppUsers, String access, String start, String end,
                                  String message) throws ActivityDBException {

    if (categoryId == null || categoryId.equals("") || categoryId.equalsIgnoreCase("select")) {
      logger.error("Activity category value is empty");
      throw new ActivityDBException("category.empty");

    }
    if (location == null || location.equals("")) {
      logger.error("Activity location value is empty");
      throw new ActivityDBException("location.empty");

    }
    if (from == null || from.equals("")) {
      logger.error("User name is empty");

      throw new ActivityDBException("user.empty");

    }
    if ( (to == null || to.equals("")) && (toAppUsers == null || toAppUsers.equals(""))) {
      logger.error("Invitee user list is empty");

      throw new ActivityDBException("invitee.empty");

    }
    if (access == null || access.equals("")) {
      logger.error("Event access value is empty");

      throw new ActivityDBException("access.empty");

    }
    if (location == null || location.equals("")) {
      logger.error("Event location value is empty");

      throw new ActivityDBException("location.empty");

    }
    if (start == null || start.equals("")) {
      logger.error("Event start date value is incorrect");

      throw new ActivityDBException("startdate.empty");
    }
    if (end == null || end.equals("")) {
      logger.error("Event end date value is incorrect");

      throw new ActivityDBException("enddate.empty");
    }

    if (start != null && !start.equals("") && end != null && !end.equals("")) {
      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ENGLISH);
      try {
        format.parse(start);
        format.parse(end);
      } catch (ParseException e) {
        logger.error("Event start/end date format is incorrect");

        throw new ActivityDBException("date.error");
      }
    }
    if (message == null || message.equals("")) {
      logger.error("message value is empty");

      throw new ActivityDBException("message.empty");

    }
    return true;
  }

  @Override
  @Caching(
      evict = { @CacheEvict(
          value = "activityCache",
          key = "#activity.id"), @CacheEvict(
          value = "activityCache",
          key = "#activity.originatorUserId") })
  public boolean updateActivity(UserActivity activity) {
//    UserActivity existingActivity = findActivity(activity.getId());
//    if (existingActivity == null) {
//      return false;
//    }
//
//    existingActivity.setFacebookEventId(activity.getFacebookEventId());

    UserActivity saved = useractivityRepository.save(activity);
    if (saved == null) {
      return false;
    }

    return true;
  }
	@Override
	public void save(UserToEvents userToEvent) {
		mongoTemplate.save(userToEvent);
		
	}

	@Override
	public Long countActivies(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Activity> searchActivities(String categoryId, String query,
			Integer from, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserActivity> findActivies(EVENT_STATUS eventStatus,
			Integer skip, Integer limit) {
	    
		logger.debug("Searching Activities..");

		Query q = query(where("status").is(eventStatus));
		q.skip(skip);
		q.limit(limit);
	    List<UserActivity> dbActivities = mongoTemplate.find(q, UserActivity.class);
	    logger.debug("Searching Activities Done");
	    
	    if (dbActivities.size() == 0) {
	      logger.info("no activity found for status " + eventStatus);
	    }
	    
	    return dbActivities;
	}

	@Override
	public List<UserActivity> findEventsIAmInvited(String username, String facebookId) {
		logger.debug("Searching Activities..");

		Criteria q = where("appParticipants").in(username);
		Criteria q1 = where("participants").in(facebookId);
		Query query;
		
		if(StringUtils.isEmpty(facebookId)){
			query = query(new Criteria().orOperator(q));
		} else {
			query = query(new Criteria().orOperator(q, q1));	
		}
		
	    List<UserActivity> dbActivities = mongoTemplate.find(query, UserActivity.class);
	    logger.debug("Searching Activities Done");
	    
	    if (dbActivities.size() == 0) {
	      logger.info("no activity found for user invited " + username);
	    }
	    
	    return dbActivities;
	}
	
}
