package com.yatrix.activity.ext.domain.google;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceLocation implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("lat")
	private String lat;

	@JsonProperty("lng")
	private String lng;

	public String getLat() {
		return lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaceLocation other = (PlaceLocation) obj;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		return true;
	}
	
	
}