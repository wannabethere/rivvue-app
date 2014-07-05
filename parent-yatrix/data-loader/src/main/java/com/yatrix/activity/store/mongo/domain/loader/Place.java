
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class Place{
   	private String addressLine1Txt;
   	private String addressLine2Txt;
   	private String cityName;
   	private String countryCode;
   	private String countryName;
   	private String directionsTxt;
   	private Dma dma;
   	private GeoPoint geoPoint;
   	private String latitude;
   	private String localityName;
   	private String longitude;
   	private String placeDsc;
   	private String placeGuid;
   	private String placeName;
   	private String placeUrlAdr;
   	private String postalCode;
   	private String stateProvinceCode;
   	private String timezone;
   	private Number timezoneDST;
   	private Number timezoneOffset;

 	public String getAddressLine1Txt(){
		return this.addressLine1Txt;
	}
	public void setAddressLine1Txt(String addressLine1Txt){
		this.addressLine1Txt = addressLine1Txt;
	}
 	public String getAddressLine2Txt(){
		return this.addressLine2Txt;
	}
	public void setAddressLine2Txt(String addressLine2Txt){
		this.addressLine2Txt = addressLine2Txt;
	}
 	public String getCityName(){
		return this.cityName;
	}
	public void setCityName(String cityName){
		this.cityName = cityName;
	}
 	public String getCountryCode(){
		return this.countryCode;
	}
	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}
 	public String getCountryName(){
		return this.countryName;
	}
	public void setCountryName(String countryName){
		this.countryName = countryName;
	}
 	public String getDirectionsTxt(){
		return this.directionsTxt;
	}
	public void setDirectionsTxt(String directionsTxt){
		this.directionsTxt = directionsTxt;
	}
 	public Dma getDma(){
		return this.dma;
	}
	public void setDma(Dma dma){
		this.dma = dma;
	}
 	public GeoPoint getGeoPoint(){
		return this.geoPoint;
	}
	public void setGeoPoint(GeoPoint geoPoint){
		this.geoPoint = geoPoint;
	}
 	public String getLatitude(){
		return this.latitude;
	}
	public void setLatitude(String latitude){
		this.latitude = latitude;
	}
 	public String getLocalityName(){
		return this.localityName;
	}
	public void setLocalityName(String localityName){
		this.localityName = localityName;
	}
 	public String getLongitude(){
		return this.longitude;
	}
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
 	public String getPlaceDsc(){
		return this.placeDsc;
	}
	public void setPlaceDsc(String placeDsc){
		this.placeDsc = placeDsc;
	}
 	public String getPlaceGuid(){
		return this.placeGuid;
	}
	public void setPlaceGuid(String placeGuid){
		this.placeGuid = placeGuid;
	}
 	public String getPlaceName(){
		return this.placeName;
	}
	public void setPlaceName(String placeName){
		this.placeName = placeName;
	}
 	public String getPlaceUrlAdr(){
		return this.placeUrlAdr;
	}
	public void setPlaceUrlAdr(String placeUrlAdr){
		this.placeUrlAdr = placeUrlAdr;
	}
 	public String getPostalCode(){
		return this.postalCode;
	}
	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}
 	public String getStateProvinceCode(){
		return this.stateProvinceCode;
	}
	public void setStateProvinceCode(String stateProvinceCode){
		this.stateProvinceCode = stateProvinceCode;
	}
 	public String getTimezone(){
		return this.timezone;
	}
	public void setTimezone(String timezone){
		this.timezone = timezone;
	}
 	public Number getTimezoneDST(){
		return this.timezoneDST;
	}
	public void setTimezoneDST(Number timezoneDST){
		this.timezoneDST = timezoneDST;
	}
 	public Number getTimezoneOffset(){
		return this.timezoneOffset;
	}
	public void setTimezoneOffset(Number timezoneOffset){
		this.timezoneOffset = timezoneOffset;
	}
}
