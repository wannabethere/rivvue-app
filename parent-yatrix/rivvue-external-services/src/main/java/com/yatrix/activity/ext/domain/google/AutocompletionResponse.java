package com.yatrix.activity.ext.domain.google;

import java.io.Serializable;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutocompletionResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static enum STATUSRESULT{
		OK,ZERO_RESULTS,OVER_QUERY_LIMIT,REQUEST_DENIED,INVALID_REQUEST;
	}
	
	private STATUSRESULT status;
	
	private Prediction[] predictions;

	public STATUSRESULT getStatus() {
		return status;
	}

	public void setStatus(STATUSRESULT status) {
		this.status = status;
	}

	public Prediction[] getPredictions() {
		return predictions;
	}

	public void setPredictions(Prediction[] predictions) {
		this.predictions = predictions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(predictions);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		AutocompletionResponse other = (AutocompletionResponse) obj;
		if (!Arrays.equals(predictions, other.predictions))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AutocompletionResponse [status=" + status + ", prediction="
				+ Arrays.toString(predictions) + "]";
	}

	
}
