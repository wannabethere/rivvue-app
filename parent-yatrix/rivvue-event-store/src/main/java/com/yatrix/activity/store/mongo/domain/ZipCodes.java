package com.yatrix.activity.store.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;

@Document(collection="ZipCodes")
public class ZipCodes {
	
	Object _id;
	
	String zip; 
	
	String type;
	
	@Field(value="primary_city")
	String primaryCity;
    
	String acceptable_cities;
	
	String unacceptable_cities;
	
	String state;
	
	String county;
	
	String timezone;
	
	String area_codes;
	
	Float latitude;
	
	Float longitude;
	
	String world_region;
	
	String country;
	
	Integer decommissioned;
	
	Long estimated_population;
	
	String notes;

	public Object get_id() {
		return _id;
	}

	public void set_id(Object _id) {
		this._id = _id;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrimaryCity() {
		return primaryCity;
	}

	public void setPrimaryCity(String primaryCity) {
		this.primaryCity = primaryCity;
	}

	public String getAcceptable_cities() {
		return acceptable_cities;
	}

	public void setAcceptable_cities(String acceptable_cities) {
		this.acceptable_cities = acceptable_cities;
	}

	public String getUnacceptable_cities() {
		return unacceptable_cities;
	}

	public void setUnacceptable_cities(String unacceptable_cities) {
		this.unacceptable_cities = unacceptable_cities;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getArea_codes() {
		return area_codes;
	}

	public void setArea_codes(String area_codes) {
		this.area_codes = area_codes;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getWorld_region() {
		return world_region;
	}

	public void setWorld_region(String world_region) {
		this.world_region = world_region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getDecommissioned() {
		return decommissioned;
	}

	public void setDecommissioned(Integer decommissioned) {
		this.decommissioned = decommissioned;
	}

	public Long getEstimated_population() {
		return estimated_population;
	}

	public void setEstimated_population(Long estimated_population) {
		this.estimated_population = estimated_population;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "ZipCodes [_id=" + _id + ", zip=" + zip + ", type=" + type
				+ ", primary_city=" + primaryCity + ", acceptable_cities="
				+ acceptable_cities + ", unacceptable_cities="
				+ unacceptable_cities + ", state=" + state + ", county="
				+ county + ", timezone=" + timezone + ", area_codes="
				+ area_codes + ", latitude=" + latitude + ", longitude="
				+ longitude + ", world_region=" + world_region + ", country="
				+ country + ", decommissioned=" + decommissioned
				+ ", estimated_population=" + estimated_population + ", notes="
				+ notes + "]";
	}
	
	
}
