
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AmpActiveEventResponse{
  // 	private List<Facet_values> facet_values;
 //  	private List<Facets> facets;
   	private Number items_per_page;
   	private Number radius;
   	private List<Results> results;
   	private Number retries;
   	private String start_date;
   	private Number start_index;
   //	private List<Suggestions> suggestions;
   	private Number total_results;

// 	public List<Facet_values> getFacet_values(){
//		return this.facet_values;
//	}
//	public void setFacet_values(List<Facet_values> facet_values){
//		this.facet_values = facet_values;
//	}
// 	public List<Facets> getFacets(){
//		return this.facets;
//	}
//	public void setFacets(List<Facets> facets){
//		this.facets = facets;
//	}
 	public Number getItems_per_page(){
		return this.items_per_page;
	}
	public void setItems_per_page(Number items_per_page){
		this.items_per_page = items_per_page;
	}
 	public Number getRadius(){
		return this.radius;
	}
	public void setRadius(Number radius){
		this.radius = radius;
	}
 	public List<Results> getResults(){
		return this.results;
	}
	public void setResults(List<Results> results){
		this.results = results;
	}
 	public Number getRetries(){
		return this.retries;
	}
	public void setRetries(Number retries){
		this.retries = retries;
	}
 	public String getStart_date(){
		return this.start_date;
	}
	public void setStart_date(String start_date){
		this.start_date = start_date;
	}
 	public Number getStart_index(){
		return this.start_index;
	}
	public void setStart_index(Number start_index){
		this.start_index = start_index;
	}
// 	public List<Suggestions> getSuggestions(){
//		return this.suggestions;
//	}
//	public void setSuggestions(List<Suggestions> suggestions){
//		this.suggestions = suggestions;
//	}
 	public Number getTotal_results(){
		return this.total_results;
	}
	public void setTotal_results(Number total_results){
		this.total_results = total_results;
	}
}
