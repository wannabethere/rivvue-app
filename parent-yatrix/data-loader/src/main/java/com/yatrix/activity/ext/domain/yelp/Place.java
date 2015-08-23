package com.yatrix.activity.ext.domain.yelp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(
    ignoreUnknown = true)
public class Place {

  @JsonProperty("city")
  private String city;

  @JsonProperty("neighborhoods")
  private List<String> neighborhood;

  @JsonProperty("display_address")
  private List<String> address;

  @JsonProperty("postal_code")
  private String postalCode;

  @JsonProperty("state_code")
  private String stateCode;

  @JsonProperty("time")
  private long time;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public List<String> getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(List<String> neighborhood) {
    this.neighborhood = neighborhood;
  }

  public List<String> getAddress() {
    return address;
  }

  public void setAddress(List<String> address) {
    this.address = address;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getStateCode() {
    return stateCode;
  }

  public void setStateCode(String stateCode) {
    this.stateCode = stateCode;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

}