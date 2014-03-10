package com.yatrix.activity.store.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(
    collection = "ActivityCategory")
public class Category
  extends Item
{

  /**
   *
   */
  private static final long serialVersionUID = 1656117077163881956L;

  private String categoryName;

  private String displayName;

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
