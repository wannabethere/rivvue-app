
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
    "assetTypeId",
    "typeName",
    "uploadSearchUrlAdr",
    "authorName",
    "onlineRegistration",
    "onlineDonation",
    "onlineMembership",
    "onlineMembershipCostAmt",
    "costAmt",
    "avgUserRatingTxt",
    "estParticipantNb",
    "maxTeamNb",
    "minGuaranteedGameNb",
    "multipleStartDate",
    "genderRequirementTxt",
    "participationCriteriaTxt",
    "userCommentTxt",
    "priceExtensionTxt",
    "searchWeight",
    "seoUrl",
    "substitutionUrl",
    "trackbackUrl",
    "eventCategories",
    "isSearchable",
    "createdDate",
    "modifiedDate"
})
public class AssetLegacyData {

    @JsonProperty("assetTypeId")
    private String assetTypeId;
    @JsonProperty("typeName")
    private String typeName;
    @JsonProperty("uploadSearchUrlAdr")
    private String uploadSearchUrlAdr;
    @JsonProperty("authorName")
    private String authorName;
    @JsonProperty("onlineRegistration")
    private String onlineRegistration;
    @JsonProperty("onlineDonation")
    private String onlineDonation;
    @JsonProperty("onlineMembership")
    private String onlineMembership;
    @JsonProperty("onlineMembershipCostAmt")
    private String onlineMembershipCostAmt;
    @JsonProperty("costAmt")
    private String costAmt;
    @JsonProperty("avgUserRatingTxt")
    private String avgUserRatingTxt;
    @JsonProperty("estParticipantNb")
    private String estParticipantNb;
    @JsonProperty("maxTeamNb")
    private String maxTeamNb;
    @JsonProperty("minGuaranteedGameNb")
    private String minGuaranteedGameNb;
    @JsonProperty("multipleStartDate")
    private String multipleStartDate;
    @JsonProperty("genderRequirementTxt")
    private String genderRequirementTxt;
    @JsonProperty("participationCriteriaTxt")
    private String participationCriteriaTxt;
    @JsonProperty("userCommentTxt")
    private String userCommentTxt;
    @JsonProperty("priceExtensionTxt")
    private String priceExtensionTxt;
    @JsonProperty("searchWeight")
    private String searchWeight;
    @JsonProperty("seoUrl")
    private String seoUrl;
    @JsonProperty("substitutionUrl")
    private String substitutionUrl;
    @JsonProperty("trackbackUrl")
    private String trackbackUrl;
    @JsonProperty("eventCategories")
    private String eventCategories;
    @JsonProperty("isSearchable")
    private String isSearchable;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("modifiedDate")
    private String modifiedDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The assetTypeId
     */
    // @JsonProperty("assetTypeId")
    public String getAssetTypeId() {
        return assetTypeId;
    }

    /**
     * 
     * @param assetTypeId
     *     The assetTypeId
     */
    // @JsonProperty("assetTypeId")
    public void setAssetTypeId(String assetTypeId) {
        this.assetTypeId = assetTypeId;
    }

    /**
     * 
     * @return
     *     The typeName
     */
    // @JsonProperty("typeName")
    public String getTypeName() {
        return typeName;
    }

    /**
     * 
     * @param typeName
     *     The typeName
     */
    // @JsonProperty("typeName")
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 
     * @return
     *     The uploadSearchUrlAdr
     */
    // @JsonProperty("uploadSearchUrlAdr")
    public String getUploadSearchUrlAdr() {
        return uploadSearchUrlAdr;
    }

    /**
     * 
     * @param uploadSearchUrlAdr
     *     The uploadSearchUrlAdr
     */
    // @JsonProperty("uploadSearchUrlAdr")
    public void setUploadSearchUrlAdr(String uploadSearchUrlAdr) {
        this.uploadSearchUrlAdr = uploadSearchUrlAdr;
    }

    /**
     * 
     * @return
     *     The authorName
     */
    // @JsonProperty("authorName")
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 
     * @param authorName
     *     The authorName
     */
    // @JsonProperty("authorName")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 
     * @return
     *     The onlineRegistration
     */
    // @JsonProperty("onlineRegistration")
    public String getOnlineRegistration() {
        return onlineRegistration;
    }

    /**
     * 
     * @param onlineRegistration
     *     The onlineRegistration
     */
    // @JsonProperty("onlineRegistration")
    public void setOnlineRegistration(String onlineRegistration) {
        this.onlineRegistration = onlineRegistration;
    }

