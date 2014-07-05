
package com.yatrix.activity.store.mongo.domain.loader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AssetAttributes{
   	private Attribute attribute;

 	public Attribute getAttribute(){
		return this.attribute;
	}
	public void setAttribute(Attribute attribute){
		this.attribute = attribute;
	}
}
