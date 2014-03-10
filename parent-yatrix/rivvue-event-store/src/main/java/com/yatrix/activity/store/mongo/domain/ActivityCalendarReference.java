package com.yatrix.activity.store.mongo.domain;

public class ActivityCalendarReference
  extends Item
{

  /**
	 * 
	 */
  private static final long serialVersionUID = -2409745569669749927L;

  private String activityId;

  public static enum EVENT_STATUS {
    ACCEPTED, REJECTED, MAYBE, NEWPROPOSAL
  };

  // Id in the application that the user has created or inserted for his friends.
  private String friendId;

  private EVENT_STATUS status;

  public String getActivityId() {
    return activityId;
  }

  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }

  public String getFriendId() {
    return friendId;
  }

  public void setFriendId(String friendId) {
    this.friendId = friendId;
  }

  public EVENT_STATUS getStatus() {
    return status;
  }

  public void setStatus(EVENT_STATUS status) {
    this.status = status;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((activityId == null) ? 0 : activityId.hashCode());
    result = prime * result + ((friendId == null) ? 0 : friendId.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    ActivityCalendarReference other = (ActivityCalendarReference)obj;
    if (activityId == null) {
      if (other.activityId != null)
        return false;
    } else if (!activityId.equals(other.activityId))
      return false;
    if (friendId == null) {
      if (other.friendId != null)
        return false;
    } else if (!friendId.equals(other.friendId))
      return false;
    if (status != other.status)
      return false;
    return true;
  }
}
