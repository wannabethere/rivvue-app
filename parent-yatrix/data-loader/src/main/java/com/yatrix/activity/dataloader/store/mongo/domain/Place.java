
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
    "placeGuid",
    "placeName",
    "placeDsc",
    "placeUrlAdr",
    "addressLine1Txt",
    "addressLine2Txt",
    "cityName",
    "stateProvinceCode",
    "localityName",
    "postalCode",
    "countryName",
    "countryCode",
    "timezone",
    "timezoneOffset",
    "timezoneDST",
    "latitude",
    "longitude",
    "directionsTxt",
    "geoPoint",
    "dma"
})
public class Place {

    @JsonProperty("placeGuid")
    private String placeGuid;
    @JsonProperty("placeName")
    private String placeName;
    @JsonProperty("placeDsc")
    private String placeDsc;
    @JsonProperty("placeUrlAdr")
    private String placeUrlAdr;
    @JsonProperty("addressLine1Txt")
    private String addressLine1Txt;
    @JsonProperty("addressLine2Txt")
    private String addressLine2Txt;
    @JsonProperty("cityName")
    private String cityName;
    @JsonProperty("stateProvinceCode")
    private String stateProvinceCode;
    @JsonProperty("localityName")
    private String localityName;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("timezoneOffset")
    private Integer timezoneOffset;
    @JsonProperty("timezoneDST")
    private Integer timezoneDST;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;
    @JsonProperty("directionsTxt")
    private String directionsTxt;
    @JsonProperty("geoPoint")
    private GeoPoint geoPoint;
    @JsonProperty("dma")
    private Dma dma;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The placeGuid
     */
    //@JsonProperty("placeGuid")
    public String getPlaceGuid() {
        return placeGuid;
    }

    /**
     * 
     * @param placeGuid
     *     The placeGuid
     */
    // @JsonProperty("placeGuid")
    public void setPlaceGuid(String placeGuid) {
        this.placeGuid = placeGuid;
    }

    /**
     * 
     * @return
     *     The placeName
     */
    // @JsonProperty("placeName")
    public String getPlaceName() {
        return placeName;
    }

    /**
     * 
     * @param placeName
     *     The placeName
     */
    // @JsonProperty("placeName")
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    /**
     * 
     * @return
     *     The placeDsc
     */
    // @JsonProperty("placeDsc")
    public String getPlaceDsc() {
        return placeDsc;
    }

    /**
     * 
     * @param placeDsc
     *     The placeDsc
     */
    // @JsonProperty("placeDsc")
    public void setPlaceDsc(String placeDsc) {
        this.placeDsc = placeDsc;
    }

    /**
     * 
     * @return
     *     The placeUrlAdr
     */
    // @JsonProperty("placeUrlAdr")
    public String getPlaceUrlAdr() {
        return placeUrlAdr;
    }

    /**
     * 
     * @param placeUrlAdr
     *     The placeUrlAdr
     */
    // @JsonProperty("placeUrlAdr")
    public void setPlaceUrlAdr(String placeUrlAdr) {
        this.placeUrlAdr = placeUrlAdr;
    }

    /**
     * 
     * @return
     *     The addressLine1Txt
     */
    // @JsonProperty("addressLine1Txt")
    public String getAddressLine1Txt() {
        return addressLine1Txt;
    }

    /**
     * 
     * @param addressLine1Txt
     *     The addressLine1Txt
     */
    // @JsonProperty("addressLine1Txt")
    public void setAddressLine1Txt(String addressLine1Txt) {
        this.addressLine1Txt = addressLine1Txt;
    }

    /**
     * 
     * @return
     *     The addressLine2Txt
     */
    // @JsonProperty("addressLine2Txt")
    public String getAddressLine2Txt() {
        return addressLine2Txt;
    }

    /**
     * 
     * @param addressLine2Txt
     *     The addressLine2Txt
     */
    // @JsonProperty("addressLine2Txt")
    public void setAddressLine2Txt(String addressLine2Txt) {
        this.addressLine2Txt = addressLine2Txt;
    }

    /**
     * 
     * @return
     *     The cityName
     */
    // @JsonProperty("cityName")
    public String getCityName() {
        return cityName;
    }

    /**
     * 
     * @param cityName
     *     The cityName
     */
    // @JsonProperty("cityName")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 
     * @return
     *     The stateProvinceCode
     */
    // @JsonProperty("stateProvinceCode")
    public String getStateProvinceCode() {
        return stateProvinceCode;
    }

    /**
     * 
     * @param stateProvinceCode
     *     The stateProvinceCode
     */
    // @JsonProperty("stateProvinceCode")
    public void setStateProvinceCode(String stateProvinceCode) {
        this.stateProvinceCode = stateProvinceCode;
    }

    /**
     * 
     * @return
     *     The localityName
     */
    // @JsonProperty("localityName")
    public String getLocalityName() {
        return localityName;
    }

    /**
     * 
     * @param localityName
     *     The localityName
     */
    // @JsonProperty("localityName")
    public void setLocalityName(String localityName) {
        this.localityName = localityName;
    }

    /**
     * 
     * @return
     *     The postalCode
     */
    // @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * 
     * @param postalCode
     *     The postalCode
     */
    // @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * 
     * @return
     *     The countryName
     */
    // @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    /**
     * 
     * @param countryName
     *     The countryName
     */
    // @JsonProperty("countryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 
     * @return
     *     The countryCode
     */
    // @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 
     * @param countryCode
     *     The countryCode
     */
    // @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 
     * @return
     *     The timezone
     */
    // @JsonProperty("timezone")
    public String getTimezone() {
        return timezone;
    }

    /**
     * 
     * @param timezone
     *     The timezone
     */
    // @JsonProperty("timezone")
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * 
     * @return
     *     The timezoneOffset
     */
    // @JsonProperty("timezoneOffset")
    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    /**
     * 
     * @param timezoneOffset
     *     The timezoneOffset
     */
    // @JsonProperty("timezoneOffset")
    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    /**
     * 
     * @return
     *     The timezoneDST
     */
    // @JsonProperty("timezoneDST")
    public Integer getTimezoneDST() {
        return timezoneDST;
    }

    /**
     * 
     * @param timezoneDST
     *     The timezoneDST
     */
    // @JsonProperty("timezoneDST")
    public void setTimezoneDST(Integer timezoneDST) {
        this.timezoneDST = timezoneDST;
    }

    /**
     * 
     * @return
     *     The latitude
     */
    // @JsonProperty("latitude")
    public String getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    // @JsonProperty("latitude")
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    // @JsonProperty("longitude")
    public String getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    // @JsonProperty("longitude")
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     *     The directionsTxt
     */
    // @JsonProperty("directionsTxt")
    public String getDirectionsTxt() {
        return directionsTxt;
    }

    /**
     * 
     * @param directionsTxt
     *     The directionsTxt
     */
    // @JsonProperty("directionsTxt")
    public void setDirectionsTxt(String directionsTxt) {
        this.directionsTxt = directionsTxt;
    }

    /**
     * 
     * @return
     *     The geoPoint
     */
    // @JsonProperty("geoPoint")
    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    /**
     * 
     * @param geoPoint
     *     The geoPoint
     */
    // @JsonProperty("geoPoint")
    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }

    /**
     * 
     * @return
     *     The dma
     */
    // @JsonProperty("dma")
    public Dma getDma() {
        return dma;
    }

    /**
     * 
     * @param dma
     *     The dma
     */
    // @JsonProperty("dma")
    public void setDma(Dma dma) {
        this.dma = dma;
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
