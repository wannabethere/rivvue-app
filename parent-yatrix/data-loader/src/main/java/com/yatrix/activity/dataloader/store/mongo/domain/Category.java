
package com.yatrix.activity.dataloader.store.mongo.domain;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "categoryId",
    "categoryTaxonomy",
    "categoryName"
})
public class Category {

    @JsonProperty("categoryId")
    private String categoryId;
    @JsonProperty("categoryTaxonomy")
    private String categoryTaxonomy;
    @JsonProperty("categoryName")
    private String categoryName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The categoryId
     */
    @JsonProperty("categoryId")
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 
     * @param categoryId
     *     The categoryId
     */
    @JsonProperty("categoryId")
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 
     * @return
     *     The categoryTaxonomy
     */
    @JsonProperty("categoryTaxonomy")
    public String getCategoryTaxonomy() {
        return categoryTaxonomy;
    }

    /**
     * 
     * @param categoryTaxonomy
     *     The categoryTaxonomy
     */
    @JsonProperty("categoryTaxonomy")
    public void setCategoryTaxonomy(String categoryTaxonomy) {
        this.categoryTaxonomy = categoryTaxonomy;
    }

    /**
     * 
     * @return
     *     The categoryName
     */
    @JsonProperty("categoryName")
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 
     * @param categoryName
     *     The categoryName
     */
    @JsonProperty("categoryName")
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
