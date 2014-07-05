
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class AssetSeoUrls{
   	private String seoSystemName;
   	private String statusCode;
   	private String urlAdr;

 	public String getSeoSystemName(){
		return this.seoSystemName;
	}
	public void setSeoSystemName(String seoSystemName){
		this.seoSystemName = seoSystemName;
	}
 	public String getStatusCode(){
		return this.statusCode;
	}
	public void setStatusCode(String statusCode){
		this.statusCode = statusCode;
	}
 	public String getUrlAdr(){
		return this.urlAdr;
	}
	public void setUrlAdr(String urlAdr){
		this.urlAdr = urlAdr;
	}
}
