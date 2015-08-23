
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
    "sequence",
    "topic"
})
public class AssetTopic {

    @JsonProperty("sequence")
    private String sequence;
    @JsonProperty("topic")
    private Topic topic;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The sequence
     */
    // @JsonProperty("sequence")
    public String getSequence() {
        return sequence;
    }

    /**
     * 
     * @param sequence
     *     The sequence
     */
    // @JsonProperty("sequence")
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    /**
     * 
     * @return
     *     The topic
     */
    // @JsonProperty("topic")
    public Topic getTopic() {
        return topic;
    }

    /**
     * 
     * @param topic
     *     The topic
     */
    // @JsonProperty("topic")
    public void setTopic(Topic topic) {
        this.topic = topic;
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
