
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
    "organizationGuid",
    "organizationName",
    "organizationDsc",
    "organizationUrlAdr",
    "addressLine1Txt",
    "addressLine2Txt",
    "addressCityName",
    "addressStateProvinceCode",
    "addressLocalityName",
    "addressPostalCd",
    "addressCountryCd",
    "fax",
    "sourceSystemGuid",
    "primaryContactEmailAdr",
    "primaryContactPhone",
    "primaryContactName",
    "imageUrlAdr",
    "shortDsc",
    "showOrganizationName",
    "hideOrganizationContact",
    "isDeleted"
})
public class Organization {

    @JsonProperty("organizationGuid")
    private String organizationGuid;
    @JsonProperty("organizationName")
    private String organizationName;
    @JsonProperty("organizationDsc")
    private String organizationDsc;
    @JsonProperty("organizationUrlAdr")
    private String organizationUrlAdr;
    @JsonProperty("addressLine1Txt")
    private String addressLine1Txt;
    @JsonProperty("addressLine2Txt")
    private String addressLine2Txt;
    @JsonProperty("addressCityName")
    private String addressCityName;
    @JsonProperty("addressStateProvinceCode")
    private String addressStateProvinceCode;
    @JsonProperty("addressLocalityName")
    private String addressLocalityName;
    @JsonProperty("addressPostalCd")
    private String addressPostalCd;
    @JsonProperty("addressCountryCd")
    private String addressCountryCd;
    @JsonProperty("fax")
    private String fax;
    @JsonProperty("sourceSystemGuid")
    private String sourceSystemGuid;
    @JsonProperty("primaryContactEmailAdr")
    private String primaryContactEmailAdr;
    @JsonProperty("primaryContactPhone")
    private String primaryContactPhone;
    @JsonProperty("primaryContactName")
    private String primaryContactName;
    @JsonProperty("imageUrlAdr")
    private String imageUrlAdr;
    @JsonProperty("shortDsc")
    private String shortDsc;
    @JsonProperty("showOrganizationName")
    private String showOrganizationName;
    @JsonProperty("hideOrganizationContact")
    private String hideOrganizationContact;
    @JsonProperty("isDeleted")
    private String isDeleted;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The organizationGuid
     */
    //@JsonProperty("organizationGuid")
    public String getOrganizationGuid() {
        return organizationGuid;
    }

    /**
     * 
     * @param organizationGuid
     *     The organizationGuid
     */
    // @JsonProperty("organizationGuid")
    public void setOrganizationGuid(String organizationGuid) {
        this.organizationGuid = organizationGuid;
    }

    /**
     * 
     * @return
     *     The organizationName
     */
    // @JsonProperty("organizationName")
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * 
     * @param organizationName
     *     The organizationName
     */
    // @JsonProperty("organizationName")
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    /**
     * 
     * @return
     *     The organizationDsc
     */
    // @JsonProperty("organizationDsc")
    public String getOrganizationDsc() {
        return organizationDsc;
    }

    /**
     * 
     * @param organizationDsc
     *     The organizationDsc
     */
    // @JsonProperty("organizationDsc")
    public void setOrganizationDsc(String organizationDsc) {
        this.organizationDsc = organizationDsc;
    }

    /**
     * 
     * @return
     *     The organizationUrlAdr
     */
    // @JsonProperty("organizationUrlAdr")
    public String getOrganizationUrlAdr() {
        return organizationUrlAdr;
    }

