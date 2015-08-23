package com.yatrix.activity.ext.domain.yelp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(
    ignoreUnknown = true)
public class PlaceReview
  implements Serializable
{

  private static final long serialVersionUID = 1L;

  @JsonProperty("excerpt")
  private String review;

  @JsonProperty("rating")
  private float rating;

  @JsonProperty("rating_img_url_small")
  private String ratingImageSmall;

  @JsonProperty("time_created")
  private long createdTime;

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public long getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(long createdTime) {
    this.createdTime = createdTime;
  }

  public String getRatingImageSmall() {
    return ratingImageSmall;
  }

  public void setRatingImageSmall(String url) {
    this.ratingImageSmall = url;
  }

  @Override
  public String toString() {
    return "PlaceReview [review=" + review + ", rating=" + rating + ", ratingImageSmall="
      + ratingImageSmall + ", createdTime=" + createdTime + "]";
  }
}