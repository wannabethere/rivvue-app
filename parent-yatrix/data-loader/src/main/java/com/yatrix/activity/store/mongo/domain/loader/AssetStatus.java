
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class AssetStatus{
   	private String assetStatusId;
   	private String assetStatusName;

 	public String getAssetStatusId(){
		return this.assetStatusId;
	}
	public void setAssetStatusId(String assetStatusId){
		this.assetStatusId = assetStatusId;
	}
 	public String getAssetStatusName(){
		return this.assetStatusName;
	}
	public void setAssetStatusName(String assetStatusName){
		this.assetStatusName = assetStatusName;
	}
}
