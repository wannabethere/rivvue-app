
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class Category{
   	private String categoryId;
   	private String categoryName;
   	private String categoryTaxonomy;

 	public String getCategoryId(){
		return this.categoryId;
	}
	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}
 	public String getCategoryName(){
		return this.categoryName;
	}
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
 	public String getCategoryTaxonomy(){
		return this.categoryTaxonomy;
	}
	public void setCategoryTaxonomy(String categoryTaxonomy){
		this.categoryTaxonomy = categoryTaxonomy;
	}
}
