
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
    "lat",
    "lon"
})
public class GeoPoint {

    @JsonProperty("lat")
    private String lat;
    @JsonProperty("lon")
    private String lon;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The lat
     */
   // @JsonProperty("lat")
    public String getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    //@JsonProperty("lat")
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return
     *     The lon
     */
   // @JsonProperty("lon")
    public String getLon() {
        return lon;
    }

    /**
     * 
     * @param lon
     *     The lon
     */
   // @JsonProperty("lon")
    public void setLon(String lon) {
        this.lon = lon;
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
