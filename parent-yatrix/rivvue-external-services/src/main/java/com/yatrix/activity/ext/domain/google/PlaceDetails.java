package com.yatrix.activity.ext.domain.google;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetails implements Serializable{

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
	
	@JsonProperty("reviews")
	private List<PlaceReview> reviews = Collections.emptyList();

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

	public List<PlacePhoto> getPhotos() {
		return photos;
	}

	public String getUrl() {
		return url;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setGeometry(PlaceGeometry geometry) {
		this.geometry = geometry;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhotos(List<PlacePhoto> photos) {
		this.photos = photos;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public List<PlaceReview> getReviews() {
		return reviews;
	}
	public void setReviews(List<PlaceReview> reviews) {
		this.reviews = reviews;
	}
	
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

}

