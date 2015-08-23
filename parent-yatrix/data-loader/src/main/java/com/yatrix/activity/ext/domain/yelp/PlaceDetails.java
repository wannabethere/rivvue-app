package com.yatrix.activity.ext.domain.yelp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@JsonIgnoreProperties(
    ignoreUnknown = true)
@Document(collection="YelpPlaceDetails")
public class PlaceDetails
  implements Serializable
{

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("rating")
  private float rating;

  @JsonProperty("review_count")
  private float reviewCount;

  @JsonProperty("reviews")
  private List<PlaceReview> reviews = Collections.emptyList();

  @JsonProperty("time_created")
  private long createdTime;

  @JsonProperty("url")
  private String url;

  @JsonProperty("location")
  private Place address;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public float getReviewCount() {
    return reviewCount;
  }

  public void setReviewCount(float reviewCount) {
    this.reviewCount = reviewCount;
  }

  public List<PlaceReview> getReviews() {
    return reviews;
  }

  public void setReviews(List<PlaceReview> reviews) {
    this.reviews = reviews;
  }

  public long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(long createdTime) {
    this.createdTime = createdTime;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Place getAddress() {
    return address;
  }

  public void setAddress(Place address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "PlaceDetails [id=" + id + ", name=" + name + ", phone=" + phone + ", rating=" + rating
      + ", reviewCount=" + reviewCount + ", reviews=" + reviews + ", createdTime=" + createdTime
      + ", url=" + url + ", address=" + address + "]";
  }
}
