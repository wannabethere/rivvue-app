
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class MediaType{
   	private String mediaTypeDsc;
   	private String mediaTypeId;
   	private String mediaTypeName;

 	public String getMediaTypeDsc(){
		return this.mediaTypeDsc;
	}
	public void setMediaTypeDsc(String mediaTypeDsc){
		this.mediaTypeDsc = mediaTypeDsc;
	}
 	public String getMediaTypeId(){
		return this.mediaTypeId;
	}
	public void setMediaTypeId(String mediaTypeId){
		this.mediaTypeId = mediaTypeId;
	}
 	public String getMediaTypeName(){
		return this.mediaTypeName;
	}
	public void setMediaTypeName(String mediaTypeName){
		this.mediaTypeName = mediaTypeName;
	}
}
