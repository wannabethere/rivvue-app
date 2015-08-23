
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
    "mediaTypeId",
    "mediaTypeName",
    "mediaTypeDsc"
})
public class MediaType {

    @JsonProperty("mediaTypeId")
    private String mediaTypeId;
    @JsonProperty("mediaTypeName")
    private String mediaTypeName;
    @JsonProperty("mediaTypeDsc")
    private String mediaTypeDsc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The mediaTypeId
     */
    @JsonProperty("mediaTypeId")
    public String getMediaTypeId() {
        return mediaTypeId;
    }

    /**
     * 
     * @param mediaTypeId
     *     The mediaTypeId
     */
    @JsonProperty("mediaTypeId")
    public void setMediaTypeId(String mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    /**
     * 
     * @return
     *     The mediaTypeName
     */
    @JsonProperty("mediaTypeName")
    public String getMediaTypeName() {
        return mediaTypeName;
    }

    /**
     * 
     * @param mediaTypeName
     *     The mediaTypeName
     */
    @JsonProperty("mediaTypeName")
    public void setMediaTypeName(String mediaTypeName) {
        this.mediaTypeName = mediaTypeName;
    }

    /**
     * 
     * @return
     *     The mediaTypeDsc
     */
    @JsonProperty("mediaTypeDsc")
    public String getMediaTypeDsc() {
        return mediaTypeDsc;
    }

    /**
     * 
     * @param mediaTypeDsc
     *     The mediaTypeDsc
     */
    @JsonProperty("mediaTypeDsc")
    public void setMediaTypeDsc(String mediaTypeDsc) {
        this.mediaTypeDsc = mediaTypeDsc;
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
