package com.yatrix.activity.store.mongo.service.impl;

import com.yatrix.activity.store.mongo.domain.UserProfile;
import com.yatrix.activity.store.mongo.domain.Reference;
import com.yatrix.activity.store.mongo.domain.Reference.REFERENCETYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Service for initializing MongoDB with sample data using {@link MongoTemplate}
 */
public class InitMongoService {

  public void init() {
    log.info("--------------- Inside InitMongoService:init() ---------------");
    // Drop existing collections
    mongoTemplate.dropCollection("ActivityUserProfile");

    // Create new records

    String userId1 = getRandomNo();
    String refId1 = getRandomNo();
    String refName1 = "Chakra P";
    UserProfile userProfile1 = new UserProfile(userId1, "vharnath", "Harnath Valeti", "Harnath", "Valeti",
      "M", Locale.ENGLISH);

    userProfile1.setAbout("Software Engineer");
    userProfile1.setBio("Mother Tongue Telugu");
    userProfile1.setBirthday("20/09/1950");

    Reference education1 = new Reference(REFERENCETYPE.ACTIVITY, userId1, refId1, refName1, "T");
    List<Reference> educationList1 = new ArrayList<Reference>();
    educationList1.add(education1);

    userProfile1.setEducation(educationList1);
    userProfile1.setEmail("vharnath@gmail.com");

    Reference location1 = new Reference(REFERENCETYPE.LOCATION, userId1, refId1, refName1, "T");

    userProfile1.setLocation(location1);
    userProfile1.setRelationshipStatus("Married");
    userProfile1.setVerified(Boolean.FALSE);

    String userId2 = getRandomNo();
    UserProfile userProfile2 = new UserProfile(userId2, "smrNagesh", "Nagesh Bhati", "Nagesh", "Bhati",
      "M", Locale.FRENCH);
    userProfile2.setAbout("Marketing");
    userProfile2.setBio("Bla Bla Bla Bla");
    userProfile2.setBirthday("02/10/1970");
    Reference education2 = new Reference(REFERENCETYPE.ACTIVITY, userId1, refId1, refName1, "T");
    List<Reference> educationList2 = new ArrayList<Reference>();
    educationList2.add(education2);
    userProfile2.setEducation(educationList2);
    userProfile2.setEmail("smrn@gmail.com");
    Reference location2 = new Reference(REFERENCETYPE.LOCATION, userId1, refId1, refName1, "T");
    userProfile2.setLocation(location2);
    userProfile2.setRelationshipStatus("Single");
    userProfile2.setVerified(Boolean.TRUE);

    // Insert to db
    mongoTemplate.insert(userProfile1, "ActivityUserProfile");
    mongoTemplate.insert(userProfile2, "ActivityUserProfile");
  }

  public static String getRandomNo() {
    return new Long(new Random(System.nanoTime()).nextInt(1000000000)).toString();
  }

  /** */
  private Logger log = Logger.getLogger(InitMongoService.class.getName());

  @Autowired
  private MongoTemplate mongoTemplate;

}
