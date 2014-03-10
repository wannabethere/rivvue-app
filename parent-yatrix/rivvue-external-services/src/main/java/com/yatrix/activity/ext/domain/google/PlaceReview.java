package com.yatrix.activity.ext.domain.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(
    ignoreUnknown = true)
public class PlaceReview {

  @JsonProperty("author_name")
  private String authorName;

  @JsonProperty("author_url")
  private String url;

  @SuppressWarnings("unused")
  @JsonProperty("text")
  private String text;

  @JsonProperty("time")
  private long time;

  @JsonProperty("rating")
  private int rating;

  @JsonProperty("aspects")
  private List<PlaceAspect> aspects = Collections.emptyList();

  public void setAuthorName(String author) {
    authorName = author;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public long getTime() {
    return time;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public int getRating() {
    return rating;
  }

  public void setAspects(List<PlaceAspect> aspects) {
    this.aspects = aspects;
  }

  public List<PlaceAspect> getAspects() {
    return aspects;
  }

}