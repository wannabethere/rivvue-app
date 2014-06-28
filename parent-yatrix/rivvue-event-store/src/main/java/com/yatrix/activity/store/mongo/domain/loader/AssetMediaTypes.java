
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class AssetMediaTypes{
   	private MediaType mediaType;
   	private String sequence;

 	public MediaType getMediaType(){
		return this.mediaType;
	}
	public void setMediaType(MediaType mediaType){
		this.mediaType = mediaType;
	}
 	public String getSequence(){
		return this.sequence;
	}
	public void setSequence(String sequence){
		this.sequence = sequence;
	}
}
