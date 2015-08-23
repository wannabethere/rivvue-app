
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
    "dmaId",
    "dmaName"
})
public class Dma {

    @JsonProperty("dmaId")
    private String dmaId;
    @JsonProperty("dmaName")
    private String dmaName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The dmaId
     */
   // @JsonProperty("dmaId")
    public String getDmaId() {
        return dmaId;
    }

    /**
     * 
     * @param dmaId
     *     The dmaId
     */
   // @JsonProperty("dmaId")
    public void setDmaId(String dmaId) {
        this.dmaId = dmaId;
    }

    /**
     * 
     * @return
     *     The dmaName
     */
   // @JsonProperty("dmaName")
    public String getDmaName() {
        return dmaName;
    }

    /**
     * 
     * @param dmaName
     *     The dmaName
     */
    //@JsonProperty("dmaName")
    public void setDmaName(String dmaName) {
        this.dmaName = dmaName;
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
