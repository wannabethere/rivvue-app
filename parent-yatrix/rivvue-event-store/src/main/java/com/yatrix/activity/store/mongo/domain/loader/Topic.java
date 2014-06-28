
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class Topic{
   	private String topicId;
   	private String topicName;
   	private String topicTaxonomy;

 	public String getTopicId(){
		return this.topicId;
	}
	public void setTopicId(String topicId){
		this.topicId = topicId;
	}
 	public String getTopicName(){
		return this.topicName;
	}
	public void setTopicName(String topicName){
		this.topicName = topicName;
	}
 	public String getTopicTaxonomy(){
		return this.topicTaxonomy;
	}
	public void setTopicTaxonomy(String topicTaxonomy){
		this.topicTaxonomy = topicTaxonomy;
	}
}
