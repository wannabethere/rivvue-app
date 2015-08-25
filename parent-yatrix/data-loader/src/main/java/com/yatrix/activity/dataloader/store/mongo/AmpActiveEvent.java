package com.yatrix.activity.dataloader.store.mongo;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yatrix.activity.dataloader.store.mongo.domain.Item;
import com.yatrix.activity.dataloader.store.mongo.domain.Market;
import com.yatrix.activity.dataloader.store.mongo.domain.Organization;
import com.yatrix.activity.dataloader.store.mongo.domain.Place;

@Document(collection="AmpActiveEvent")
public class AmpActiveEvent extends Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("assetGuid")
    private String assetGuid;
	
    @JsonProperty("assetName")
    private String assetName;
    
    @JsonProperty("assetDsc")
    private String assetDsc;
    
    @JsonProperty("market")
    private Market market;
    
//    @JsonProperty("organization")
//    private Organization organization;
    
//    @JsonProperty("place")
//    private Place place;

	public String getAssetGuid() {
		return assetGuid;
	}

	public void setAssetGuid(String assetGuid) {
		this.assetGuid = assetGuid;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetDsc() {
		return assetDsc;
	}

	public void setAssetDsc(String assetDsc) {
		this.assetDsc = assetDsc;
	}
	
	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}
	
//	public Organization getOrganization() {
//		return organization;
//	}
//
//	public void setOrganization(Organization organization) {
//		this.organization = organization;
//	}

//	public Place getPlace() {
//		return place;
//	}
//
//	public void setPlace(Place place) {
//		this.place = place;
//	}

	@Override
	public String toString() {
		return "AmpActiveEvent [assetGuid=" + assetGuid + ", assetName="
				+ assetName + ", assetDsc=" + assetDsc + ", market=" + market
				+ "]";
	}

}