    /**
     * 
     * @return
     *     The onlineDonation
     */
    // @JsonProperty("onlineDonation")
    public String getOnlineDonation() {
        return onlineDonation;
    }

    /**
     * 
     * @param onlineDonation
     *     The onlineDonation
     */
    // @JsonProperty("onlineDonation")
    public void setOnlineDonation(String onlineDonation) {
        this.onlineDonation = onlineDonation;
    }

    /**
     * 
     * @return
     *     The onlineMembership
     */
    // @JsonProperty("onlineMembership")
    public String getOnlineMembership() {
        return onlineMembership;
    }

    /**
     * 
     * @param onlineMembership
     *     The onlineMembership
     */
    // @JsonProperty("onlineMembership")
    public void setOnlineMembership(String onlineMembership) {
        this.onlineMembership = onlineMembership;
    }

    /**
     * 
     * @return
     *     The onlineMembershipCostAmt
     */
    // @JsonProperty("onlineMembershipCostAmt")
    public String getOnlineMembershipCostAmt() {
        return onlineMembershipCostAmt;
    }

    /**
     * 
     * @param onlineMembershipCostAmt
     *     The onlineMembershipCostAmt
     */
    // @JsonProperty("onlineMembershipCostAmt")
    public void setOnlineMembershipCostAmt(String onlineMembershipCostAmt) {
        this.onlineMembershipCostAmt = onlineMembershipCostAmt;
    }

    /**
     * 
     * @return
     *     The costAmt
     */
    // @JsonProperty("costAmt")
    public String getCostAmt() {
        return costAmt;
    }

    /**
     * 
     * @param costAmt
     *     The costAmt
     */
    // @JsonProperty("costAmt")
    public void setCostAmt(String costAmt) {
        this.costAmt = costAmt;
    }

    /**
     * 
     * @return
     *     The avgUserRatingTxt
     */
    // @JsonProperty("avgUserRatingTxt")
    public String getAvgUserRatingTxt() {
        return avgUserRatingTxt;
    }

    /**
     * 
     * @param avgUserRatingTxt
     *     The avgUserRatingTxt
     */
    // @JsonProperty("avgUserRatingTxt")
    public void setAvgUserRatingTxt(String avgUserRatingTxt) {
        this.avgUserRatingTxt = avgUserRatingTxt;
    }

    /**
     * 
     * @return
     *     The estParticipantNb
     */
    // @JsonProperty("estParticipantNb")
    public String getEstParticipantNb() {
        return estParticipantNb;
    }

    /**
     * 
     * @param estParticipantNb
     *     The estParticipantNb
     */
    // @JsonProperty("estParticipantNb")
    public void setEstParticipantNb(String estParticipantNb) {
        this.estParticipantNb = estParticipantNb;
    }

    /**
     * 
     * @return
     *     The maxTeamNb
     */
    // @JsonProperty("maxTeamNb")
    public String getMaxTeamNb() {
        return maxTeamNb;
    }

    /**
     * 
     * @param maxTeamNb
     *     The maxTeamNb
     */
    // @JsonProperty("maxTeamNb")
    public void setMaxTeamNb(String maxTeamNb) {
        this.maxTeamNb = maxTeamNb;
    }

    /**
     * 
     * @return
     *     The minGuaranteedGameNb
     */
    // @JsonProperty("minGuaranteedGameNb")
    public String getMinGuaranteedGameNb() {
        return minGuaranteedGameNb;
    }

    /**
     * 
     * @param minGuaranteedGameNb
     *     The minGuaranteedGameNb
     */
    // @JsonProperty("minGuaranteedGameNb")
    public void setMinGuaranteedGameNb(String minGuaranteedGameNb) {
        this.minGuaranteedGameNb = minGuaranteedGameNb;
    }

    /**
     * 
     * @return
     *     The multipleStartDate
     */
    // @JsonProperty("multipleStartDate")
    public String getMultipleStartDate() {
        return multipleStartDate;
    }

    /**
     * 
     * @param multipleStartDate
     *     The multipleStartDate
     */
    // @JsonProperty("multipleStartDate")
    public void setMultipleStartDate(String multipleStartDate) {
        this.multipleStartDate = multipleStartDate;
    }

    /**
     * 
     * @return
     *     The genderRequirementTxt
     */
    // @JsonProperty("genderRequirementTxt")
    public String getGenderRequirementTxt() {
        return genderRequirementTxt;
    }

