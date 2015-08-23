
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
    "assetStatusId",
    "assetStatusName"
})
public class AssetStatus {

    @JsonProperty("assetStatusId")
    private String assetStatusId;
    @JsonProperty("assetStatusName")
    private String assetStatusName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The assetStatusId
     */
   // @JsonProperty("assetStatusId")
    public String getAssetStatusId() {
        return assetStatusId;
    }

    /**
     * 
     * @param assetStatusId
     *     The assetStatusId
     */
    //@JsonProperty("assetStatusId")
    public void setAssetStatusId(String assetStatusId) {
        this.assetStatusId = assetStatusId;
    }

    /**
     * 
     * @return
     *     The assetStatusName
     */
    //@JsonProperty("assetStatusName")
    public String getAssetStatusName() {
        return assetStatusName;
    }

    /**
     * 
     * @param assetStatusName
     *     The assetStatusName
     */
   // @JsonProperty("assetStatusName")
    public void setAssetStatusName(String assetStatusName) {
        this.assetStatusName = assetStatusName;
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
