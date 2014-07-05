
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class Attribute{
   	private String attributeType;
   	private String attributeValue;
   	private String tagId;

 	public String getAttributeType(){
		return this.attributeType;
	}
	public void setAttributeType(String attributeType){
		this.attributeType = attributeType;
	}
 	public String getAttributeValue(){
		return this.attributeValue;
	}
	public void setAttributeValue(String attributeValue){
		this.attributeValue = attributeValue;
	}
 	public String getTagId(){
		return this.tagId;
	}
	public void setTagId(String tagId){
		this.tagId = tagId;
	}
}
