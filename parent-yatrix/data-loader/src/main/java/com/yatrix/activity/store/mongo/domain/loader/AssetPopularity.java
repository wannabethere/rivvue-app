
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

public class AssetPopularity{
   	private Number a3Rank;
   	private ClickTypes clickTypes;
   	private Number clicks;
   	private Number createdAt;
   	private Number rank;
   	private Number results;
   	private Number searches;
   	private Number views;

 	public Number getA3Rank(){
		return this.a3Rank;
	}
	public void setA3Rank(Number a3Rank){
		this.a3Rank = a3Rank;
	}
 	public ClickTypes getClickTypes(){
		return this.clickTypes;
	}
	public void setClickTypes(ClickTypes clickTypes){
		this.clickTypes = clickTypes;
	}
 	public Number getClicks(){
		return this.clicks;
	}
	public void setClicks(Number clicks){
		this.clicks = clicks;
	}
 	public Number getCreatedAt(){
		return this.createdAt;
	}
	public void setCreatedAt(Number createdAt){
		this.createdAt = createdAt;
	}
 	public Number getRank(){
		return this.rank;
	}
	public void setRank(Number rank){
		this.rank = rank;
	}
 	public Number getResults(){
		return this.results;
	}
	public void setResults(Number results){
		this.results = results;
	}
 	public Number getSearches(){
		return this.searches;
	}
	public void setSearches(Number searches){
		this.searches = searches;
	}
 	public Number getViews(){
		return this.views;
	}
	public void setViews(Number views){
		this.views = views;
	}
}
