
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class AssetImages{
   	private String imageCaptionTxt;
   	private String imageName;
   	private String imageUrlAdr;
   	private String linkTarget;
   	private String linkUrl;

 	public String getImageCaptionTxt(){
		return this.imageCaptionTxt;
	}
	public void setImageCaptionTxt(String imageCaptionTxt){
		this.imageCaptionTxt = imageCaptionTxt;
	}
 	public String getImageName(){
		return this.imageName;
	}
	public void setImageName(String imageName){
		this.imageName = imageName;
	}
 	public String getImageUrlAdr(){
		return this.imageUrlAdr;
	}
	public void setImageUrlAdr(String imageUrlAdr){
		this.imageUrlAdr = imageUrlAdr;
	}
 	public String getLinkTarget(){
		return this.linkTarget;
	}
	public void setLinkTarget(String linkTarget){
		this.linkTarget = linkTarget;
	}
 	public String getLinkUrl(){
		return this.linkUrl;
	}
	public void setLinkUrl(String linkUrl){
		this.linkUrl = linkUrl;
	}
}