    /**
     * 
     * @param genderRequirementTxt
     *     The genderRequirementTxt
     */
    // @JsonProperty("genderRequirementTxt")
    public void setGenderRequirementTxt(String genderRequirementTxt) {
        this.genderRequirementTxt = genderRequirementTxt;
    }

    /**
     * 
     * @return
     *     The participationCriteriaTxt
     */
    // @JsonProperty("participationCriteriaTxt")
    public String getParticipationCriteriaTxt() {
        return participationCriteriaTxt;
    }

    /**
     * 
     * @param participationCriteriaTxt
     *     The participationCriteriaTxt
     */
    // @JsonProperty("participationCriteriaTxt")
    public void setParticipationCriteriaTxt(String participationCriteriaTxt) {
        this.participationCriteriaTxt = participationCriteriaTxt;
    }

    /**
     * 
     * @return
     *     The userCommentTxt
     */
    // @JsonProperty("userCommentTxt")
    public String getUserCommentTxt() {
        return userCommentTxt;
    }

    /**
     * 
     * @param userCommentTxt
     *     The userCommentTxt
     */
    // @JsonProperty("userCommentTxt")
    public void setUserCommentTxt(String userCommentTxt) {
        this.userCommentTxt = userCommentTxt;
    }

    /**
     * 
     * @return
     *     The priceExtensionTxt
     */
    // @JsonProperty("priceExtensionTxt")
    public String getPriceExtensionTxt() {
        return priceExtensionTxt;
    }

    /**
     * 
     * @param priceExtensionTxt
     *     The priceExtensionTxt
     */
    // @JsonProperty("priceExtensionTxt")
    public void setPriceExtensionTxt(String priceExtensionTxt) {
        this.priceExtensionTxt = priceExtensionTxt;
    }

    /**
     * 
     * @return
     *     The searchWeight
     */
    // @JsonProperty("searchWeight")
    public String getSearchWeight() {
        return searchWeight;
    }

    /**
     * 
     * @param searchWeight
     *     The searchWeight
     */
    // @JsonProperty("searchWeight")
    public void setSearchWeight(String searchWeight) {
        this.searchWeight = searchWeight;
    }

    /**
     * 
     * @return
     *     The seoUrl
     */
    // @JsonProperty("seoUrl")
    public String getSeoUrl() {
        return seoUrl;
    }

    /**
     * 
     * @param seoUrl
     *     The seoUrl
     */
    // @JsonProperty("seoUrl")
    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    /**
     * 
     * @return
     *     The substitutionUrl
     */
    // @JsonProperty("substitutionUrl")
    public String getSubstitutionUrl() {
        return substitutionUrl;
    }

    /**
     * 
     * @param substitutionUrl
     *     The substitutionUrl
     */
    // @JsonProperty("substitutionUrl")
    public void setSubstitutionUrl(String substitutionUrl) {
        this.substitutionUrl = substitutionUrl;
    }

    /**
     * 
     * @return
     *     The trackbackUrl
     */
    // @JsonProperty("trackbackUrl")
    public String getTrackbackUrl() {
        return trackbackUrl;
    }

    /**
     * 
     * @param trackbackUrl
     *     The trackbackUrl
     */
    // @JsonProperty("trackbackUrl")
    public void setTrackbackUrl(String trackbackUrl) {
        this.trackbackUrl = trackbackUrl;
    }

    /**
     * 
     * @return
     *     The eventCategories
     */
    // @JsonProperty("eventCategories")
    public String getEventCategories() {
        return eventCategories;
    }

    /**
     * 
     * @param eventCategories
     *     The eventCategories
     */
    // @JsonProperty("eventCategories")
    public void setEventCategories(String eventCategories) {
        this.eventCategories = eventCategories;
    }

    /**
     * 
     * @return
     *     The isSearchable
     */
    // @JsonProperty("isSearchable")
    public String getIsSearchable() {
        return isSearchable;
    }

    /**
     * 
     * @param isSearchable
     *     The isSearchable
     */
    // @JsonProperty("isSearchable")
    public void setIsSearchable(String isSearchable) {
        this.isSearchable = isSearchable;
    }

    /**
     * 
     * @return
     *     The createdDate
     */
    // @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * 
     * @param createdDate
     *     The createdDate
     */
    // @JsonProperty("createdDate")
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 
     * @return
     *     The modifiedDate
     */
    // @JsonProperty("modifiedDate")
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * 
     * @param modifiedDate
     *     The modifiedDate
     */
    // @JsonProperty("modifiedDate")
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
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
