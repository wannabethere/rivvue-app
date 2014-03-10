package com.yatrix.activity.ext.domain.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(
    ignoreUnknown = true)
public class PlacePhoto
  implements Serializable
{

  private static final long serialVersionUID = 1L;

  @JsonProperty("photo_reference")
  private String reference;

  @SuppressWarnings("unused")
  @JsonProperty("height")
  private int height;

  @SuppressWarnings("unused")
  @JsonProperty("width")
  private int width;

  @SuppressWarnings("unused")
  @JsonProperty("html_attributions")
  private List<String> htmlAttributions;

  public String getReference() {
    return reference;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }
}