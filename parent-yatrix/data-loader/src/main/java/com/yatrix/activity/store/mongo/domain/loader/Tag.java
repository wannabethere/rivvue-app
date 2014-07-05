
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class Tag{
   	private String tagDescription;
   	private String tagId;
   	private String tagName;

 	public String getTagDescription(){
		return this.tagDescription;
	}
	public void setTagDescription(String tagDescription){
		this.tagDescription = tagDescription;
	}
 	public String getTagId(){
		return this.tagId;
	}
	public void setTagId(String tagId){
		this.tagId = tagId;
	}
 	public String getTagName(){
		return this.tagName;
	}
	public void setTagName(String tagName){
		this.tagName = tagName;
	}
}
