
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
    "topicId",
    "topicTaxonomy",
    "topicName"
})
public class Topic {

    @JsonProperty("topicId")
    private String topicId;
    @JsonProperty("topicTaxonomy")
    private String topicTaxonomy;
    @JsonProperty("topicName")
    private String topicName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The topicId
     */
    @JsonProperty("topicId")
    public String getTopicId() {
        return topicId;
    }

    /**
     * 
     * @param topicId
     *     The topicId
     */
    @JsonProperty("topicId")
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    /**
     * 
     * @return
     *     The topicTaxonomy
     */
    @JsonProperty("topicTaxonomy")
    public String getTopicTaxonomy() {
        return topicTaxonomy;
    }

    /**
     * 
     * @param topicTaxonomy
     *     The topicTaxonomy
     */
    @JsonProperty("topicTaxonomy")
    public void setTopicTaxonomy(String topicTaxonomy) {
        this.topicTaxonomy = topicTaxonomy;
    }

    /**
     * 
     * @return
     *     The topicName
     */
    @JsonProperty("topicName")
    public String getTopicName() {
        return topicName;
    }

    /**
     * 
     * @param topicName
     *     The topicName
     */
    @JsonProperty("topicName")
    public void setTopicName(String topicName) {
        this.topicName = topicName;
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
