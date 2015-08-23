
package com.yatrix.activity.dataloader.store.mongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "assetStatus",
    "organization",
    "place",
    "evergreenParentAsset",
    "sourceSystem",
    "assetRootAsset",
    "assetParentAsset",
    "market",
    "assetGuid",
    "assetName",
    "assetDsc",
    "alternateName",
    "timezone",
    "localTimeZoneId",
    "timezoneAbb",
    "timezoneName",
    "currencyCd",
    "localeCd",
    "salesStartDate",
    "salesEndDate",
    "urlAdr",
    "detailPageTemplateId",
    "preferredUrlAdr",
    "logoUrlAdr",
    "activityStartDate",
    "activityEndDate",
    "donationUrlAdr",
    "teamUrlAdr",
    "homePageUrlAdr",
    "registrationUrlAdr",
    "registrantSearchUrlAdr",
    "regReqMinAge",
    "regReqMaxAge",
    "regReqGenderCd",
    "resultsUrlAdr",
    "contactName",
    "contactEmailAdr",
    "contactPhone",
    "contactTxt",
    "showContact",
    "sorId",
    "sorCreateDtm",
    "sorCreateUserId",
    "authorName",
    "childIndex",
    "publishDate",
    "createdDate",
    "modifiedDate",
    "activityRecurrences",
    "assetQuantity",
    "assetLegacyData",
    "assetTags",
    "assetAttributes",
    "assetPrices",
    "assetDescriptions",
    "assetChannels",
    "assetMediaTypes",
    "assetImages",
    "assetTopics",
    "assetCategories",
    "assetMetaInterests",
    "assetInterests",
    "assetDeals",
    "assetComponents",
    "assetServiceHostName",
    "componentInUrlAdr",
    "assetSeoUrls",
    "overrideSeourlFlag",
    "activenetTopGraphic",
    "evergreenAssetFlag",
    "evergreenAssets",
    "searchScore",
    "assetVersion",
    "salesStatus",
    "retryDate",
    "retryCounter",
    "socialMedia",
    "assetReferences"
})
@Document(collection="AmpActiveEvents")
public class AmpActiveEvent extends Item implements Serializable{

 
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("assetStatus")
    private AssetStatus assetStatus;
    @JsonProperty("organization")
    private Organization organization;
    @JsonProperty("place")
    private Place place;
    @JsonProperty("evergreenParentAsset")
    private EvergreenParentAsset evergreenParentAsset;
    @JsonProperty("sourceSystem")
    private SourceSystem sourceSystem;
    @JsonProperty("assetRootAsset")
    private AssetRootAsset assetRootAsset;
    @JsonProperty("assetParentAsset")
    private AssetParentAsset assetParentAsset;
    @JsonProperty("market")
    private Market market;
    @JsonProperty("assetGuid")
    private String assetGuid;
    @JsonProperty("assetName")
    private String assetName;
    @JsonProperty("assetDsc")
    private String assetDsc;
    @JsonProperty("alternateName")
    private String alternateName;
    @JsonProperty("timezone")
    private String timezone;
    @JsonProperty("localTimeZoneId")
    private String localTimeZoneId;
    @JsonProperty("timezoneAbb")
    private String timezoneAbb;
    @JsonProperty("timezoneName")
    private String timezoneName;
    @JsonProperty("currencyCd")
    private String currencyCd;
    @JsonProperty("localeCd")
    private String localeCd;
    @JsonProperty("salesStartDate")
    private String salesStartDate;
    @JsonProperty("salesEndDate")
    private String salesEndDate;
    @JsonProperty("urlAdr")
    private String urlAdr;
    @JsonProperty("detailPageTemplateId")
    private String detailPageTemplateId;
    @JsonProperty("preferredUrlAdr")
    private String preferredUrlAdr;
    @JsonProperty("logoUrlAdr")
    private String logoUrlAdr;
    @JsonProperty("activityStartDate")
    private String activityStartDate;
    @JsonProperty("activityEndDate")
    private String activityEndDate;
    @JsonProperty("donationUrlAdr")
    private String donationUrlAdr;
    @JsonProperty("teamUrlAdr")
    private String teamUrlAdr;
    @JsonProperty("homePageUrlAdr")
    private String homePageUrlAdr;
    @JsonProperty("registrationUrlAdr")
    private String registrationUrlAdr;
    @JsonProperty("registrantSearchUrlAdr")
    private String registrantSearchUrlAdr;
    @JsonProperty("regReqMinAge")
    private Object regReqMinAge;
    @JsonProperty("regReqMaxAge")
    private Object regReqMaxAge;
    @JsonProperty("regReqGenderCd")
    private String regReqGenderCd;
    @JsonProperty("resultsUrlAdr")
    private String resultsUrlAdr;
    @JsonProperty("contactName")
    private String contactName;
    @JsonProperty("contactEmailAdr")
    private String contactEmailAdr;
    @JsonProperty("contactPhone")
    private String contactPhone;
    @JsonProperty("contactTxt")
    private String contactTxt;
    @JsonProperty("showContact")
    private String showContact;
    @JsonProperty("sorId")
    private String sorId;
    @JsonProperty("sorCreateDtm")
    private Object sorCreateDtm;
    @JsonProperty("sorCreateUserId")
    private String sorCreateUserId;
    @JsonProperty("authorName")
    private String authorName;
    @JsonProperty("childIndex")
    private String childIndex;
    @JsonProperty("publishDate")
    private Object publishDate;
    @JsonProperty("createdDate")
    private String createdDate;
    @JsonProperty("modifiedDate")
    private String modifiedDate;
    @JsonProperty("activityRecurrences")
    private List<ActivityRecurrence> activityRecurrences = new ArrayList<ActivityRecurrence>();
    @JsonProperty("assetQuantity")
    private AssetQuantity assetQuantity;
    @JsonProperty("assetLegacyData")
    private AssetLegacyData assetLegacyData;
    @JsonProperty("assetTags")
    private List<AssetTag> assetTags = new ArrayList<AssetTag>();
    @JsonProperty("assetAttributes")
    private List<AssetAttribute> assetAttributes = new ArrayList<AssetAttribute>();
    @JsonProperty("assetPrices")
    private List<AssetPrice> assetPrices = new ArrayList<AssetPrice>();
    @JsonProperty("assetDescriptions")
    private List<AssetDescription> assetDescriptions = new ArrayList<AssetDescription>();
    @JsonProperty("assetChannels")
    private List<AssetChannel> assetChannels = new ArrayList<AssetChannel>();
    @JsonProperty("assetMediaTypes")
    private List<AssetMediaType> assetMediaTypes = new ArrayList<AssetMediaType>();
    @JsonProperty("assetImages")
    private List<AssetImage> assetImages = new ArrayList<AssetImage>();
    @JsonProperty("assetTopics")
    private List<AssetTopic> assetTopics = new ArrayList<AssetTopic>();
    @JsonProperty("assetCategories")
    private List<AssetCategory> assetCategories = new ArrayList<AssetCategory>();
    @JsonProperty("assetMetaInterests")
    private List<Object> assetMetaInterests = new ArrayList<Object>();
    @JsonProperty("assetInterests")
    private List<Object> assetInterests = new ArrayList<Object>();
    @JsonProperty("assetDeals")
    private List<Object> assetDeals = new ArrayList<Object>();
    @JsonProperty("assetComponents")
    private List<Object> assetComponents = new ArrayList<Object>();
    @JsonProperty("assetServiceHostName")
    private String assetServiceHostName;
    @JsonProperty("componentInUrlAdr")
    private ComponentInUrlAdr componentInUrlAdr;
    @JsonProperty("assetSeoUrls")
    private List<Object> assetSeoUrls = new ArrayList<Object>();
    @JsonProperty("overrideSeourlFlag")
    private String overrideSeourlFlag;
    @JsonProperty("activenetTopGraphic")
    private ActivenetTopGraphic activenetTopGraphic;
    @JsonProperty("evergreenAssetFlag")
    private String evergreenAssetFlag;
    @JsonProperty("evergreenAssets")
    private List<Object> evergreenAssets = new ArrayList<Object>();
    @JsonProperty("searchScore")
    private Double searchScore;
    @JsonProperty("assetVersion")
    private Integer assetVersion;
    @JsonProperty("salesStatus")
    private String salesStatus;
    @JsonProperty("retryDate")
    private String retryDate;
    @JsonProperty("retryCounter")
    private String retryCounter;
    @JsonProperty("socialMedia")
    private List<Object> socialMedia = new ArrayList<Object>();
    @JsonProperty("assetReferences")
    private List<Object> assetReferences = new ArrayList<Object>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The assetStatus
     */
   // @JsonProperty("assetStatus")
    public AssetStatus getAssetStatus() {
        return assetStatus;
    }

