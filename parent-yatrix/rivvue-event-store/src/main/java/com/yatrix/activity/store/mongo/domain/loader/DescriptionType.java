
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class DescriptionType{
   	private String descriptionTypeId;
   	private String descriptionTypeName;

 	public String getDescriptionTypeId(){
		return this.descriptionTypeId;
	}
	public void setDescriptionTypeId(String descriptionTypeId){
		this.descriptionTypeId = descriptionTypeId;
	}
 	public String getDescriptionTypeName(){
		return this.descriptionTypeName;
	}
	public void setDescriptionTypeName(String descriptionTypeName){
		this.descriptionTypeName = descriptionTypeName;
	}
}
