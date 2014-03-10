package com.yatrix.activity.ext.domain.google;

import java.io.Serializable;
import java.util.Arrays;


/**
 * This is the class that gets populated for making Places API requests.
 * Two calls will happen in this code.
 * One Call to get the location of the device/APP/etc.
 * Second call to do a text search in a location with radius.
 * the below class supports the second call. 
 * @author tkmald2
 * Service to support near place search
 */

public class PlacesRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private final String query;
	private String[] types;
	private PlaceLocation location;
	
	public PlacesRequest(String query){
		this.query=query;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public PlaceLocation getLocation() {
		return location;
	}

	public void setLocation(PlaceLocation location) {
		this.location = location;
	}

	public String getQuery() {
		return query;
	}

	@Override
	public String toString() {
		return "PlacesRequest [query=" + query + ", types="
				+ Arrays.toString(types) + ", location=" + location + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result + Arrays.hashCode(types);
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
		PlacesRequest other = (PlacesRequest) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (!Arrays.equals(types, other.types))
			return false;
		return true;
	}
	
	
}