    /**
     * 
     * @param assetStatus
     *     The assetStatus
     */
   // @JsonProperty("assetStatus")
    public void setAssetStatus(AssetStatus assetStatus) {
        this.assetStatus = assetStatus;
    }

    /**
     * 
     * @return
     *     The organization
     */
   // @JsonProperty("organization")
    public Organization getOrganization() {
        return organization;
    }

    /**
     * 
     * @param organization
     *     The organization
     */
   // @JsonProperty("organization")
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * 
     * @return
     *     The place
     */
   // @JsonProperty("place")
    public Place getPlace() {
        return place;
    }

    /**
     * 
     * @param place
     *     The place
     */
   // @JsonProperty("place")
    public void setPlace(Place place) {
        this.place = place;
    }

    /**
     * 
     * @return
     *     The evergreenParentAsset
     */
   // @JsonProperty("evergreenParentAsset")
    public EvergreenParentAsset getEvergreenParentAsset() {
        return evergreenParentAsset;
    }

    /**
     * 
     * @param evergreenParentAsset
     *     The evergreenParentAsset
     */
   // @JsonProperty("evergreenParentAsset")
    public void setEvergreenParentAsset(EvergreenParentAsset evergreenParentAsset) {
        this.evergreenParentAsset = evergreenParentAsset;
    }

    /**
     * 
     * @return
     *     The sourceSystem
     */
   // @JsonProperty("sourceSystem")
    public SourceSystem getSourceSystem() {
        return sourceSystem;
    }

