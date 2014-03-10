package com.yatrix.activity.store.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("serial")
@Document(
    collection = "Counter")
public class Counter
  extends Item
{

  private String name;

  private long sequence;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getSequence() {
    return sequence;
  }

  public void setSequence(long sequence) {
    this.sequence = sequence;
  }
}
