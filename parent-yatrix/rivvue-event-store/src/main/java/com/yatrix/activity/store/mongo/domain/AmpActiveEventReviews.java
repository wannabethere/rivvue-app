/**
 * 
 */
package com.yatrix.activity.store.mongo.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Kishore Manthangod
 *
 */

@Document(collection="AmpActiveEventReviews")
public class AmpActiveEventReviews implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String ampActiveEventRequest;
	
	AmpActiveEventResponse ampActiveEventResponse;
	
	public AmpActiveEventReviews(String request, AmpActiveEventResponse response){
		ampActiveEventRequest = request;
	}

	public String getAmpActiveEventRequest() {
		return ampActiveEventRequest;
	}

	public void setAmpActiveEventRequest(String ampActiveEventRequest) {
		this.ampActiveEventRequest = ampActiveEventRequest;
	}

	public AmpActiveEventResponse getAmpActiveEventResponse() {
		return ampActiveEventResponse;
	}

	public void setAmpActiveEventResponse(
			AmpActiveEventResponse ampActiveEventResponse) {
		this.ampActiveEventResponse = ampActiveEventResponse;
	}
	
}
