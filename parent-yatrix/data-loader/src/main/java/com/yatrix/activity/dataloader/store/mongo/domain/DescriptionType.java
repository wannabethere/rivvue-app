
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
    "descriptionTypeId",
    "descriptionTypeName"
})
public class DescriptionType {

    @JsonProperty("descriptionTypeId")
    private String descriptionTypeId;
    @JsonProperty("descriptionTypeName")
    private String descriptionTypeName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The descriptionTypeId
     */
    @JsonProperty("descriptionTypeId")
    public String getDescriptionTypeId() {
        return descriptionTypeId;
    }

    /**
     * 
     * @param descriptionTypeId
     *     The descriptionTypeId
     */
    @JsonProperty("descriptionTypeId")
    public void setDescriptionTypeId(String descriptionTypeId) {
        this.descriptionTypeId = descriptionTypeId;
    }

    /**
     * 
     * @return
     *     The descriptionTypeName
     */
    @JsonProperty("descriptionTypeName")
    public String getDescriptionTypeName() {
        return descriptionTypeName;
    }

    /**
     * 
     * @param descriptionTypeName
     *     The descriptionTypeName
     */
    @JsonProperty("descriptionTypeName")
    public void setDescriptionTypeName(String descriptionTypeName) {
        this.descriptionTypeName = descriptionTypeName;
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
