package com.yatrix.activity.ext.domain.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(
    ignoreUnknown = true)
public class SearchResult
  implements Serializable
{

  private static final long serialVersionUID = 1L;

  @JsonProperty("name")
  private String name;

  @JsonProperty("icon")
  private String icon;

  @JsonProperty("url")
  private String url;

  @JsonProperty("formatted_address")
  private String address;

  @JsonProperty("geometry")
  private PlaceGeometry geometry;

  @JsonProperty("photos")
  private List<PlacePhoto> photos = Collections.emptyList();

  @JsonProperty("rating")
  private float rating;

  @JsonProperty("price_level")
  private int priceLevel;

  @JsonProperty("types")
  private List<String> types;

  @JsonProperty("reference")
  private String reference;

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }

  public int getPriceLevel() {
    return priceLevel;
  }

  public void setPriceLevel(int priceLevel) {
    this.priceLevel = priceLevel;
  }

  public List<String> getTypes() {
    return types;
  }

  public void setTypes(List<String> types) {
    this.types = types;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void setGeometry(PlaceGeometry geometry) {
    this.geometry = geometry;
  }

  public void setPhotos(List<PlacePhoto> photos) {
    this.photos = photos;
  }

  public String getAddress() {
    return address;
  }

  public PlaceGeometry getGeometry() {
    return geometry;
  }

  public String getIcon() {
    return icon;
  }

  public String getName() {
    return name;
  }

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }

  public List<PlacePhoto> getPhotos() {
    return photos;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public String toString() {
    return "SearchResult [name=" + name + ", icon=" + icon + ", url=" + url + ", address="
      + address + ", geometry=" + geometry + ", photos=" + photos + ", rating=" + rating
      + ", priceLevel=" + priceLevel + ", types=" + types + "reference=" + reference + "]";
  }

}
