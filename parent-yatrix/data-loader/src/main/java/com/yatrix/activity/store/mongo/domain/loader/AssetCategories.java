
package com.yatrix.activity.store.mongo.domain.loader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AssetCategories{
   	private Category category;
   	private String sequence;

 	public Category getCategory(){
		return this.category;
	}
	public void setCategory(Category category){
		this.category = category;
	}
 	public String getSequence(){
		return this.sequence;
	}
	public void setSequence(String sequence){
		this.sequence = sequence;
	}
}
