
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
    "tagName",
    "tagDescription"
})
public class Tag {

    @JsonProperty("tagId")
    private String tagId;
    @JsonProperty("tagName")
    private String tagName;
    @JsonProperty("tagDescription")
    private String tagDescription;
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
     *     The tagName
     */
    @JsonProperty("tagName")
    public String getTagName() {
        return tagName;
    }

    /**
     * 
     * @param tagName
     *     The tagName
     */
    @JsonProperty("tagName")
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 
     * @return
     *     The tagDescription
     */
    @JsonProperty("tagDescription")
    public String getTagDescription() {
        return tagDescription;
    }

    /**
     * 
     * @param tagDescription
     *     The tagDescription
     */
    @JsonProperty("tagDescription")
    public void setTagDescription(String tagDescription) {
        this.tagDescription = tagDescription;
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
