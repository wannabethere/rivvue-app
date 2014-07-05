
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class Channel{
   	private String channelDsc;
   	private String channelId;
   	private String channelName;

 	public String getChannelDsc(){
		return this.channelDsc;
	}
	public void setChannelDsc(String channelDsc){
		this.channelDsc = channelDsc;
	}
 	public String getChannelId(){
		return this.channelId;
	}
	public void setChannelId(String channelId){
		this.channelId = channelId;
	}
 	public String getChannelName(){
		return this.channelName;
	}
	public void setChannelName(String channelName){
		this.channelName = channelName;
	}
}
