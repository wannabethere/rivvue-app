
package com.yatrix.activity.dataloader.store.mongo.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "activityStartDate",
    "startTime",
    "activityEndDate",
    "endTime",
    "frequencyInterval",
    "frequency",
    "days",
    "activityExclusions"
})
public class ActivityRecurrence {

    @JsonProperty("activityStartDate")
    private String activityStartDate;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("activityEndDate")
    private String activityEndDate;
    @JsonProperty("endTime")
    private String endTime;
    @JsonProperty("frequencyInterval")
    private String frequencyInterval;
    @JsonProperty("frequency")
    private Frequency frequency;
    @JsonProperty("days")
    private String days;
    @JsonProperty("activityExclusions")
    private List<Object> activityExclusions = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The activityStartDate
     */
    // @JsonProperty("activityStartDate")
    public String getActivityStartDate() {
        return activityStartDate;
    }

    /**
     * 
     * @param activityStartDate
     *     The activityStartDate
     */
    // @JsonProperty("activityStartDate")
    public void setActivityStartDate(String activityStartDate) {
        this.activityStartDate = activityStartDate;
    }

    /**
     * 
     * @return
     *     The startTime
     */
    // @JsonProperty("startTime")
    public String getStartTime() {
        return startTime;
    }

    /**
     * 
     * @param startTime
     *     The startTime
     */
    // @JsonProperty("startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 
     * @return
     *     The activityEndDate
     */
    // @JsonProperty("activityEndDate")
    public String getActivityEndDate() {
        return activityEndDate;
    }

    /**
     * 
     * @param activityEndDate
     *     The activityEndDate
     */
    // @JsonProperty("activityEndDate")
    public void setActivityEndDate(String activityEndDate) {
        this.activityEndDate = activityEndDate;
    }

    /**
     * 
     * @return
     *     The endTime
     */
    // @JsonProperty("endTime")
    public String getEndTime() {
        return endTime;
    }

    /**
     * 
     * @param endTime
     *     The endTime
     */
    // @JsonProperty("endTime")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 
     * @return
     *     The frequencyInterval
     */
    // @JsonProperty("frequencyInterval")
    public String getFrequencyInterval() {
        return frequencyInterval;
    }

    /**
     * 
     * @param frequencyInterval
     *     The frequencyInterval
     */
    // @JsonProperty("frequencyInterval")
    public void setFrequencyInterval(String frequencyInterval) {
        this.frequencyInterval = frequencyInterval;
    }

    /**
     * 
     * @return
     *     The frequency
     */
    // @JsonProperty("frequency")
    public Frequency getFrequency() {
        return frequency;
    }

    /**
     * 
     * @param frequency
     *     The frequency
     */
    // @JsonProperty("frequency")
    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    /**
     * 
     * @return
     *     The days
     */
    // @JsonProperty("days")
    public String getDays() {
        return days;
    }

    /**
     * 
     * @param days
     *     The days
     */
    // @JsonProperty("days")
    public void setDays(String days) {
        this.days = days;
    }

    /**
     * 
     * @return
     *     The activityExclusions
     */
    // @JsonProperty("activityExclusions")
    public List<Object> getActivityExclusions() {
        return activityExclusions;
    }

    /**
     * 
     * @param activityExclusions
     *     The activityExclusions
     */
    // @JsonProperty("activityExclusions")
    public void setActivityExclusions(List<Object> activityExclusions) {
        this.activityExclusions = activityExclusions;
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
