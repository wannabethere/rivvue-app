
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class Market{
   	private String marketId;
   	private String marketName;

 	public String getMarketId(){
		return this.marketId;
	}
	public void setMarketId(String marketId){
		this.marketId = marketId;
	}
 	public String getMarketName(){
		return this.marketName;
	}
	public void setMarketName(String marketName){
		this.marketName = marketName;
	}
}
