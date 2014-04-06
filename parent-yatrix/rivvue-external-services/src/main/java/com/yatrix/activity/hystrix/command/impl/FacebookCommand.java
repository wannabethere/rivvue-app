package com.yatrix.activity.hystrix.command.impl;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.yatrix.activity.hystrix.command.HystrixSocialResult;
import com.yatrix.activity.hystrix.command.IFacebookCommand;
import com.yatrix.activity.store.mongo.domain.UserActivity;
import com.yatrix.activity.store.mongo.repository.ConnectionService;
import com.yatrix.activity.store.mongo.service.IUserActivityCatalogService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Service;

@Service
@Scope( value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
public class FacebookCommand
  extends HystrixCommand<HystrixSocialResult>
  implements IFacebookCommand
{

  private static final Logger logger = LoggerFactory.getLogger(FacebookCommand.class);

  @Autowired
  ConnectionService userSocialConnectionService;

  @Autowired
  IUserActivityCatalogService userActivityCatalogService;

  @Autowired
  ConnectionFactoryLocator connectionFactoryLocator;

  public FacebookCommand() {
    // Set to one minute
    super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("com.yatrix.activity"))
      .andCommandKey(HystrixCommandKey.Factory.asKey("FacebookCommand"))
      .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("FacebookThread"))
      .andCommandPropertiesDefaults(
      // we default to a one minute timeout for primary
        HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(60000)));
    logger.info("Inside Facebook Command");
  }

  @Override
  public HystrixSocialResult run() throws Exception {

    logger
      .info("* * * * * * * * * * * * * * * * * Running Facebook Command * * * * * * * * * * * * * * * * *");

    if(userActivity == null){
    	userActivity = userActivityCatalogService.findActivity(userActivityId);
    }

    logger.info("User Activity1: " + userActivity);

    String providerId = connectionFactoryLocator.getConnectionFactory(Facebook.class).getProviderId();

    String userName = userActivity.getOriginatorUserId();
    @SuppressWarnings("unchecked")
    Connection<Facebook> connection = (Connection<Facebook>)userSocialConnectionService.getPrimaryConnection(userName, providerId);
    
    facebook = connection.getApi();

    String message = userActivity.getMessageposted() == null ? "" : userActivity.getMessageposted().getMessage();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    String invitees[] = userActivity.getParticipants().toArray(new String[0]);
    long stime=userActivity.getStartTime().getTime();
    long etime=userActivity.getEndTime().getTime();
    
    if(stime+3600>etime){
    	etime=stime+3600;
    }
    Date startTime= new Date(stime);
    Date endTime= new Date(etime);
    
    String startDate = dateFormat.format(startTime);
    String endDate = dateFormat.format(endTime);
    String eventId = facebook.eventOperations().createEvent(message, startDate, endDate);
    facebook.eventOperations().sendInvitation(eventId, invitees);

    // TODO: Decide whether to update facebook event id to our database here or to the place where
    // it is returned.
    // activity.setFacebookEventId(eventId);
    logger.info("Facebook activity created: " + eventId);

    return new HystrixSocialResult(Boolean.TRUE, eventId, "Event successfully created!");
  }

  @Override
  public Future<HystrixSocialResult> executeFacebookCommand(String pUserActivityId) {
    userActivityId = pUserActivityId;
    return this.queue();
  }
  
  @Override
  public Future<HystrixSocialResult> executeFacebookCommand(
  		UserActivity pUserActivity) {
  	userActivity = pUserActivity;
  	return this.queue();
  }

  @Override
  protected HystrixSocialResult getFallback() {
    logger
      .info("* * * * * * * * * * * * * * * * * *Running fallback method * * * * * * * * * * * * * * * * * *");
    // TODO: I believe we are going to store failed records with event id into a table and try after
    // sometime.
    // So constructing a failed record. -- Harnath
    Throwable exception = getFailedExecutionException();

    logger.error("Facebook command exception: ", exception);

    return new HystrixSocialResult(Boolean.FALSE, null, "Event creation failed!");
  }

  String userActivityId;
  Facebook facebook;
  UserActivity userActivity;


}
