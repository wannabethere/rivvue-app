
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class AssetPrices{
   	private String effectiveUntilDate;
   	private String maxPriceAmt;
   	private String minPriceAmt;
   	private String priceAmt;

 	public String getEffectiveUntilDate(){
		return this.effectiveUntilDate;
	}
	public void setEffectiveUntilDate(String effectiveUntilDate){
		this.effectiveUntilDate = effectiveUntilDate;
	}
 	public String getMaxPriceAmt(){
		return this.maxPriceAmt;
	}
	public void setMaxPriceAmt(String maxPriceAmt){
		this.maxPriceAmt = maxPriceAmt;
	}
 	public String getMinPriceAmt(){
		return this.minPriceAmt;
	}
	public void setMinPriceAmt(String minPriceAmt){
		this.minPriceAmt = minPriceAmt;
	}
 	public String getPriceAmt(){
		return this.priceAmt;
	}
	public void setPriceAmt(String priceAmt){
		this.priceAmt = priceAmt;
	}
}
