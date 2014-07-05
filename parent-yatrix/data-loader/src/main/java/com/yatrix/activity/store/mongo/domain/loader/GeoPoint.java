
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class GeoPoint{
   	private String lat;
   	private String lon;

 	public String getLat(){
		return this.lat;
	}
	public void setLat(String lat){
		this.lat = lat;
	}
 	public String getLon(){
		return this.lon;
	}
	public void setLon(String lon){
		this.lon = lon;
	}
}