    /**
     * 
     * @param sourceSystem
     *     The sourceSystem
     */
   // @JsonProperty("sourceSystem")
    public void setSourceSystem(SourceSystem sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    /**
     * 
     * @return
     *     The assetRootAsset
     */
    //@JsonProperty("assetRootAsset")
    public AssetRootAsset getAssetRootAsset() {
        return assetRootAsset;
    }

    /**
     * 
     * @param assetRootAsset
     *     The assetRootAsset
     */
    //@JsonProperty("assetRootAsset")
    public void setAssetRootAsset(AssetRootAsset assetRootAsset) {
        this.assetRootAsset = assetRootAsset;
    }

    /**
     * 
     * @return
     *     The assetParentAsset
     */
   // @JsonProperty("assetParentAsset")
    public AssetParentAsset getAssetParentAsset() {
        return assetParentAsset;
    }

    /**
     * 
     * @param assetParentAsset
     *     The assetParentAsset
     */
    //@JsonProperty("assetParentAsset")
    public void setAssetParentAsset(AssetParentAsset assetParentAsset) {
        this.assetParentAsset = assetParentAsset;
    }

    /**
     * 
     * @return
     *     The market
     */
   // @JsonProperty("market")
    public Market getMarket() {
        return market;
    }

    /**
     * 
     * @param market
     *     The market
     */
    //@JsonProperty("market")
    public void setMarket(Market market) {
        this.market = market;
    }

    /**
     * 
     * @return
     *     The assetGuid
     */
   // @JsonProperty("assetGuid")
    public String getAssetGuid() {
        return assetGuid;
    }

    /**
     * 
     * @param assetGuid
     *     The assetGuid
     */
    //@JsonProperty("assetGuid")
    public void setAssetGuid(String assetGuid) {
        this.assetGuid = assetGuid;
    }

    /**
     * 
     * @return
     *     The assetName
     */
    //@JsonProperty("assetName")
    public String getAssetName() {
        return assetName;
    }

    /**
     * 
     * @param assetName
     *     The assetName
     */
   // @JsonProperty("assetName")
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    /**
     * 
     * @return
     *     The assetDsc
     */
   // @JsonProperty("assetDsc")
    public String getAssetDsc() {
        return assetDsc;
    }

    /**
     * 
     * @param assetDsc
     *     The assetDsc
     */
   // @JsonProperty("assetDsc")
    public void setAssetDsc(String assetDsc) {
        this.assetDsc = assetDsc;
    }

    /**
     * 
     * @return
     *     The alternateName
     */
   // @JsonProperty("alternateName")
    public String getAlternateName() {
        return alternateName;
    }

    /**
     * 
     * @param alternateName
     *     The alternateName
     */
    //@JsonProperty("alternateName")
    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
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
     *     The localTimeZoneId
     */
   // @JsonProperty("localTimeZoneId")
    public String getLocalTimeZoneId() {
        return localTimeZoneId;
    }

    /**
     * 
     * @param localTimeZoneId
     *     The localTimeZoneId
     */
    //@JsonProperty("localTimeZoneId")
    public void setLocalTimeZoneId(String localTimeZoneId) {
        this.localTimeZoneId = localTimeZoneId;
    }

    /**
     * 
     * @return
     *     The timezoneAbb
     */
   // @JsonProperty("timezoneAbb")
    public String getTimezoneAbb() {
        return timezoneAbb;
    }

    /**
     * 
     * @param timezoneAbb
     *     The timezoneAbb
     */
    //@JsonProperty("timezoneAbb")
    public void setTimezoneAbb(String timezoneAbb) {
        this.timezoneAbb = timezoneAbb;
    }

    /**
     * 
     * @return
     *     The timezoneName
     */
    //@JsonProperty("timezoneName")
    public String getTimezoneName() {
        return timezoneName;
    }

    /**
     * 
     * @param timezoneName
     *     The timezoneName
     */
   // @JsonProperty("timezoneName")
    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }

    /**
     * 
     * @return
     *     The currencyCd
     */
   // @JsonProperty("currencyCd")
    public String getCurrencyCd() {
        return currencyCd;
    }

