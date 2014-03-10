package com.yatrix.activity.ext.domain.google;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceGeometry implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("location")
	private PlaceLocation location;

	public PlaceLocation getLocation() {
		return location;
	}

	public void setLocation(PlaceLocation location) {
		this.location = location;
	}
}