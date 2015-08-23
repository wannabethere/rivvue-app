
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
    "effectiveUntilDate",
    "priceAmt",
    "maxPriceAmt",
    "minPriceAmt"
})
public class AssetPrice {

    @JsonProperty("effectiveUntilDate")
    private Object effectiveUntilDate;
    @JsonProperty("priceAmt")
    private String priceAmt;
    @JsonProperty("maxPriceAmt")
    private Object maxPriceAmt;
    @JsonProperty("minPriceAmt")
    private Object minPriceAmt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The effectiveUntilDate
     */
    // @JsonProperty("effectiveUntilDate")
    public Object getEffectiveUntilDate() {
        return effectiveUntilDate;
    }

    /**
     * 
     * @param effectiveUntilDate
     *     The effectiveUntilDate
     */
    // @JsonProperty("effectiveUntilDate")
    public void setEffectiveUntilDate(Object effectiveUntilDate) {
        this.effectiveUntilDate = effectiveUntilDate;
    }

    /**
     * 
     * @return
     *     The priceAmt
     */
    // @JsonProperty("priceAmt")
    public String getPriceAmt() {
        return priceAmt;
    }

    /**
     * 
     * @param priceAmt
     *     The priceAmt
     */
    // @JsonProperty("priceAmt")
    public void setPriceAmt(String priceAmt) {
        this.priceAmt = priceAmt;
    }

    /**
     * 
     * @return
     *     The maxPriceAmt
     */
    // @JsonProperty("maxPriceAmt")
    public Object getMaxPriceAmt() {
        return maxPriceAmt;
    }

    /**
     * 
     * @param maxPriceAmt
     *     The maxPriceAmt
     */
    // @JsonProperty("maxPriceAmt")
    public void setMaxPriceAmt(Object maxPriceAmt) {
        this.maxPriceAmt = maxPriceAmt;
    }

    /**
     * 
     * @return
     *     The minPriceAmt
     */
    // @JsonProperty("minPriceAmt")
    public Object getMinPriceAmt() {
        return minPriceAmt;
    }

    /**
     * 
     * @param minPriceAmt
     *     The minPriceAmt
     */
    // @JsonProperty("minPriceAmt")
    public void setMinPriceAmt(Object minPriceAmt) {
        this.minPriceAmt = minPriceAmt;
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
