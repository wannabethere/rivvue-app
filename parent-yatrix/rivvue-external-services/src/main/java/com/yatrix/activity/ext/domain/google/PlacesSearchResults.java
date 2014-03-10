package com.yatrix.activity.ext.domain.google;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlacesSearchResults implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("debug_info")
	private List<String> debugInfo;
	
	@JsonProperty("results")
	private SearchResult[] results;
	
	
	public static enum STATUSRESULT{
		OK,ZERO_RESULTS,OVER_QUERY_LIMIT,REQUEST_DENIED,INVALID_REQUEST;
	}
	
	@JsonProperty("status")
	private STATUSRESULT status;
	
	
	public SearchResult[] getResults() {
		return results;
	}
	
	public void setResults(SearchResult[] results) {
		this.results = results;
	}
	
	public STATUSRESULT getStatus() {
		return status;
	}

	public void setStatus(STATUSRESULT status) {
		this.status = status;
	}
	
	
	@JsonIgnore
	public List<String> getDebugInfo() {
		return debugInfo;
	}

	public void setDebugInfo(List<String> debugInfo) {
		this.debugInfo = debugInfo;
	}

	@Override
	public String toString() {
		return "PlacesSearchResults [status=" + status.toString()+ ""
				+ ", results="
				+ Arrays.toString(results) + "]";
	}
	
	
	
}
