
package com.yatrix.activity.dataloader.store.mongo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "retries",
    "total_results",
    "items_per_page",
    "start_index",
    "facets",
    "facet_values",
    "suggestions",
    "results",
    "radius",
    "start_date",
    "sort"
})
public class AmpActiveEventResponse {

    @JsonProperty("retries")
    private Integer retries;
    @JsonProperty("total_results")
    private Integer total_results;
    @JsonProperty("items_per_page")
    private Integer items_per_page;
    @JsonProperty("start_index")
    private Integer start_index;
    @JsonProperty("facets")
    private List<Object> facets = new ArrayList<Object>();
    @JsonProperty("facet_values")
    private List<Object> facet_values = new ArrayList<Object>();
    @JsonProperty("suggestions")
    private List<Object> suggestions = new ArrayList<Object>();
    @JsonProperty("results")
    private List<AmpActiveEvent> results = new ArrayList<AmpActiveEvent>();
    @JsonProperty("radius")
    private Integer radius;
    @JsonProperty("start_date")
    private String start_date;
    @JsonProperty("sort")
    private Object sort;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The retries
     */
    @JsonProperty("retries")
    public Integer getRetries() {
        return retries;
    }

    /**
     * 
     * @param retries
     *     The retries
     */
    @JsonProperty("retries")
    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    /**
     * 
     * @return
     *     The total_results
     */
    @JsonProperty("total_results")
    public Integer getTotal_results() {
        return total_results;
    }

    /**
     * 
     * @param total_results
     *     The total_results
     */
    @JsonProperty("total_results")
    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }

    /**
     * 
     * @return
     *     The items_per_page
     */
    @JsonProperty("items_per_page")
    public Integer getItems_per_page() {
        return items_per_page;
    }

    /**
     * 
     * @param items_per_page
     *     The items_per_page
     */
    @JsonProperty("items_per_page")
    public void setItems_per_page(Integer items_per_page) {
        this.items_per_page = items_per_page;
    }

    /**
     * 
     * @return
     *     The start_index
     */
    @JsonProperty("start_index")
    public Integer getStart_index() {
        return start_index;
    }

    /**
     * 
     * @param start_index
     *     The start_index
     */
    @JsonProperty("start_index")
    public void setStart_index(Integer start_index) {
        this.start_index = start_index;
    }

    /**
     * 
     * @return
     *     The facets
     */
    @JsonProperty("facets")
    public List<Object> getFacets() {
        return facets;
    }

    /**
     * 
     * @param facets
     *     The facets
     */
    @JsonProperty("facets")
    public void setFacets(List<Object> facets) {
        this.facets = facets;
    }

    /**
     * 
     * @return
     *     The facet_values
     */
    @JsonProperty("facet_values")
    public List<Object> getFacet_values() {
        return facet_values;
    }

    /**
     * 
     * @param facet_values
     *     The facet_values
     */
    @JsonProperty("facet_values")
    public void setFacet_values(List<Object> facet_values) {
        this.facet_values = facet_values;
    }

    /**
     * 
     * @return
     *     The suggestions
     */
    @JsonProperty("suggestions")
    public List<Object> getSuggestions() {
        return suggestions;
    }

    /**
     * 
     * @param suggestions
     *     The suggestions
     */
    @JsonProperty("suggestions")
    public void setSuggestions(List<Object> suggestions) {
        this.suggestions = suggestions;
    }

    /**
     * 
     * @return
     *     The results
     */
    @JsonProperty("results")
    public List<AmpActiveEvent> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    @JsonProperty("results")
    public void setResults(List<AmpActiveEvent> results) {
        this.results = results;
    }

    /**
     * 
     * @return
     *     The radius
     */
    @JsonProperty("radius")
    public Integer getRadius() {
        return radius;
    }

    /**
     * 
     * @param radius
     *     The radius
     */
    @JsonProperty("radius")
    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    /**
     * 
     * @return
     *     The start_date
     */
    @JsonProperty("start_date")
    public String getStart_date() {
        return start_date;
    }

    /**
     * 
     * @param start_date
     *     The start_date
     */
    @JsonProperty("start_date")
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * 
     * @return
     *     The sort
     */
    @JsonProperty("sort")
    public Object getSort() {
        return sort;
    }

    /**
     * 
     * @param sort
     *     The sort
     */
    @JsonProperty("sort")
    public void setSort(Object sort) {
        this.sort = sort;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return "AmpActiveEventResponse [retries=" + retries
				+ ", total_results=" + total_results + ", items_per_page="
				+ items_per_page + ", start_index=" + start_index + ", facets="
				+ facets + ", facet_values=" + facet_values + ", suggestions="
				+ suggestions + ", results=" + results + ", radius=" + radius
				+ ", start_date=" + start_date + ", sort=" + sort
				+ ", additionalProperties=" + additionalProperties + "]";
	}
    
    

}
