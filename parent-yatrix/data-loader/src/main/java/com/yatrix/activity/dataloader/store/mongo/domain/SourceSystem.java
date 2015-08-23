
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
    "sourceSystemName",
    "legacyGuid",
    "affiliate"
})
public class SourceSystem {

    @JsonProperty("sourceSystemName")
    private String sourceSystemName;
    @JsonProperty("legacyGuid")
    private String legacyGuid;
    @JsonProperty("affiliate")
    private String affiliate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The sourceSystemName
     */
    //@JsonProperty("sourceSystemName")
    public String getSourceSystemName() {
        return sourceSystemName;
    }

    /**
     * 
     * @param sourceSystemName
     *     The sourceSystemName
     */
    //@JsonProperty("sourceSystemName")
    public void setSourceSystemName(String sourceSystemName) {
        this.sourceSystemName = sourceSystemName;
    }

    /**
     * 
     * @return
     *     The legacyGuid
     */
   // @JsonProperty("legacyGuid")
    public String getLegacyGuid() {
        return legacyGuid;
    }

    /**
     * 
     * @param legacyGuid
     *     The legacyGuid
     */
   // @JsonProperty("legacyGuid")
    public void setLegacyGuid(String legacyGuid) {
        this.legacyGuid = legacyGuid;
    }

    /**
     * 
     * @return
     *     The affiliate
     */
   // @JsonProperty("affiliate")
    public String getAffiliate() {
        return affiliate;
    }

    /**
     * 
     * @param affiliate
     *     The affiliate
     */
    //@JsonProperty("affiliate")
    public void setAffiliate(String affiliate) {
        this.affiliate = affiliate;
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
