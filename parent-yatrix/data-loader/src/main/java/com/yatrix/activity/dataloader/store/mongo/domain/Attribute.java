
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
    "tagId",
    "attributeValue",
    "attributeType"
})
public class Attribute {

    @JsonProperty("tagId")
    private String tagId;
    @JsonProperty("attributeValue")
    private String attributeValue;
    @JsonProperty("attributeType")
    private String attributeType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The tagId
     */
    @JsonProperty("tagId")
    public String getTagId() {
        return tagId;
    }

    /**
     * 
     * @param tagId
     *     The tagId
     */
    @JsonProperty("tagId")
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    /**
     * 
     * @return
     *     The attributeValue
     */
    @JsonProperty("attributeValue")
    public String getAttributeValue() {
        return attributeValue;
    }

    /**
     * 
     * @param attributeValue
     *     The attributeValue
     */
    @JsonProperty("attributeValue")
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    /**
     * 
     * @return
     *     The attributeType
     */
    @JsonProperty("attributeType")
    public String getAttributeType() {
        return attributeType;
    }

    /**
     * 
     * @param attributeType
     *     The attributeType
     */
    @JsonProperty("attributeType")
    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
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
