package com.yatrix.activity.ext.domain.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetailsResponse {
	@JsonProperty("result")
	private PlaceDetails result;

	public PlaceDetails getResult() {
		return result;
	}

	public void setResult(PlaceDetails result) {
		this.result = result;
	}
}