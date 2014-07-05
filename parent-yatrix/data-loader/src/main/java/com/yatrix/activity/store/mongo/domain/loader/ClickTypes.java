
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class ClickTypes{
   	private Number nonActive;
   	private Number otherActive;
   	private Number registration;

 	public Number getNonActive(){
		return this.nonActive;
	}
	public void setNonActive(Number nonActive){
		this.nonActive = nonActive;
	}
 	public Number getOtherActive(){
		return this.otherActive;
	}
	public void setOtherActive(Number otherActive){
		this.otherActive = otherActive;
	}
 	public Number getRegistration(){
		return this.registration;
	}
	public void setRegistration(Number registration){
		this.registration = registration;
	}
}
