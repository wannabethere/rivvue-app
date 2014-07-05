
package com.yatrix.activity.store.mongo.domain.loader;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AssetChannels{
   	private Channel channel;
   	private String sequence;

 	public Channel getChannel(){
		return this.channel;
	}
	public void setChannel(Channel channel){
		this.channel = channel;
	}
 	public String getSequence(){
		return this.sequence;
	}
	public void setSequence(String sequence){
		this.sequence = sequence;
	}
}
