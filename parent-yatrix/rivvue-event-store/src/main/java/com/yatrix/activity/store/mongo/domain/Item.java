package com.yatrix.activity.store.mongo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

/**
 * @author RAVI
 */
@Document
public abstract class Item
  implements Serializable
{

  public Item() {
    this(UUID.randomUUID());
  }

  public Item(UUID guid) {
    Assert.notNull(guid, "UUID is required");
    setUuid(guid.toString());
    this.timeCreated = new Date();
    this.id = uuid;
  }

  public String getId() {
    return this.id;
  }

  /**
   * In most instances we can rely on the UUID to identify the object. Subclasses may want a user
   * friendly identifier for constructing easy to read urls <code>
   *    /user/1883c578-76be-47fb-a5c1-7bbea3bf7fd0 using uuid as the identifier
   * 
   *    /user/jsmith using the username as the identifier
   * 
   * </code>
   * 
   * @return Object unique identifier for the object
   */
  public Object getIdentifier() {
    return getUuid().toString();
  }

  public Date getTimeCreated() {
    return timeCreated;
  }

  public UUID getUuid() {
    return UUID.fromString(uuid);
  }

  public int getVersion() {
    return version;
  }

  @Override
  public int hashCode() {
    return getUuid().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Item other = (Item)obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;

    return true;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  /**
	 * 
	 */
  private static final long serialVersionUID = 5016820684287697762L;

  @Id
  private String id;

  private Date timeCreated;

  /**
   * All objects will have a unique UUID which allows for the decoupling from DB generated ids
   */
  private String uuid;

  private int version;
}