    /**
     * 
     * @param currencyCd
     *     The currencyCd
     */
   // @JsonProperty("currencyCd")
    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }

    /**
     * 
     * @return
     *     The localeCd
     */
   // @JsonProperty("localeCd")
    public String getLocaleCd() {
        return localeCd;
    }

    /**
     * 
     * @param localeCd
     *     The localeCd
     */
    //@JsonProperty("localeCd")
    public void setLocaleCd(String localeCd) {
        this.localeCd = localeCd;
    }

    /**
     * 
     * @return
     *     The salesStartDate
     */
    //@JsonProperty("salesStartDate")
    public String getSalesStartDate() {
        return salesStartDate;
    }

    /**
     * 
     * @param salesStartDate
     *     The salesStartDate
     */
   // @JsonProperty("salesStartDate")
    public void setSalesStartDate(String salesStartDate) {
        this.salesStartDate = salesStartDate;
    }

    /**
     * 
     * @return
     *     The salesEndDate
     */
   // @JsonProperty("salesEndDate")
    public String getSalesEndDate() {
        return salesEndDate;
    }

    /**
     * 
     * @param salesEndDate
     *     The salesEndDate
     */
    //@JsonProperty("salesEndDate")
    public void setSalesEndDate(String salesEndDate) {
        this.salesEndDate = salesEndDate;
    }

    /**
     * 
     * @return
     *     The urlAdr
     */
   // @JsonProperty("urlAdr")
    public String getUrlAdr() {
        return urlAdr;
    }

    /**
     * 
     * @param urlAdr
     *     The urlAdr
     */
   // @JsonProperty("urlAdr")
    public void setUrlAdr(String urlAdr) {
        this.urlAdr = urlAdr;
    }

    /**
     * 
     * @return
     *     The detailPageTemplateId
     */
    //@JsonProperty("detailPageTemplateId")
    public String getDetailPageTemplateId() {
        return detailPageTemplateId;
    }

    /**
     * 
     * @param detailPageTemplateId
     *     The detailPageTemplateId
     */
   // @JsonProperty("detailPageTemplateId")
    public void setDetailPageTemplateId(String detailPageTemplateId) {
        this.detailPageTemplateId = detailPageTemplateId;
    }

    /**
     * 
     * @return
     *     The preferredUrlAdr
     */
    //@JsonProperty("preferredUrlAdr")
    public String getPreferredUrlAdr() {
        return preferredUrlAdr;
    }

    /**
     * 
     * @param preferredUrlAdr
     *     The preferredUrlAdr
     */
   // @JsonProperty("preferredUrlAdr")
    public void setPreferredUrlAdr(String preferredUrlAdr) {
        this.preferredUrlAdr = preferredUrlAdr;
    }

    /**
     * 
     * @return
     *     The logoUrlAdr
     */
   // @JsonProperty("logoUrlAdr")
    public String getLogoUrlAdr() {
        return logoUrlAdr;
    }

    /**
     * 
     * @param logoUrlAdr
     *     The logoUrlAdr
     */
   // @JsonProperty("logoUrlAdr")
    public void setLogoUrlAdr(String logoUrlAdr) {
        this.logoUrlAdr = logoUrlAdr;
    }

    /**
     * 
     * @return
     *     The activityStartDate
     */
    //@JsonProperty("activityStartDate")
    public String getActivityStartDate() {
        return activityStartDate;
    }

    /**
     * 
     * @param activityStartDate
     *     The activityStartDate
     */
    //@JsonProperty("activityStartDate")
    public void setActivityStartDate(String activityStartDate) {
        this.activityStartDate = activityStartDate;
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
     *     The donationUrlAdr
     */
    //@JsonProperty("donationUrlAdr")
    public String getDonationUrlAdr() {
        return donationUrlAdr;
    }

    /**
     * 
     * @param donationUrlAdr
     *     The donationUrlAdr
     */
    //@JsonProperty("donationUrlAdr")
    public void setDonationUrlAdr(String donationUrlAdr) {
        this.donationUrlAdr = donationUrlAdr;
    }

    /**
     * 
     * @return
     *     The teamUrlAdr
     */
    //@JsonProperty("teamUrlAdr")
    public String getTeamUrlAdr() {
        return teamUrlAdr;
    }

    /**
     * 
     * @param teamUrlAdr
     *     The teamUrlAdr
     */
    //@JsonProperty("teamUrlAdr")
    public void setTeamUrlAdr(String teamUrlAdr) {
        this.teamUrlAdr = teamUrlAdr;
    }

    /**
     * 
     * @return
     *     The homePageUrlAdr
     */
    // @JsonProperty("homePageUrlAdr")
    public String getHomePageUrlAdr() {
        return homePageUrlAdr;
    }

    /**
     * 
     * @param homePageUrlAdr
     *     The homePageUrlAdr
     */
    // @JsonProperty("homePageUrlAdr")
    public void setHomePageUrlAdr(String homePageUrlAdr) {
        this.homePageUrlAdr = homePageUrlAdr;
    }

    /**
     * 
     * @return
     *     The registrationUrlAdr
     */
    // @JsonProperty("registrationUrlAdr")
    public String getRegistrationUrlAdr() {
        return registrationUrlAdr;
    }

    /**
     * 
     * @param registrationUrlAdr
     *     The registrationUrlAdr
     */
    // @JsonProperty("registrationUrlAdr")
    public void setRegistrationUrlAdr(String registrationUrlAdr) {
        this.registrationUrlAdr = registrationUrlAdr;
    }

    /**
     * 
     * @return
     *     The registrantSearchUrlAdr
     */
    // @JsonProperty("registrantSearchUrlAdr")
    public String getRegistrantSearchUrlAdr() {
        return registrantSearchUrlAdr;
    }

    /**
     * 
     * @param registrantSearchUrlAdr
     *     The registrantSearchUrlAdr
     */
    // @JsonProperty("registrantSearchUrlAdr")
    public void setRegistrantSearchUrlAdr(String registrantSearchUrlAdr) {
        this.registrantSearchUrlAdr = registrantSearchUrlAdr;
    }

    /**
     * 
     * @return
     *     The regReqMinAge
     */
    // @JsonProperty("regReqMinAge")
    public Object getRegReqMinAge() {
        return regReqMinAge;
    }

    /**
     * 
     * @param regReqMinAge
     *     The regReqMinAge
     */
    // @JsonProperty("regReqMinAge")
    public void setRegReqMinAge(Object regReqMinAge) {
        this.regReqMinAge = regReqMinAge;
    }

    /**
     * 
     * @return
     *     The regReqMaxAge
     */
    // @JsonProperty("regReqMaxAge")
    public Object getRegReqMaxAge() {
        return regReqMaxAge;
    }

    /**
     * 
     * @param regReqMaxAge
     *     The regReqMaxAge
     */
    // @JsonProperty("regReqMaxAge")
    public void setRegReqMaxAge(Object regReqMaxAge) {
        this.regReqMaxAge = regReqMaxAge;
    }

    /**
     * 
     * @return
     *     The regReqGenderCd
     */
    // @JsonProperty("regReqGenderCd")
    public String getRegReqGenderCd() {
        return regReqGenderCd;
    }

    /**
     * 
     * @param regReqGenderCd
     *     The regReqGenderCd
     */
    // @JsonProperty("regReqGenderCd")
    public void setRegReqGenderCd(String regReqGenderCd) {
        this.regReqGenderCd = regReqGenderCd;
    }

    /**
     * 
     * @return
     *     The resultsUrlAdr
     */
    // @JsonProperty("resultsUrlAdr")
    public String getResultsUrlAdr() {
        return resultsUrlAdr;
    }

    /**
     * 
     * @param resultsUrlAdr
     *     The resultsUrlAdr
     */
    // @JsonProperty("resultsUrlAdr")
    public void setResultsUrlAdr(String resultsUrlAdr) {
        this.resultsUrlAdr = resultsUrlAdr;
    }

    /**
     * 
     * @return
     *     The contactName
     */
    // @JsonProperty("contactName")
    public String getContactName() {
        return contactName;
    }

    /**
     * 
     * @param contactName
     *     The contactName
     */
    // @JsonProperty("contactName")
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * 
     * @return
     *     The contactEmailAdr
     */
    // @JsonProperty("contactEmailAdr")
    public String getContactEmailAdr() {
        return contactEmailAdr;
    }

    /**
     * 
     * @param contactEmailAdr
     *     The contactEmailAdr
     */
    // @JsonProperty("contactEmailAdr")
    public void setContactEmailAdr(String contactEmailAdr) {
        this.contactEmailAdr = contactEmailAdr;
    }

    /**
     * 
     * @return
     *     The contactPhone
     */
    // @JsonProperty("contactPhone")
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * 
     * @param contactPhone
     *     The contactPhone
     */
    // @JsonProperty("contactPhone")
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * 
     * @return
     *     The contactTxt
     */
    // @JsonProperty("contactTxt")
    public String getContactTxt() {
        return contactTxt;
    }

    /**
     * 
     * @param contactTxt
     *     The contactTxt
     */
    // @JsonProperty("contactTxt")
    public void setContactTxt(String contactTxt) {
        this.contactTxt = contactTxt;
    }

    /**
     * 
     * @return
     *     The showContact
     */
    // @JsonProperty("showContact")
    public String getShowContact() {
        return showContact;
    }

    /**
     * 
     * @param showContact
     *     The showContact
     */
    // @JsonProperty("showContact")
    public void setShowContact(String showContact) {
        this.showContact = showContact;
    }

    /**
     * 
     * @return
     *     The sorId
     */
    // @JsonProperty("sorId")
    public String getSorId() {
        return sorId;
    }

    /**
     * 
     * @param sorId
     *     The sorId
     */
    // @JsonProperty("sorId")
    public void setSorId(String sorId) {
        this.sorId = sorId;
    }

    /**
     * 
     * @return
     *     The sorCreateDtm
     */
    // @JsonProperty("sorCreateDtm")
    public Object getSorCreateDtm() {
        return sorCreateDtm;
    }

    /**
     * 
     * @param sorCreateDtm
     *     The sorCreateDtm
     */
    // @JsonProperty("sorCreateDtm")
    public void setSorCreateDtm(Object sorCreateDtm) {
        this.sorCreateDtm = sorCreateDtm;
    }

    /**
     * 
     * @return
     *     The sorCreateUserId
     */
    // @JsonProperty("sorCreateUserId")
    public String getSorCreateUserId() {
        return sorCreateUserId;
    }

    /**
     * 
     * @param sorCreateUserId
     *     The sorCreateUserId
     */
    // @JsonProperty("sorCreateUserId")
    public void setSorCreateUserId(String sorCreateUserId) {
        this.sorCreateUserId = sorCreateUserId;
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
     *     The childIndex
     */
    // @JsonProperty("childIndex")
    public String getChildIndex() {
        return childIndex;
    }

    /**
     * 
     * @param childIndex
     *     The childIndex
     */
    // @JsonProperty("childIndex")
    public void setChildIndex(String childIndex) {
        this.childIndex = childIndex;
    }

    /**
     * 
     * @return
     *     The publishDate
     */
    // @JsonProperty("publishDate")
    public Object getPublishDate() {
        return publishDate;
    }

    /**
     * 
     * @param publishDate
     *     The publishDate
     */
    // @JsonProperty("publishDate")
    public void setPublishDate(Object publishDate) {
        this.publishDate = publishDate;
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

    /**
     * 
     * @return
     *     The activityRecurrences
     */
    // @JsonProperty("activityRecurrences")
    public List<ActivityRecurrence> getActivityRecurrences() {
        return activityRecurrences;
    }

    /**
     * 
     * @param activityRecurrences
     *     The activityRecurrences
     */
    // @JsonProperty("activityRecurrences")
    public void setActivityRecurrences(List<ActivityRecurrence> activityRecurrences) {
        this.activityRecurrences = activityRecurrences;
    }

    /**
     * 
     * @return
     *     The assetQuantity
     */
    // @JsonProperty("assetQuantity")
    public AssetQuantity getAssetQuantity() {
        return assetQuantity;
    }

    /**
     * 
     * @param assetQuantity
     *     The assetQuantity
     */
    // @JsonProperty("assetQuantity")
    public void setAssetQuantity(AssetQuantity assetQuantity) {
        this.assetQuantity = assetQuantity;
    }

    /**
     * 
     * @return
     *     The assetLegacyData
     */
    // @JsonProperty("assetLegacyData")
    public AssetLegacyData getAssetLegacyData() {
        return assetLegacyData;
    }

    /**
     * 
     * @param assetLegacyData
     *     The assetLegacyData
     */
    // @JsonProperty("assetLegacyData")
    public void setAssetLegacyData(AssetLegacyData assetLegacyData) {
        this.assetLegacyData = assetLegacyData;
    }

    /**
     * 
     * @return
     *     The assetTags
     */
    // @JsonProperty("assetTags")
    public List<AssetTag> getAssetTags() {
        return assetTags;
    }

    /**
     * 
     * @param assetTags
     *     The assetTags
     */
    // @JsonProperty("assetTags")
    public void setAssetTags(List<AssetTag> assetTags) {
        this.assetTags = assetTags;
    }

    /**
     * 
     * @return
     *     The assetAttributes
     */
    // @JsonProperty("assetAttributes")
    public List<AssetAttribute> getAssetAttributes() {
        return assetAttributes;
    }

    /**
     * 
     * @param assetAttributes
     *     The assetAttributes
     */
    // @JsonProperty("assetAttributes")
    public void setAssetAttributes(List<AssetAttribute> assetAttributes) {
        this.assetAttributes = assetAttributes;
    }

    /**
     * 
     * @return
     *     The assetPrices
     */
    // @JsonProperty("assetPrices")
    public List<AssetPrice> getAssetPrices() {
        return assetPrices;
    }

    /**
     * 
     * @param assetPrices
     *     The assetPrices
     */
    // @JsonProperty("assetPrices")
    public void setAssetPrices(List<AssetPrice> assetPrices) {
        this.assetPrices = assetPrices;
    }

    /**
     * 
     * @return
     *     The assetDescriptions
     */
    // @JsonProperty("assetDescriptions")
    public List<AssetDescription> getAssetDescriptions() {
        return assetDescriptions;
    }

    /**
     * 
     * @param assetDescriptions
     *     The assetDescriptions
     */
    // @JsonProperty("assetDescriptions")
    public void setAssetDescriptions(List<AssetDescription> assetDescriptions) {
        this.assetDescriptions = assetDescriptions;
    }

    /**
     * 
     * @return
     *     The assetChannels
     */
    // @JsonProperty("assetChannels")
    public List<AssetChannel> getAssetChannels() {
        return assetChannels;
    }

    /**
     * 
     * @param assetChannels
     *     The assetChannels
     */
    // @JsonProperty("assetChannels")
    public void setAssetChannels(List<AssetChannel> assetChannels) {
        this.assetChannels = assetChannels;
    }

    /**
     * 
     * @return
     *     The assetMediaTypes
     */
    // @JsonProperty("assetMediaTypes")
    public List<AssetMediaType> getAssetMediaTypes() {
        return assetMediaTypes;
    }

    /**
     * 
     * @param assetMediaTypes
     *     The assetMediaTypes
     */
    // @JsonProperty("assetMediaTypes")
    public void setAssetMediaTypes(List<AssetMediaType> assetMediaTypes) {
        this.assetMediaTypes = assetMediaTypes;
    }

    /**
     * 
     * @return
     *     The assetImages
     */
    // @JsonProperty("assetImages")
    public List<AssetImage> getAssetImages() {
        return assetImages;
    }

    /**
     * 
     * @param assetImages
     *     The assetImages
     */
    // @JsonProperty("assetImages")
    public void setAssetImages(List<AssetImage> assetImages) {
        this.assetImages = assetImages;
    }

    /**
     * 
     * @return
     *     The assetTopics
     */
    // @JsonProperty("assetTopics")
    public List<AssetTopic> getAssetTopics() {
        return assetTopics;
    }

    /**
     * 
     * @param assetTopics
     *     The assetTopics
     */
    // @JsonProperty("assetTopics")
    public void setAssetTopics(List<AssetTopic> assetTopics) {
        this.assetTopics = assetTopics;
    }

    /**
     * 
     * @return
     *     The assetCategories
     */
    // @JsonProperty("assetCategories")
    public List<AssetCategory> getAssetCategories() {
        return assetCategories;
    }

    /**
     * 
     * @param assetCategories
     *     The assetCategories
     */
    // @JsonProperty("assetCategories")
    public void setAssetCategories(List<AssetCategory> assetCategories) {
        this.assetCategories = assetCategories;
    }

    /**
     * 
     * @return
     *     The assetMetaInterests
     */
    // @JsonProperty("assetMetaInterests")
    public List<Object> getAssetMetaInterests() {
        return assetMetaInterests;
    }

    /**
     * 
     * @param assetMetaInterests
     *     The assetMetaInterests
     */
    // @JsonProperty("assetMetaInterests")
    public void setAssetMetaInterests(List<Object> assetMetaInterests) {
        this.assetMetaInterests = assetMetaInterests;
    }

    /**
     * 
     * @return
     *     The assetInterests
     */
    // @JsonProperty("assetInterests")
    public List<Object> getAssetInterests() {
        return assetInterests;
    }

    /**
     * 
     * @param assetInterests
     *     The assetInterests
     */
    // @JsonProperty("assetInterests")
    public void setAssetInterests(List<Object> assetInterests) {
        this.assetInterests = assetInterests;
    }

    /**
     * 
     * @return
     *     The assetDeals
     */
    // @JsonProperty("assetDeals")
    public List<Object> getAssetDeals() {
        return assetDeals;
    }

    /**
     * 
     * @param assetDeals
     *     The assetDeals
     */
    // @JsonProperty("assetDeals")
    public void setAssetDeals(List<Object> assetDeals) {
        this.assetDeals = assetDeals;
    }

    /**
     * 
     * @return
     *     The assetComponents
     */
    // @JsonProperty("assetComponents")
    public List<Object> getAssetComponents() {
        return assetComponents;
    }

    /**
     * 
     * @param assetComponents
     *     The assetComponents
     */
    // @JsonProperty("assetComponents")
    public void setAssetComponents(List<Object> assetComponents) {
        this.assetComponents = assetComponents;
    }

    /**
     * 
     * @return
     *     The assetServiceHostName
     */
    // @JsonProperty("assetServiceHostName")
    public String getAssetServiceHostName() {
        return assetServiceHostName;
    }

    /**
     * 
     * @param assetServiceHostName
     *     The assetServiceHostName
     */
    // @JsonProperty("assetServiceHostName")
    public void setAssetServiceHostName(String assetServiceHostName) {
        this.assetServiceHostName = assetServiceHostName;
    }

    /**
     * 
     * @return
     *     The componentInUrlAdr
     */
    // @JsonProperty("componentInUrlAdr")
    public ComponentInUrlAdr getComponentInUrlAdr() {
        return componentInUrlAdr;
    }

    /**
     * 
     * @param componentInUrlAdr
     *     The componentInUrlAdr
     */
    // @JsonProperty("componentInUrlAdr")
    public void setComponentInUrlAdr(ComponentInUrlAdr componentInUrlAdr) {
        this.componentInUrlAdr = componentInUrlAdr;
    }

    /**
     * 
     * @return
     *     The assetSeoUrls
     */
    // @JsonProperty("assetSeoUrls")
    public List<Object> getAssetSeoUrls() {
        return assetSeoUrls;
    }

    /**
     * 
     * @param assetSeoUrls
     *     The assetSeoUrls
     */
    // @JsonProperty("assetSeoUrls")
    public void setAssetSeoUrls(List<Object> assetSeoUrls) {
        this.assetSeoUrls = assetSeoUrls;
    }

    /**
     * 
     * @return
     *     The overrideSeourlFlag
     */
    // @JsonProperty("overrideSeourlFlag")
    public String getOverrideSeourlFlag() {
        return overrideSeourlFlag;
    }

    /**
     * 
     * @param overrideSeourlFlag
     *     The overrideSeourlFlag
     */
    // @JsonProperty("overrideSeourlFlag")
    public void setOverrideSeourlFlag(String overrideSeourlFlag) {
        this.overrideSeourlFlag = overrideSeourlFlag;
    }

    /**
     * 
     * @return
     *     The activenetTopGraphic
     */
    // @JsonProperty("activenetTopGraphic")
    public ActivenetTopGraphic getActivenetTopGraphic() {
        return activenetTopGraphic;
    }

    /**
     * 
     * @param activenetTopGraphic
     *     The activenetTopGraphic
     */
    // @JsonProperty("activenetTopGraphic")
    public void setActivenetTopGraphic(ActivenetTopGraphic activenetTopGraphic) {
        this.activenetTopGraphic = activenetTopGraphic;
    }

    /**
     * 
     * @return
     *     The evergreenAssetFlag
     */
    // @JsonProperty("evergreenAssetFlag")
    public String getEvergreenAssetFlag() {
        return evergreenAssetFlag;
    }

    /**
     * 
     * @param evergreenAssetFlag
     *     The evergreenAssetFlag
     */
    // @JsonProperty("evergreenAssetFlag")
    public void setEvergreenAssetFlag(String evergreenAssetFlag) {
        this.evergreenAssetFlag = evergreenAssetFlag;
    }

    /**
     * 
     * @return
     *     The evergreenAssets
     */
    // @JsonProperty("evergreenAssets")
    public List<Object> getEvergreenAssets() {
        return evergreenAssets;
    }

    /**
     * 
     * @param evergreenAssets
     *     The evergreenAssets
     */
    // @JsonProperty("evergreenAssets")
    public void setEvergreenAssets(List<Object> evergreenAssets) {
        this.evergreenAssets = evergreenAssets;
    }

    /**
     * 
     * @return
     *     The searchScore
     */
    // @JsonProperty("searchScore")
    public Double getSearchScore() {
        return searchScore;
    }

    /**
     * 
     * @param searchScore
     *     The searchScore
     */
    // @JsonProperty("searchScore")
    public void setSearchScore(Double searchScore) {
        this.searchScore = searchScore;
    }

    /**
     * 
     * @return
     *     The assetVersion
     */
    // @JsonProperty("assetVersion")
    public Integer getAssetVersion() {
        return assetVersion;
    }

    /**
     * 
     * @param assetVersion
     *     The assetVersion
     */
    // @JsonProperty("assetVersion")
    public void setAssetVersion(Integer assetVersion) {
        this.assetVersion = assetVersion;
    }

    /**
     * 
     * @return
     *     The salesStatus
     */
    // @JsonProperty("salesStatus")
    public String getSalesStatus() {
        return salesStatus;
    }

    /**
     * 
     * @param salesStatus
     *     The salesStatus
     */
    // @JsonProperty("salesStatus")
    public void setSalesStatus(String salesStatus) {
        this.salesStatus = salesStatus;
    }

    /**
     * 
     * @return
     *     The retryDate
     */
    // @JsonProperty("retryDate")
    public String getRetryDate() {
        return retryDate;
    }

    /**
     * 
     * @param retryDate
     *     The retryDate
     */
    // @JsonProperty("retryDate")
    public void setRetryDate(String retryDate) {
        this.retryDate = retryDate;
    }

    /**
     * 
     * @return
     *     The retryCounter
     */
    // @JsonProperty("retryCounter")
    public String getRetryCounter() {
        return retryCounter;
    }

    /**
     * 
     * @param retryCounter
     *     The retryCounter
     */
    // @JsonProperty("retryCounter")
    public void setRetryCounter(String retryCounter) {
        this.retryCounter = retryCounter;
    }

    /**
     * 
     * @return
     *     The socialMedia
     */
    // @JsonProperty("socialMedia")
    public List<Object> getSocialMedia() {
        return socialMedia;
    }

    /**
     * 
     * @param socialMedia
     *     The socialMedia
     */
    // @JsonProperty("socialMedia")
    public void setSocialMedia(List<Object> socialMedia) {
        this.socialMedia = socialMedia;
    }

    /**
     * 
     * @return
     *     The assetReferences
     */
    // @JsonProperty("assetReferences")
    public List<Object> getAssetReferences() {
        return assetReferences;
    }

    /**
     * 
     * @param assetReferences
     *     The assetReferences
     */
    // @JsonProperty("assetReferences")
    public void setAssetReferences(List<Object> assetReferences) {
        this.assetReferences = assetReferences;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

	@Override
	public String toString() {
		return "AmpActiveEvent [assetStatus=" + assetStatus + ", organization="
				+ organization + ", place=" + place + ", evergreenParentAsset="
				+ evergreenParentAsset + ", sourceSystem=" + sourceSystem
				+ ", assetRootAsset=" + assetRootAsset + ", assetParentAsset="
				+ assetParentAsset + ", market=" + market + ", assetGuid="
				+ assetGuid + ", assetName=" + assetName + ", assetDsc="
				+ assetDsc + ", alternateName=" + alternateName + ", timezone="
				+ timezone + ", localTimeZoneId=" + localTimeZoneId
				+ ", timezoneAbb=" + timezoneAbb + ", timezoneName="
				+ timezoneName + ", currencyCd=" + currencyCd + ", localeCd="
				+ localeCd + ", salesStartDate=" + salesStartDate
				+ ", salesEndDate=" + salesEndDate + ", urlAdr=" + urlAdr
				+ ", detailPageTemplateId=" + detailPageTemplateId
				+ ", preferredUrlAdr=" + preferredUrlAdr + ", logoUrlAdr="
				+ logoUrlAdr + ", activityStartDate=" + activityStartDate
				+ ", activityEndDate=" + activityEndDate + ", donationUrlAdr="
				+ donationUrlAdr + ", teamUrlAdr=" + teamUrlAdr
				+ ", homePageUrlAdr=" + homePageUrlAdr
				+ ", registrationUrlAdr=" + registrationUrlAdr
				+ ", registrantSearchUrlAdr=" + registrantSearchUrlAdr
				+ ", regReqMinAge=" + regReqMinAge + ", regReqMaxAge="
				+ regReqMaxAge + ", regReqGenderCd=" + regReqGenderCd
				+ ", resultsUrlAdr=" + resultsUrlAdr + ", contactName="
				+ contactName + ", contactEmailAdr=" + contactEmailAdr
				+ ", contactPhone=" + contactPhone + ", contactTxt="
				+ contactTxt + ", showContact=" + showContact + ", sorId="
				+ sorId + ", sorCreateDtm=" + sorCreateDtm
				+ ", sorCreateUserId=" + sorCreateUserId + ", authorName="
				+ authorName + ", childIndex=" + childIndex + ", publishDate="
				+ publishDate + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", activityRecurrences="
				+ activityRecurrences + ", assetQuantity=" + assetQuantity
				+ ", assetLegacyData=" + assetLegacyData + ", assetTags="
				+ assetTags + ", assetAttributes=" + assetAttributes
				+ ", assetPrices=" + assetPrices + ", assetDescriptions="
				+ assetDescriptions + ", assetChannels=" + assetChannels
				+ ", assetMediaTypes=" + assetMediaTypes + ", assetImages="
				+ assetImages + ", assetTopics=" + assetTopics
				+ ", assetCategories=" + assetCategories
				+ ", assetMetaInterests=" + assetMetaInterests
				+ ", assetInterests=" + assetInterests + ", assetDeals="
				+ assetDeals + ", assetComponents=" + assetComponents
				+ ", assetServiceHostName=" + assetServiceHostName
				+ ", componentInUrlAdr=" + componentInUrlAdr
				+ ", assetSeoUrls=" + assetSeoUrls + ", overrideSeourlFlag="
				+ overrideSeourlFlag + ", activenetTopGraphic="
				+ activenetTopGraphic + ", evergreenAssetFlag="
				+ evergreenAssetFlag + ", evergreenAssets=" + evergreenAssets
				+ ", searchScore=" + searchScore + ", assetVersion="
				+ assetVersion + ", salesStatus=" + salesStatus
				+ ", retryDate=" + retryDate + ", retryCounter=" + retryCounter
				+ ", socialMedia=" + socialMedia + ", assetReferences="
				+ assetReferences + ", additionalProperties="
				+ additionalProperties + "]";
	}

}
