package com.yatrix.activity.store.config;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

import javax.inject.Inject;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuration for MongoDB.
 * 
 * @author Yuan Ji
 */
@Configuration
@EnableMongoRepositories(
    basePackages = "com.yatrix.activity.store.mongo.repository")
public class MongoConfig
  extends AbstractMongoConfiguration
{

  @Inject
  private Environment environment;

  @Override
  public String getDatabaseName() {
    return environment.getProperty("mongo.db.name");
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
    Mongo mongo = new Mongo(environment.getProperty("mongo.host.name"), environment.getProperty(
      "mongo.host.port", Integer.class));
    mongo.setWriteConcern(WriteConcern.SAFE);
    return mongo;
  }
}