    /**
     * 
     * @param organizationUrlAdr
     *     The organizationUrlAdr
     */
    // @JsonProperty("organizationUrlAdr")
    public void setOrganizationUrlAdr(String organizationUrlAdr) {
        this.organizationUrlAdr = organizationUrlAdr;
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
     *     The addressCityName
     */
    // @JsonProperty("addressCityName")
    public String getAddressCityName() {
        return addressCityName;
    }

    /**
     * 
     * @param addressCityName
     *     The addressCityName
     */
    // @JsonProperty("addressCityName")
    public void setAddressCityName(String addressCityName) {
        this.addressCityName = addressCityName;
    }

    /**
     * 
     * @return
     *     The addressStateProvinceCode
     */
    // @JsonProperty("addressStateProvinceCode")
    public String getAddressStateProvinceCode() {
        return addressStateProvinceCode;
    }

    /**
     * 
     * @param addressStateProvinceCode
     *     The addressStateProvinceCode
     */
    // @JsonProperty("addressStateProvinceCode")
    public void setAddressStateProvinceCode(String addressStateProvinceCode) {
        this.addressStateProvinceCode = addressStateProvinceCode;
    }

    /**
     * 
     * @return
     *     The addressLocalityName
     */
    // @JsonProperty("addressLocalityName")
    public String getAddressLocalityName() {
        return addressLocalityName;
    }

    /**
     * 
     * @param addressLocalityName
     *     The addressLocalityName
     */
    // @JsonProperty("addressLocalityName")
    public void setAddressLocalityName(String addressLocalityName) {
        this.addressLocalityName = addressLocalityName;
    }

    /**
     * 
     * @return
     *     The addressPostalCd
     */
    // @JsonProperty("addressPostalCd")
    public String getAddressPostalCd() {
        return addressPostalCd;
    }

    /**
     * 
     * @param addressPostalCd
     *     The addressPostalCd
     */
    // @JsonProperty("addressPostalCd")
    public void setAddressPostalCd(String addressPostalCd) {
        this.addressPostalCd = addressPostalCd;
    }

    /**
     * 
     * @return
     *     The addressCountryCd
     */
    // @JsonProperty("addressCountryCd")
    public String getAddressCountryCd() {
        return addressCountryCd;
    }

    /**
     * 
     * @param addressCountryCd
     *     The addressCountryCd
     */
    // @JsonProperty("addressCountryCd")
    public void setAddressCountryCd(String addressCountryCd) {
        this.addressCountryCd = addressCountryCd;
    }

    /**
     * 
     * @return
     *     The fax
     */
    // @JsonProperty("fax")
    public String getFax() {
        return fax;
    }

    /**
     * 
     * @param fax
     *     The fax
     */
    // @JsonProperty("fax")
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 
     * @return
     *     The sourceSystemGuid
     */
    // @JsonProperty("sourceSystemGuid")
    public String getSourceSystemGuid() {
        return sourceSystemGuid;
    }

    /**
     * 
     * @param sourceSystemGuid
     *     The sourceSystemGuid
     */
    // @JsonProperty("sourceSystemGuid")
    public void setSourceSystemGuid(String sourceSystemGuid) {
        this.sourceSystemGuid = sourceSystemGuid;
    }

    /**
     * 
     * @return
     *     The primaryContactEmailAdr
     */
    // @JsonProperty("primaryContactEmailAdr")
    public String getPrimaryContactEmailAdr() {
        return primaryContactEmailAdr;
    }

    /**
     * 
     * @param primaryContactEmailAdr
     *     The primaryContactEmailAdr
     */
    // @JsonProperty("primaryContactEmailAdr")
    public void setPrimaryContactEmailAdr(String primaryContactEmailAdr) {
        this.primaryContactEmailAdr = primaryContactEmailAdr;
    }

    /**
     * 
     * @return
     *     The primaryContactPhone
     */
    // @JsonProperty("primaryContactPhone")
    public String getPrimaryContactPhone() {
        return primaryContactPhone;
    }

    /**
     * 
     * @param primaryContactPhone
     *     The primaryContactPhone
     */
    // @JsonProperty("primaryContactPhone")
    public void setPrimaryContactPhone(String primaryContactPhone) {
        this.primaryContactPhone = primaryContactPhone;
    }

    /**
     * 
     * @return
     *     The primaryContactName
     */
    // @JsonProperty("primaryContactName")
    public String getPrimaryContactName() {
        return primaryContactName;
    }

    /**
     * 
     * @param primaryContactName
     *     The primaryContactName
     */
    // @JsonProperty("primaryContactName")
    public void setPrimaryContactName(String primaryContactName) {
        this.primaryContactName = primaryContactName;
    }

    /**
     * 
     * @return
     *     The imageUrlAdr
     */
    // @JsonProperty("imageUrlAdr")
    public String getImageUrlAdr() {
        return imageUrlAdr;
    }

    /**
     * 
     * @param imageUrlAdr
     *     The imageUrlAdr
     */
    // @JsonProperty("imageUrlAdr")
    public void setImageUrlAdr(String imageUrlAdr) {
        this.imageUrlAdr = imageUrlAdr;
    }

    /**
     * 
     * @return
     *     The shortDsc
     */
    // @JsonProperty("shortDsc")
    public String getShortDsc() {
        return shortDsc;
    }

    /**
     * 
     * @param shortDsc
     *     The shortDsc
     */
    // @JsonProperty("shortDsc")
    public void setShortDsc(String shortDsc) {
        this.shortDsc = shortDsc;
    }

    /**
     * 
     * @return
     *     The showOrganizationName
     */
    // @JsonProperty("showOrganizationName")
    public String getShowOrganizationName() {
        return showOrganizationName;
    }

    /**
     * 
     * @param showOrganizationName
     *     The showOrganizationName
     */
    // @JsonProperty("showOrganizationName")
    public void setShowOrganizationName(String showOrganizationName) {
        this.showOrganizationName = showOrganizationName;
    }

    /**
     * 
     * @return
     *     The hideOrganizationContact
     */
    // @JsonProperty("hideOrganizationContact")
    public String getHideOrganizationContact() {
        return hideOrganizationContact;
    }

    /**
     * 
     * @param hideOrganizationContact
     *     The hideOrganizationContact
     */
    // @JsonProperty("hideOrganizationContact")
    public void setHideOrganizationContact(String hideOrganizationContact) {
        this.hideOrganizationContact = hideOrganizationContact;
    }

    /**
     * 
     * @return
     *     The isDeleted
     */
    // @JsonProperty("isDeleted")
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 
     * @param isDeleted
     *     The isDeleted
     */
    // @JsonProperty("isDeleted")
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
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
