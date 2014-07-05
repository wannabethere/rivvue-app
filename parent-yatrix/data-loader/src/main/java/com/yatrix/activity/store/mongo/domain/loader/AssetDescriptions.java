
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class AssetDescriptions{
   	private String description;
   	private DescriptionType descriptionType;

 	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
 	public DescriptionType getDescriptionType(){
		return this.descriptionType;
	}
	public void setDescriptionType(DescriptionType descriptionType){
		this.descriptionType = descriptionType;
	}
}
