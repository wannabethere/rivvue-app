package com.yatrix.activity.store.mongo.domain;

import java.util.Date;
import java.util.List;

public abstract class Message
  extends Item
{

  /**
	 * 
	 */
  private static final long serialVersionUID = 1819015426721833609L;

  public static enum MESSAGE_TYPE {
    CONVERSATION, MESSAGE
  };

  String originatorUserId;

  List<String> participants;

  PostMessage messageposted;

  Date createdTimeStamp;

  Date updatedTimeStamp;

  public static enum STATUS {
    ACTIVE, DELETED
  };

  public static enum VISIBILITY {
    PRIVATE, PUBLIC, FRIENDSONLY
  };

  private STATUS processedStatus;

  private VISIBILITY visibility;

  private MESSAGE_TYPE messageStatus;

  public String getOriginatorUserId() {
    return originatorUserId;
  }

  public void setOriginatorUserId(String originatorUserId) {
    this.originatorUserId = originatorUserId;
  }

  public List<String> getParticipants() {
    return participants;
  }

  public void setParticipants(List<String> participants) {
    this.participants = participants;
  }

  public PostMessage getMessageposted() {
    return messageposted;
  }

  public void setMessageposted(PostMessage messageposted) {
    this.messageposted = messageposted;
  }

  public Date getCreatedTimeStamp() {
    return createdTimeStamp;
  }

  public void setCreatedTimeStamp(Date createdTimeStamp) {
    this.createdTimeStamp = createdTimeStamp;
  }

  public Date getUpdatedTimeStamp() {
    return updatedTimeStamp;
  }

  public void setUpdatedTimeStamp(Date updatedTimeStamp) {
    this.updatedTimeStamp = updatedTimeStamp;
  }

  public STATUS getProcessedStatus() {
    return processedStatus;
  }

  public void setProcessedStatus(STATUS processedStatus) {
    this.processedStatus = processedStatus;
  }

  public VISIBILITY getVisibility() {
    return visibility;
  }

  public void setVisibility(VISIBILITY visibility) {
    this.visibility = visibility;
  }

  public MESSAGE_TYPE getMessageStatus() {
    return messageStatus;
  }

  public void setMessageStatus(MESSAGE_TYPE messageStatus) {
    this.messageStatus = messageStatus;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((createdTimeStamp == null) ? 0 : createdTimeStamp.hashCode());
    result = prime * result + ((messageStatus == null) ? 0 : messageStatus.hashCode());
    result = prime * result + ((messageposted == null) ? 0 : messageposted.hashCode());
    result = prime * result + ((originatorUserId == null) ? 0 : originatorUserId.hashCode());
    result = prime * result + ((participants == null) ? 0 : participants.hashCode());
    result = prime * result + ((processedStatus == null) ? 0 : processedStatus.hashCode());
    result = prime * result + ((updatedTimeStamp == null) ? 0 : updatedTimeStamp.hashCode());
    result = prime * result + ((visibility == null) ? 0 : visibility.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Message other = (Message)obj;
    if (createdTimeStamp == null) {
      if (other.createdTimeStamp != null)
        return false;
    } else if (!createdTimeStamp.equals(other.createdTimeStamp))
      return false;
    if (messageStatus != other.messageStatus)
      return false;
    if (messageposted == null) {
      if (other.messageposted != null)
        return false;
    } else if (!messageposted.equals(other.messageposted))
      return false;
    if (originatorUserId == null) {
      if (other.originatorUserId != null)
        return false;
    } else if (!originatorUserId.equals(other.originatorUserId))
      return false;
    if (participants == null) {
      if (other.participants != null)
        return false;
    } else if (!participants.equals(other.participants))
      return false;
    if (processedStatus != other.processedStatus)
      return false;
    if (updatedTimeStamp == null) {
      if (other.updatedTimeStamp != null)
        return false;
    } else if (!updatedTimeStamp.equals(other.updatedTimeStamp))
      return false;
    if (visibility != other.visibility)
      return false;
    return true;
  }
}
