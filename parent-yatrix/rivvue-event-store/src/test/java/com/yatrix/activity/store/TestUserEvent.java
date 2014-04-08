/**
 * 
 */
package com.yatrix.activity.store;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yatrix.activity.store.config.MongoConfig;
import com.yatrix.activity.store.exception.ActivityDBException;
import com.yatrix.activity.store.mongo.domain.Comment;
import com.yatrix.activity.store.mongo.domain.Message.VISIBILITY;
import com.yatrix.activity.store.mongo.domain.Venue;
import com.yatrix.activity.store.mongo.domain.Participant;
import com.yatrix.activity.store.mongo.domain.Participant.TYPE;
import com.yatrix.activity.store.mongo.domain.UserEvent;
import com.yatrix.activity.store.mongo.domain.Participant.RSVPSTATUS;
import com.yatrix.activity.store.mongo.service.impl.UserEventsService;

/**
 * @author tkmald2
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoConfig.class, TestSocialConfig.class})
public class TestUserEvent {

	private UserEvent userEvent;
	
	@Autowired
	private UserEventsService service;
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		//ApplicationContext context = new ClassPathXmlApplicationContext("spring-data.xml");
		//MongoTemplate test=(MongoTemplate)context.getBean("mongoTemplate");
		
		
	}

	/**
	 * Test method for {@link com.yatrix.activity.store.mongo.domain.UserEvent#getEndTime()}.
	 * @throws ActivityDBException 
	 */
	@Test
	public void testCreateEvent() throws ActivityDBException {
		mongoTemplate.dropCollection("UserEvents2");
		UserEvent eventCreated=service.createUserEvent(this.createEvent(0));
		System.out.println(eventCreated);
	}
	
	@Test
	public void testUpdateEvent() throws ActivityDBException{
		UserEvent eventCreated=service.createUserEvent(this.createEvent(0));
		System.out.println("Before: "+ eventCreated.getInvitedIds());
		System.out.println("Before: "+ eventCreated.getAppComments());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "", "FB"), eventCreated.getId());
		System.out.println("After: "+ eventCreated.getInvitedIds());
		System.out.println("After: "+ eventCreated.getAppComments());
		System.out.println(eventCreated);
	}
	
	
	@Test
	public void testUpdateEventWithRemoveParticipants() throws ActivityDBException{
		UserEvent eventCreated=service.createUserEvent(this.createEvent(0));
		System.out.println("Before: "+ eventCreated.getInvitedIds());
		System.out.println("Before: "+ eventCreated.getAppComments());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "", "FB"), eventCreated.getId());
		System.out.println("After: "+ eventCreated.getInvitedIds());
		System.out.println("After: "+ eventCreated.getAppComments());
		System.out.println(eventCreated);
		Participant p=eventCreated.getInvitedIds().get(1);
		eventCreated=service.removeEventParticipant(p, eventCreated.getId());
		System.out.println("Removed PArticipant" + p);
		System.out.println("Removed: "+ eventCreated.getInvitedIds());
		System.out.println("Removed: "+ eventCreated.getAppComments());
		System.out.println("Removed PArticipant" + eventCreated);		
	}
	
	
	
	@Test
	public void testUpdateEventWithMessage() throws ActivityDBException{
		UserEvent eventCreated=service.createUserEvent(this.createEvent(0));
		System.out.println("Before: "+ eventCreated.getInvitedIds());
		System.out.println("Before: "+ eventCreated.getAppComments());
		Participant p = new Participant();
		p.setInviteeName("Sameer");
		p.setUserId("12121");
		p.setLupd(System.currentTimeMillis());
		
		eventCreated=service.updateUserEventMessage(p, eventCreated.getId(), "I am testing pushing Messages");
		System.out.println("Event Message: "+ eventCreated.getAppComments());
		System.out.println("Event Message: " + eventCreated);	
	}
	
	@Test
	public void testInvitedActivities() throws ActivityDBException{
		UserEvent eventCreated=service.createUserEvent(this.createEvent(0));
		System.out.println("TIS Event Message: " + eventCreated.getInvitedIds());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "", "FB"), eventCreated.getId());
		System.out.println("TIS Event Message: "+ eventCreated.getInvitedIds());
		System.out.println("TIS Event Message: " + eventCreated);
		
		UserEvent eventCreated1=service.createUserEvent(this.createEvent(1));
		System.out.println("TIS Event Message: " + eventCreated1.getInvitedIds());
		eventCreated1=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "", "FB"), eventCreated1.getId());
		
		System.out.println("TIS Event Message: "+ eventCreated1.getInvitedIds());
		System.out.println("TIS Event Message: " + eventCreated1);
		System.out.println("Invited Activities" + service.getInvitedActivities("12121"));
		
	}
	
	
	@Test
	public void testMyActivities() throws ActivityDBException{
		UserEvent eventCreated=service.createUserEvent(this.createEvent(0));
		System.out.println("TIS Event Message: " + eventCreated.getInvitedIds());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "", "FB"), eventCreated.getId());
		System.out.println("TIS Event Message: "+ eventCreated.getInvitedIds());
		System.out.println("TIS Event Message: " + eventCreated);
		
		UserEvent eventCreated1=service.createUserEvent(this.createEvent(1));
		System.out.println("TIS Event Message: " + eventCreated1.getInvitedIds());
		eventCreated1=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "", "FB"), eventCreated1.getId());
		
		System.out.println("TIS Event Message: "+ eventCreated1.getInvitedIds());
		System.out.println("TIS Event Message: " + eventCreated1);
		System.out.println("All My  Activities invited and originated" + service.getMyActivities("12121"));
		
	}
	
	
	@Test
	public void testAcceptActivity() throws ActivityDBException{
		UserEvent eventCreated=service.createUserEvent(this.createEvent(0));
		System.out.println("TIS Event Message: " + eventCreated.getInvitedIds());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "", "FB"), eventCreated.getId());
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121"));
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121").size());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "Accepted", "FB"), eventCreated.getId());
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121"));
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121").size());
	}
	
	
	@Test
	public void testPublicActivities() throws ActivityDBException{
		UserEvent eventCreated=service.createUserEvent(this.createEvent(0));
		System.out.println("TIS Event Message: " + eventCreated.getInvitedIds());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "", "FB"), eventCreated.getId());
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121"));
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121").size());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "DECLINED", "FB"), eventCreated.getId());
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121"));
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121").size());
		System.out.println("Test Public Activities");
		System.out.println(service.getActivitiesByState("CA"));
		
	}
	
	
	@Test
	public void testDeclineActivity() throws ActivityDBException{
		UserEvent eventCreated=service.createUserEvent(this.createEvent(0));
		System.out.println("TIS Event Message: " + eventCreated.getInvitedIds());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "", "FB"), eventCreated.getId());
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121"));
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121").size());
		eventCreated=service.updateUserEventStatus(this.createParticipant("12121", "Sameer 2", "DECLINED", "FB"), eventCreated.getId());
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121"));
		System.out.println("Invited Activities: " + service.getInvitedActivities("12121").size());
	}
	
	
	
	
	
	

	/**
	 * 
	 */
	
	/**
	 * Test method for {@link com.yatrix.activity.store.mongo.domain.UserEvent#getFacebookEventId()}.
	 */
	@Test
	public void testGetFacebookEventId() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link com.yatrix.activity.store.mongo.domain.UserEvent#getGoogleEventId()}.
	 */
	@Test
	public void testGetGoogleEventId() {
		assertTrue(true);
	}



	/**
	 * Test method for {@link com.yatrix.activity.store.mongo.domain.UserEvent#setFacebookFeedId(java.lang.String)}.
	 */
	@Test
	public void testSetFacebookFeedId() {
		assertTrue(true);
	}

	
	
	
	private Participant createParticipant(String userId, String userName,String status,String userType ){
		Participant p = new Participant();
		p.setInviteeName("");
		p.setLupd(System.currentTimeMillis());
		if(status.equalsIgnoreCase("ACCEPTED")){
			p.setStatus(RSVPSTATUS.ATTENDING);
		}
		else if(status.equalsIgnoreCase("MAYBE")){
			p.setStatus(RSVPSTATUS.MAYBE);
		}
		else if(status.equalsIgnoreCase("DECLINED")){
			p.setStatus(RSVPSTATUS.DECLINED);
		}
		else{
			p.setStatus(RSVPSTATUS.NOT_REPLIED);
		}
		if(userType.equalsIgnoreCase("FB")){
			p.setUserType(TYPE.FB);
		}
		else{
			p.setUserType(TYPE.APP);
		}
		p.setUserId(userId);
		return p;
	}
	
	private Comment createComment(Participant p, String message){
		Comment comment=new Comment(message, System.currentTimeMillis(), p);
		return comment;
	}
	
	private Venue createLocation(String formattedAddress,String location, String place, double[] latlng){
		Venue loc= new Venue(place+" CA",formattedAddress+ " CA", latlng);
		loc.setLocation(location+ " CA");
		loc.setTags(Arrays.asList(new String[]{"dum1","dum2"}));
		return loc;
	}
	
	public double[] getLatLong(){
		double latSt=0, latEnd=180;
		double lngSt=0, lngEnd=90;
		return new double[] {getRandomNo(latSt, latEnd), getRandomNo(lngSt,lngEnd)};
				
	}
	
	public static double getRandomNo(double st, double end) {
		double result= st+((end-st)*(new Random().nextDouble()));
	    return result;
	}
	
	private UserEvent createEvent(int index){
		String concat="";
		if(index>0){
			concat+=index;
		}
		userEvent =new UserEvent();
		userEvent.setTitle("Title of the Event" + concat);
		userEvent.setAuthorName("Sameer Mangalampalli" + concat);
		userEvent.setCategoryId("12123213"+ concat);
		userEvent.setCreatedTimeStamp(System.currentTimeMillis());
		userEvent.setDescription("sdfsddsfdsf sdfsdfsdf  adsfsdfdsf"+ concat);
		userEvent.setDisplayName("Sameers Events");
		userEvent.setStartTime(System.currentTimeMillis()+100000);
		userEvent.setEndTime(System.currentTimeMillis()+200000);
		userEvent.setLocation(this.createLocation("testaing, asdasd, 23123", "test", "MyCity", getLatLong()));
		Participant p1 = this.createParticipant("1212123213", "Sam3"+ concat,"N", "FB");
		userEvent.addInvitedId(p1);
		Participant p2 = this.createParticipant("1212123214", "Sam4"+ concat,"N", "FB");
		userEvent.addInvitedId(p2);
		Participant p3 = this.createParticipant("1212123215", "Sam5"+ concat,"N", "FB");
		userEvent.addInvitedId(p3);
		Participant p = this.createParticipant("1212123213", "Sam1"+ concat,"N", "FB");
		userEvent.addComment(this.createComment(p, p.getStatus().getMessage()));
		userEvent.setOriginatorUserId("2123123123");
		userEvent.setLupd(System.currentTimeMillis());
		userEvent.setVisibility(VISIBILITY.PUBLIC);
		
		System.out.println(userEvent);
		return userEvent;
	}
	
	
	

}
