package com.yatrix.activity.store.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Initially we will be using one table. later if we have a lot of data we can use seperate tables.
 * The purpose is to create REFERENCE elements. and then link these to UserProfile.
 */

@Document(
    collection = "Reference")
public class Reference
  extends Item
{

  public enum REFERENCETYPE {

    ACTIVITY, CONTACTS, INTEREST, LOCATION, PREFERENCE, SOCIALTYPE, USER;

  }

  public Reference() {
    this(null, null, null, null, null);
  }

  /**
   * @param type
   * @param userId
   * @param refId
   */
  public Reference(REFERENCETYPE type, String userId, String refId) {

    this(type, userId, refId, null, null);

  }

  /**
   * @param type
   * @param userId
   * @param refId
   * @param name
   */
  public Reference(REFERENCETYPE type, String userId, String refId, String name) {

    this(type, userId, refId, name, null);

  }

  /**
   * @param type
   * @param userId
   * @param refId
   * @param name
   * @param value
   */
  public Reference(REFERENCETYPE type, String userId, String refId, String name, String value) {

    this.refId = refId;

    this.name = name;

    this.userId = userId;

    this.type = type;

    this.value = value;

  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the refId
   */
  public String getRefId() {
    return refId;
  }

  /**
   * @return the type
   */
  public REFERENCETYPE getType() {
    return type;
  }

  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * 
   */
  @Override
  public String toString() {
    StringBuilder wBuilder = new StringBuilder();
    wBuilder.append("Reference [refId=");
    wBuilder.append(refId);
    wBuilder.append(", name=");
    wBuilder.append(name);
    wBuilder.append(", value=");
    wBuilder.append(value);
    wBuilder.append(", type=");
    wBuilder.append(type);
    wBuilder.append(", userId=");
    wBuilder.append(userId);
    wBuilder.append("]");
    return wBuilder.toString();
  }

  /**
   */
  private static final long serialVersionUID = -4739186471933329964L;

  private final String name;

  private final String refId;

  private final REFERENCETYPE type;

  /**
   * Owner of the reference. So multiple users can have different references.
   */

  private final String userId;

  private final String value;
}
