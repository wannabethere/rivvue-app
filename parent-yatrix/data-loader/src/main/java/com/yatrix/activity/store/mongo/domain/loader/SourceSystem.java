
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class SourceSystem{
   	private String affiliate;
   	private String legacyGuid;
   	private String sourceSystemName;

 	public String getAffiliate(){
		return this.affiliate;
	}
	public void setAffiliate(String affiliate){
		this.affiliate = affiliate;
	}
 	public String getLegacyGuid(){
		return this.legacyGuid;
	}
	public void setLegacyGuid(String legacyGuid){
		this.legacyGuid = legacyGuid;
	}
 	public String getSourceSystemName(){
		return this.sourceSystemName;
	}
	public void setSourceSystemName(String sourceSystemName){
		this.sourceSystemName = sourceSystemName;
	}
}
