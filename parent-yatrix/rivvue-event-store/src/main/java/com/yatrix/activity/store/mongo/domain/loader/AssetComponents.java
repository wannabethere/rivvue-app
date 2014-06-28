
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class AssetComponents{
   	private String activityEndDate;
   	private String activityStartDate;
   	private String assetGuid;
   	private String assetName;
   	private List<AssetSeoUrls> assetSeoUrls;
   	private String sequence;

 	public String getActivityEndDate(){
		return this.activityEndDate;
	}
	public void setActivityEndDate(String activityEndDate){
		this.activityEndDate = activityEndDate;
	}
 	public String getActivityStartDate(){
		return this.activityStartDate;
	}
	public void setActivityStartDate(String activityStartDate){
		this.activityStartDate = activityStartDate;
	}
 	public String getAssetGuid(){
		return this.assetGuid;
	}
	public void setAssetGuid(String assetGuid){
		this.assetGuid = assetGuid;
	}
 	public String getAssetName(){
		return this.assetName;
	}
	public void setAssetName(String assetName){
		this.assetName = assetName;
	}
 	public List<AssetSeoUrls> getAssetSeoUrls(){
		return this.assetSeoUrls;
	}
	public void setAssetSeoUrls(List<AssetSeoUrls> assetSeoUrls){
		this.assetSeoUrls = assetSeoUrls;
	}
 	public String getSequence(){
		return this.sequence;
	}
	public void setSequence(String sequence){
		this.sequence = sequence;
	}
}
