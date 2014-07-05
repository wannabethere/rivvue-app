package com.yatrix.activity.batch.loader.config;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.StringUtils;

/**
 * Configuration for MongoDB.
 * 
 * @author Yuan Ji
 */
@Configuration
@EnableMongoRepositories(
    basePackages = "com.yatrix.activity.mongo.repository")
public class MongoConfig
  extends AbstractMongoConfiguration
{

  @Inject
  private Environment environment;

  @Override
  public String getDatabaseName() {
	  //Bad Hack to figure out the env
    return StringUtils.isEmpty(environment.getProperty("mongo.db.name"))?"socialeventdb1":environment.getProperty("mongo.db.name");
  }

  /*
   * @Override protected UserCredentials getUserCredentials() { return new
   * UserCredentials(environment.getProperty("mongodb.username"),
   * environment.getProperty("mongodb.password")); }
   */

  @Override
  public String getMappingBasePackage() {
    return "com.yatrix.activity.store.mongo.repository";
  }

  @Override
  public Mongo mongo() throws Exception {
    @SuppressWarnings("boxing")
    Mongo mongo;
    if(environment!=null && !StringUtils.isEmpty(environment.getProperty("mongo.host.name"))){
    	 mongo = new Mongo(environment.getProperty("mongo.host.name"), environment.getProperty(
    		      "mongo.host.port", Integer.class));
    	 System.out.println("Debug1");
    }
    else{
    	System.out.println("Debug 2");
    	 mongo = new Mongo("localhost",27017);
    }
   
    mongo.setWriteConcern(WriteConcern.SAFE);
    return mongo;
  }
}
