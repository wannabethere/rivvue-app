package com.yatrix.activity.store.mongo.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Venue extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5034297748807457242L;
	private String location;
	private String formattedAddress;
	private double[] latlng;
	private String place;
	private List<String> tags = new ArrayList<String>();
	public Venue(){
		this(null,null,null);
	}
	
	public Venue(String place,String formattedAddress, double[] latlng){
		super();
		this.place= place;
		this.formattedAddress=formattedAddress;
		this.latlng=latlng;
	}
	
	public Venue(String place,String formattedAddress, double x, double y){
		super();
		this.place= place;
		this.formattedAddress=formattedAddress;
		this.latlng= new double[] {x,y};
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getFormattedAddress() {
		return formattedAddress;
	}
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}
	
	public double[] getLatlng() {
		return latlng;
	}

	public void setLatlng(double[] latlng) {
		this.latlng = latlng;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Venue [location=" + location + ", formattedAddress="
				+ formattedAddress + ", latlng=" + Arrays.toString(latlng)
				+ ", place=" + place + ", tags=" + tags + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(latlng);
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venue other = (Venue) obj;
		if (!Arrays.equals(latlng, other.latlng))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
	
	
	
}
