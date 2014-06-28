
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class AssetTopics{
   	private String sequence;
   	private Topic topic;

 	public String getSequence(){
		return this.sequence;
	}
	public void setSequence(String sequence){
		this.sequence = sequence;
	}
 	public Topic getTopic(){
		return this.topic;
	}
	public void setTopic(Topic topic){
		this.topic = topic;
	}
}
