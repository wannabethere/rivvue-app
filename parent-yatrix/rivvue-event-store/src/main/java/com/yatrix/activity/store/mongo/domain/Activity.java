package com.yatrix.activity.store.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(
    collection = "Activities")
public class Activity
  extends Item
{

  /**
   * Serial Version UID.
   */
  private static final long serialVersionUID = 2242068081611181292L;

  private String categoryId;

  private String subCategory;

  private String displayName;

  public void setCategoryId(String id) {
    this.categoryId = id;
  }

  public String getCategory() {
    return this.categoryId;
  }

  public String getSubCategory() {
    return subCategory;
  }

  public void setSubCategory(String subCategory) {
    this.subCategory = subCategory;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }
}
