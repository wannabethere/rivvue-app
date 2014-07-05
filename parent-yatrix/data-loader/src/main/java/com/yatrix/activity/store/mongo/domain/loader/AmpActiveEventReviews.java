/**
 * 
 */
package com.yatrix.activity.store.mongo.domain.loader;

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
	
	String ampActiveEventResponse;
	
	public AmpActiveEventReviews(String request, AmpActiveEventResponse response){
		ampActiveEventRequest = request;
	}

	public String getAmpActiveEventRequest() {
		return ampActiveEventRequest;
	}

	public void setAmpActiveEventRequest(String ampActiveEventRequest) {
		this.ampActiveEventRequest = ampActiveEventRequest;
	}

	public String getAmpActiveEventResponse() {
		return ampActiveEventResponse;
	}

	public void setAmpActiveEventResponse(
			String ampActiveEventResponse) {
		this.ampActiveEventResponse = ampActiveEventResponse;
	}
	
}
