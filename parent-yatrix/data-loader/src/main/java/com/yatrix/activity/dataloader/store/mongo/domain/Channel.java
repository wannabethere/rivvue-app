
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
    "channelId",
    "channelName",
    "channelDsc"
})
public class Channel {

    @JsonProperty("channelId")
    private String channelId;
    @JsonProperty("channelName")
    private String channelName;
    @JsonProperty("channelDsc")
    private String channelDsc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The channelId
     */
    @JsonProperty("channelId")
    public String getChannelId() {
        return channelId;
    }

    /**
     * 
     * @param channelId
     *     The channelId
     */
    @JsonProperty("channelId")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    /**
     * 
     * @return
     *     The channelName
     */
    @JsonProperty("channelName")
    public String getChannelName() {
        return channelName;
    }

    /**
     * 
     * @param channelName
     *     The channelName
     */
    @JsonProperty("channelName")
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 
     * @return
     *     The channelDsc
     */
    @JsonProperty("channelDsc")
    public String getChannelDsc() {
        return channelDsc;
    }

    /**
     * 
     * @param channelDsc
     *     The channelDsc
     */
    @JsonProperty("channelDsc")
    public void setChannelDsc(String channelDsc) {
        this.channelDsc = channelDsc;
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
