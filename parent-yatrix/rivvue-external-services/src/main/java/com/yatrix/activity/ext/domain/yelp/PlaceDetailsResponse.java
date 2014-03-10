package com.yatrix.activity.ext.domain.yelp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Collections;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlaceDetailsResponse {
	@JsonProperty("businesses")
	private List<PlaceDetails> result=Collections.emptyList();

	public List<PlaceDetails> getResult() {
		return result;
	}

	public void setResult(List<PlaceDetails> result) {
		this.result = result;
	}
}