package com.yatrix.activity.store.mongo.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(
		collection = "ActivityUserProfile")
		public class UserProfile
		extends Item
		{


	/**
	 */
	private static final long serialVersionUID = -5820308160993319697L;
	private String about;
	private String bio;
	private String birthday;
	private List<Reference> education;
	private String email;
	private final String firstName;
	private final String gender;
	private Reference hometown;
	private List<Reference> inspirationalPeople;
	private final String lastName;
	private String link;
	private final Locale locale;
	private Reference location;
	private String middleName;
	private List<Reference> myPreferredActivities;
	private final String name;
	private String relationshipStatus;
	private Reference significantOther;
	
	
	private PROFILETYPE srcprofileType;
	private String thirdPartyId;
	private Float timezone;
	private Date updatedTime;
	@Indexed
	private final String userId;
	// FB Profile Params
	private final String username;
	private Boolean verified;
	private List<String> website = new ArrayList<String>();
	private String parentId;
	private boolean appAccount;
	private String imageUrl;
	private PSTATUS status;
	
	public static enum PSTATUS{
		ACTIVE,DELETED,NOTUSING;
	}
	
	public static enum PROFILETYPE {
		APP, FB, LIN, TWT;
	}

	public UserProfile(String userId, String username, String name, String firstName,
			String lastName, String gender, Locale locale) {
		this.userId= userId;
		this.username = username;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.locale = locale;
	}

	/**
	 * @return the about
	 */
	 public String getAbout() {
		 return about;
	 }

	 /**
	  * @return the bio
	  */
	 public String getBio() {
		 return bio;
	 }

	 /**
	  * @return the birthday
	  */
	 public String getBirthday() {
		 return birthday;
	 }

	 /**
	  * @return the education
	  */
	 public List<Reference> getEducation() {
		 return education;
	 }

	 /**
	  * @return the email
	  */
	 public String getEmail() {
		 return email;
	 }

	 /**
	  * @return the firstName
	  */
	 public String getFirstName() {
		 return firstName;
	 }

	 /**
	  * @return the gender
	  */
	 public String getGender() {
		 return gender;
	 }

	 /**
	  * @return the hometown
	  */
	 public Reference getHometown() {
		 return hometown;
	 }

	 /**
	  * @return the inspirationalPeople
	  */
	 public List<Reference> getInspirationalPeople() {
		 return inspirationalPeople;
	 }

	 /**
	  * @return the lastName
	  */
	 public String getLastName() {
		 return lastName;
	 }

	 /**
	  * @return the link
	  */
	 public String getLink() {
		 return link;
	 }
	 
	 public String getUserId(){
		 return userId;
	 }
	 /**
	  * @return the locale
	  */
	 public Locale getLocale() {
		 return locale;
	 }

	 /**
	  * @return the location
	  */
	 public Reference getLocation() {
		 return location;
	 }

	 /**
	  * @return the middleName
	  */
	 public String getMiddleName() {
		 return middleName;
	 }

	 /**
	  * @return the myPreferredActivities
	  */
	 public List<Reference> getMyPreferredActivities() {
		 return myPreferredActivities;
	 }

	 /**
	  * @return the name
	  */
	 public String getName() {
		 return name;
	 }

	 /**
	  * @return the relationshipStatus
	  */
	 public String getRelationshipStatus() {
		 return relationshipStatus;
	 }

	 /**
	  * @return the significantOther
	  */
	 public Reference getSignificantOther() {
		 return significantOther;
	 }

	
	 /**
	  * @return the srcprofileType
	  */
	 public PROFILETYPE getSrcprofileType() {
		 return srcprofileType;
	 }

	 /**
	  * @return the thirdPartyId
	  */
	 public String getThirdPartyId() {
		 return thirdPartyId;
	 }

	 /**
	  * @return the timezone
	  */
	 public Float getTimezone() {
		 return timezone;
	 }

	 /**
	  * @return the updatedTime
	  */
	 public Date getUpdatedTime() {
		 return updatedTime;
	 }

	
	 /**
	  * @return the username
	  */
	 public String getUsername() {
		 return username;
	 }

	 /**
	  * @return the verified
	  */
	 public Boolean getVerified() {
		 return verified;
	 }

	 /**
	  * @return the website
	  */
	 public List<String> getWebsite() {
		 return website;
	 }

	 /**
	  * @param pAbout
	  *          the about to set
	  */
	 public void setAbout(String pAbout) {
		 about = pAbout;
	 }

	 /**
	  * @param pBio
	  *          the bio to set
	  */
	 public void setBio(String pBio) {
		 bio = pBio;
	 }

	 /**
	  * @param pBirthday
	  *          the birthday to set
	  */
	 public void setBirthday(String pBirthday) {
		 birthday = pBirthday;
	 }

	 /**
	  * @param pEducation
	  *          the education to set
	  */
	 public void setEducation(List<Reference> pEducation) {
		 education = pEducation;
	 }

	 /**
	  * @param pEmail
	  *          the email to set
	  */
	 public void setEmail(String pEmail) {
		 email = pEmail;
	 }

	 /**
	  * @param pHometown
	  *          the hometown to set
	  */
	 public void setHometown(Reference pHometown) {
		 hometown = pHometown;
	 }

	 /**
	  * @param pInspirationalPeople
	  *          the inspirationalPeople to set
	  */
	 public void setInspirationalPeople(List<Reference> pInspirationalPeople) {
		 inspirationalPeople = pInspirationalPeople;
	 }

	 /**
	  * @param pLink
	  *          the link to set
	  */
	 public void setLink(String pLink) {
		 link = pLink;
	 }

	 /**
	  * @param pLocation
	  *          the location to set
	  */
	 public void setLocation(Reference pLocation) {
		 location = pLocation;
	 }

	 /**
	  * @param pMiddleName
	  *          the middleName to set
	  */
	 public void setMiddleName(String pMiddleName) {
		 middleName = pMiddleName;
	 }

	 /**
	  * @param pMyPreferredActivities
	  *          the myPreferredActivities to set
	  */
	 public void setMyPreferredActivities(List<Reference> pMyPreferredActivities) {
		 myPreferredActivities = pMyPreferredActivities;
	 }

	 /**
	  * @param pRelationshipStatus
	  *          the relationshipStatus to set
	  */
	 public void setRelationshipStatus(String pRelationshipStatus) {
		 relationshipStatus = pRelationshipStatus;
	 }

	 /**
	  * @param pSignificantOther
	  *          the significantOther to set
	  */
	 public void setSignificantOther(Reference pSignificantOther) {
		 significantOther = pSignificantOther;
	 }

	 
	 /**
	  * @param pSrcprofileType
	  *          the srcprofileType to set
	  */
	 public void setSrcprofileType(PROFILETYPE pSrcprofileType) {
		 srcprofileType = pSrcprofileType;
	 }

	 /**
	  * @param pThirdPartyId
	  *          the thirdPartyId to set
	  */
	 public void setThirdPartyId(String pThirdPartyId) {
		 thirdPartyId = pThirdPartyId;
	 }
	 
	 public void setParentId(String parentId){
		 this.parentId=parentId;
	 }

	 /**
	  * @param pTimezone
	  *          the timezone to set
	  */
	 public void setTimezone(Float pTimezone) {
		 timezone = pTimezone;
	 }

	 /**
	  * @param pUpdatedTime
	  *          the updatedTime to set
	  */
	 public void setUpdatedTime(Date pUpdatedTime) {
		 updatedTime = pUpdatedTime;
	 }

	 /**
	  * @param pVerified
	  *          the verified to set
	  */
	 public void setVerified(Boolean pVerified) {
		 verified = pVerified;
	 }

	 /**
	  * @param pWebsite
	  *          the website to set
	  */
	 public void addWebsite(String pWebsite) {
		 this.website.add(pWebsite);
	 }
	 
	 /**
	  * @param pWebsite
	  *          the website to set
	  */
	 public void setWebsite(List<String> pWebsite) {
		 website.addAll(pWebsite);
	 }

	 public String getParentId() {
		 return parentId;
	 }

	 

	 public void setAppAccount(boolean appAccount) {
		 this.appAccount = appAccount;
	 }

	 public boolean hasAppAccount() {
		 return appAccount;
	 }

	 /**
	  * {@inheritDoc}
	  */
	 @Override
	 public String toString() {
		 StringBuilder wBuilder = new StringBuilder();
		 wBuilder.append("UserProfile [parentId=");
		 wBuilder.append(parentId);
		 wBuilder.append(", userId=");
		 wBuilder.append(userId);
		 wBuilder.append(", srcprofileType=");
		 wBuilder.append(srcprofileType);
		 wBuilder.append(", username=");
		 wBuilder.append(username);
		 wBuilder.append(", name=");
		 wBuilder.append(name);
		 wBuilder.append(", firstName=");
		 wBuilder.append(firstName);
		 wBuilder.append(", middleName=");
		 wBuilder.append(middleName);
		 wBuilder.append(", lastName=");
		 wBuilder.append(lastName);
		 wBuilder.append(", gender=");
		 wBuilder.append(gender);
		 wBuilder.append(", locale=");
		 wBuilder.append(locale);
		 wBuilder.append(", link=");
		 wBuilder.append(link);
		 wBuilder.append(", website=");
		 wBuilder.append(website);
		 wBuilder.append(", email=");
		 wBuilder.append(email);
		 wBuilder.append(", thirdPartyId=");
		 wBuilder.append(thirdPartyId);
		 wBuilder.append(", timezone=");
		 wBuilder.append(timezone);
		 wBuilder.append(", updatedTime=");
		 wBuilder.append(updatedTime);
		 wBuilder.append(", verified=");
		 wBuilder.append(verified);
		 wBuilder.append(", about=");
		 wBuilder.append(about);
		 wBuilder.append(", bio=");
		 wBuilder.append(bio);
		 wBuilder.append(", birthday=");
		 wBuilder.append(birthday);
		 wBuilder.append(", location=");
		 wBuilder.append(location);
		 wBuilder.append(", hometown=");
		 wBuilder.append(hometown);
		 wBuilder.append(", myPreferredActivities=");
		 wBuilder.append(myPreferredActivities);
		 wBuilder.append(", inspirationalPeople=");
		 wBuilder.append(inspirationalPeople);
		 wBuilder.append(", relationshipStatus=");
		 wBuilder.append(relationshipStatus);
		 wBuilder.append(", significantOther=");
		 wBuilder.append(significantOther);
		 wBuilder.append(", education=");
		 wBuilder.append(education);
		 wBuilder.append("]");
		 return wBuilder.toString();
	 }

	public void setImageUrl(String imageUrl) {
		this.imageUrl= imageUrl;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setStatus(PSTATUS status) {
		this.status = status;
	}

	public PSTATUS getStatus() {
		return status;
	}
}