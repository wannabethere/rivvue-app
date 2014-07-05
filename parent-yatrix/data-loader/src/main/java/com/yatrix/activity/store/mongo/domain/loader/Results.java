
package com.yatrix.activity.store.mongo.domain.loader;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_EMPTY)

public class Results{
   	//private List<ActivenetTopGraphic> activenetTopGraphic;
   	private String activityEndDate;
   	//private List<ActivityRecurrences> activityRecurrences;
   	private String activityStartDate;
   	private String alternateName;
   	private List<AssetAttributes> assetAttributes;
   	private List<AssetCategories> assetCategories;
   	private List<AssetChannels> assetChannels;
   	private List<AssetComponents> assetComponents;
   	//private List<AssetDeals> assetDeals;
   	private List<AssetDescriptions> assetDescriptions;
   	private String assetDsc;
   	private String assetGuid;
   	private List<AssetImages> assetImages;
   	//private List<AssetInterests> assetInterests;
   	private AssetLegacyData assetLegacyData;
   	private List<AssetMediaTypes> assetMediaTypes;
   	//private List<AssetMetaInterests> assetMetaInterests;
   	private String assetName;
   	private AssetParentAsset assetParentAsset;
   	private List<AssetPrices> assetPrices;
   	//private List<AssetQuantity> assetQuantity;
   	private AssetRootAsset assetRootAsset;
   	private List<AssetSeoUrls> assetSeoUrls;
   	private String assetServiceHostName;
   	private AssetStatus assetStatus;
   	private List<AssetTags> assetTags;
   	private List<AssetTopics> assetTopics;
   	private Number assetVersion;
   	private String authorName;
   	private String childIndex;
   	//private List<ComponentInUrlAdr> componentInUrlAdr;
   	private String contactEmailAdr;
   	private String contactName;
   	private String contactPhone;
   	private String contactTxt;
   	private String createdDate;
   	private String currencyCd;
   	private String detailPageTemplateId;
   	private String donationUrlAdr;
   	private String evergreenAssetFlag;
   	//private List<EvergreenAssets> evergreenAssets;
   	//private List<EvergreenParentAsset> evergreenParentAsset;
   	private String homePageUrlAdr;
   	private String localTimeZoneId;
   	private String localeCd;
   	private String logoUrlAdr;
   	private Market market;
   	private String modifiedDate;
   	private Organization organization;
   	private String overrideSeourlFlag;
   	private Place place;
   	private String preferredUrlAdr;
   	private String publishDate;
   	private String regReqGenderCd;
   	private String regReqMaxAge;
   	private String regReqMinAge;
   	private String registrantSearchUrlAdr;
   	private String registrationUrlAdr;
   	private String resultsUrlAdr;
   	private String salesEndDate;
   	private String salesStartDate;
   	private String salesStatus;
   	private Number searchScore;
   	private String showContact;
   	private String sorCreateDtm;
   	private String sorCreateUserId;
   	private String sorId;
   	private SourceSystem sourceSystem;
   	private String teamUrlAdr;
   	private String timezone;
   	private String timezoneAbb;
   	private String timezoneName;
   	private String urlAdr;

// 	public List<ActivenetTopGraphic> getActivenetTopGraphic(){
//		return this.activenetTopGraphic;
//	}
//	public void setActivenetTopGraphic(List<ActivenetTopGraphic> activenetTopGraphic){
//		this.activenetTopGraphic = activenetTopGraphic;
//	}
 	public String getActivityEndDate(){
		return this.activityEndDate;
	}
	public void setActivityEndDate(String activityEndDate){
		this.activityEndDate = activityEndDate;
	}
// 	public List<ActivityRecurrences> getActivityRecurrences(){
//		return this.activityRecurrences;
//	}
//	public void setActivityRecurrences(List<ActivityRecurrences> activityRecurrences){
//		this.activityRecurrences = activityRecurrences;
//	}
 	public String getActivityStartDate(){
		return this.activityStartDate;
	}
	public void setActivityStartDate(String activityStartDate){
		this.activityStartDate = activityStartDate;
	}
 	public String getAlternateName(){
		return this.alternateName;
	}
	public void setAlternateName(String alternateName){
		this.alternateName = alternateName;
	}
 	public List<AssetAttributes> getAssetAttributes(){
		return this.assetAttributes;
	}
	public void setAssetAttributes(List<AssetAttributes> assetAttributes){
		this.assetAttributes = assetAttributes;
	}
 	public List<AssetCategories> getAssetCategories(){
		return this.assetCategories;
	}
	public void setAssetCategories(List<AssetCategories> assetCategories){
		this.assetCategories = assetCategories;
	}
 	public List<AssetChannels> getAssetChannels(){
		return this.assetChannels;
	}
	public void setAssetChannels(List<AssetChannels> assetChannels){
		this.assetChannels = assetChannels;
	}
 	public List<AssetComponents> getAssetComponents(){
		return this.assetComponents;
	}
	public void setAssetComponents(List<AssetComponents> assetComponents){
		this.assetComponents = assetComponents;
	}
// 	public List<AssetDeals> getAssetDeals(){
//		return this.assetDeals;
//	}
//	public void setAssetDeals(List<AssetDeals> assetDeals){
//		this.assetDeals = assetDeals;
//	}
 	public List<AssetDescriptions> getAssetDescriptions(){
		return this.assetDescriptions;
	}
	public void setAssetDescriptions(List<AssetDescriptions> assetDescriptions){
		this.assetDescriptions = assetDescriptions;
	}
 	public String getAssetDsc(){
		return this.assetDsc;
	}
	public void setAssetDsc(String assetDsc){
		this.assetDsc = assetDsc;
	}
 	public String getAssetGuid(){
		return this.assetGuid;
	}
	public void setAssetGuid(String assetGuid){
		this.assetGuid = assetGuid;
	}
 	public List<AssetImages> getAssetImages(){
		return this.assetImages;
	}
	public void setAssetImages(List<AssetImages> assetImages){
		this.assetImages = assetImages;
	}
// 	public List<AssetInterests> getAssetInterests(){
//		return this.assetInterests;
//	}
//	public void setAssetInterests(List<AssetInterests> assetInterests){
//		this.assetInterests = assetInterests;
//	}
 	public AssetLegacyData getAssetLegacyData(){
		return this.assetLegacyData;
	}
	public void setAssetLegacyData(AssetLegacyData assetLegacyData){
		this.assetLegacyData = assetLegacyData;
	}
 	public List<AssetMediaTypes> getAssetMediaTypes(){
		return this.assetMediaTypes;
	}
	public void setAssetMediaTypes(List<AssetMediaTypes> assetMediaTypes){
		this.assetMediaTypes = assetMediaTypes;
	}
// 	public List<AssetMetaInterests> getAssetMetaInterests(){
//		return this.assetMetaInterests;
//	}
//	public void setAssetMetaInterests(List<AssetMetaInterests> assetMetaInterests){
//		this.assetMetaInterests = assetMetaInterests;
//	}
 	public String getAssetName(){
		return this.assetName;
	}
	public void setAssetName(String assetName){
		this.assetName = assetName;
	}
 	public AssetParentAsset getAssetParentAsset(){
		return this.assetParentAsset;
	}
	public void setAssetParentAsset(AssetParentAsset assetParentAsset){
		this.assetParentAsset = assetParentAsset;
	}
 	public List<AssetPrices> getAssetPrices(){
		return this.assetPrices;
	}
	public void setAssetPrices(List<AssetPrices> assetPrices){
		this.assetPrices = assetPrices;
	}
// 	public List<AssetQuantity> getAssetQuantity(){
//		return this.assetQuantity;
//	}
//	public void setAssetQuantity(List<AssetQuantity> assetQuantity){
//		this.assetQuantity = assetQuantity;
//	}
 	public AssetRootAsset getAssetRootAsset(){
		return this.assetRootAsset;
	}
	public void setAssetRootAsset(AssetRootAsset assetRootAsset){
		this.assetRootAsset = assetRootAsset;
	}
 	public List<AssetSeoUrls> getAssetSeoUrls(){
		return this.assetSeoUrls;
	}
	public void setAssetSeoUrls(List<AssetSeoUrls> assetSeoUrls){
		this.assetSeoUrls = assetSeoUrls;
	}
 	public String getAssetServiceHostName(){
		return this.assetServiceHostName;
	}
	public void setAssetServiceHostName(String assetServiceHostName){
		this.assetServiceHostName = assetServiceHostName;
	}
 	public AssetStatus getAssetStatus(){
		return this.assetStatus;
	}
	public void setAssetStatus(AssetStatus assetStatus){
		this.assetStatus = assetStatus;
	}
 	public List<AssetTags> getAssetTags(){
		return this.assetTags;
	}
	public void setAssetTags(List<AssetTags> assetTags){
		this.assetTags = assetTags;
	}
 	public List<AssetTopics> getAssetTopics(){
		return this.assetTopics;
	}
	public void setAssetTopics(List<AssetTopics> assetTopics){
		this.assetTopics = assetTopics;
	}
 	public Number getAssetVersion(){
		return this.assetVersion;
	}
	public void setAssetVersion(Number assetVersion){
		this.assetVersion = assetVersion;
	}
 	public String getAuthorName(){
		return this.authorName;
	}
	public void setAuthorName(String authorName){
		this.authorName = authorName;
	}
 	public String getChildIndex(){
		return this.childIndex;
	}
	public void setChildIndex(String childIndex){
		this.childIndex = childIndex;
	}
// 	public List<ComponentInUrlAdr> getComponentInUrlAdr(){
//		return this.componentInUrlAdr;
//	}
//	public void setComponentInUrlAdr(List<ComponentInUrlAdr> componentInUrlAdr){
//		this.componentInUrlAdr = componentInUrlAdr;
//	}
 	public String getContactEmailAdr(){
		return this.contactEmailAdr;
	}
	public void setContactEmailAdr(String contactEmailAdr){
		this.contactEmailAdr = contactEmailAdr;
	}
 	public String getContactName(){
		return this.contactName;
	}
	public void setContactName(String contactName){
		this.contactName = contactName;
	}
 	public String getContactPhone(){
		return this.contactPhone;
	}
	public void setContactPhone(String contactPhone){
		this.contactPhone = contactPhone;
	}
 	public String getContactTxt(){
		return this.contactTxt;
	}
	public void setContactTxt(String contactTxt){
		this.contactTxt = contactTxt;
	}
 	public String getCreatedDate(){
		return this.createdDate;
	}
	public void setCreatedDate(String createdDate){
		this.createdDate = createdDate;
	}
 	public String getCurrencyCd(){
		return this.currencyCd;
	}
	public void setCurrencyCd(String currencyCd){
		this.currencyCd = currencyCd;
	}
 	public String getDetailPageTemplateId(){
		return this.detailPageTemplateId;
	}
	public void setDetailPageTemplateId(String detailPageTemplateId){
		this.detailPageTemplateId = detailPageTemplateId;
	}
 	public String getDonationUrlAdr(){
		return this.donationUrlAdr;
	}
	public void setDonationUrlAdr(String donationUrlAdr){
		this.donationUrlAdr = donationUrlAdr;
	}
 	public String getEvergreenAssetFlag(){
		return this.evergreenAssetFlag;
	}
	public void setEvergreenAssetFlag(String evergreenAssetFlag){
		this.evergreenAssetFlag = evergreenAssetFlag;
	}
// 	public List<EvergreenAssets> getEvergreenAssets(){
//		return this.evergreenAssets;
//	}
//	public void setEvergreenAssets(List<EvergreenAssets> evergreenAssets){
//		this.evergreenAssets = evergreenAssets;
//	}
// 	public List<EvergreenParentAsset> getEvergreenParentAsset(){
//		return this.evergreenParentAsset;
//	}
//	public void setEvergreenParentAsset(List<EvergreenParentAsset> evergreenParentAsset){
//		this.evergreenParentAsset = evergreenParentAsset;
//	}
 	public String getHomePageUrlAdr(){
		return this.homePageUrlAdr;
	}
	public void setHomePageUrlAdr(String homePageUrlAdr){
		this.homePageUrlAdr = homePageUrlAdr;
	}
 	public String getLocalTimeZoneId(){
		return this.localTimeZoneId;
	}
	public void setLocalTimeZoneId(String localTimeZoneId){
		this.localTimeZoneId = localTimeZoneId;
	}
 	public String getLocaleCd(){
		return this.localeCd;
	}
	public void setLocaleCd(String localeCd){
		this.localeCd = localeCd;
	}
 	public String getLogoUrlAdr(){
		return this.logoUrlAdr;
	}
	public void setLogoUrlAdr(String logoUrlAdr){
		this.logoUrlAdr = logoUrlAdr;
	}
 	public Market getMarket(){
		return this.market;
	}
	public void setMarket(Market market){
		this.market = market;
	}
 	public String getModifiedDate(){
		return this.modifiedDate;
	}
	public void setModifiedDate(String modifiedDate){
		this.modifiedDate = modifiedDate;
	}
 	public Organization getOrganization(){
		return this.organization;
	}
	public void setOrganization(Organization organization){
		this.organization = organization;
	}
 	public String getOverrideSeourlFlag(){
		return this.overrideSeourlFlag;
	}
	public void setOverrideSeourlFlag(String overrideSeourlFlag){
		this.overrideSeourlFlag = overrideSeourlFlag;
	}
 	public Place getPlace(){
		return this.place;
	}
	public void setPlace(Place place){
		this.place = place;
	}
 	public String getPreferredUrlAdr(){
		return this.preferredUrlAdr;
	}
	public void setPreferredUrlAdr(String preferredUrlAdr){
		this.preferredUrlAdr = preferredUrlAdr;
	}
 	public String getPublishDate(){
		return this.publishDate;
	}
	public void setPublishDate(String publishDate){
		this.publishDate = publishDate;
	}
 	public String getRegReqGenderCd(){
		return this.regReqGenderCd;
	}
	public void setRegReqGenderCd(String regReqGenderCd){
		this.regReqGenderCd = regReqGenderCd;
	}
 	public String getRegReqMaxAge(){
		return this.regReqMaxAge;
	}
	public void setRegReqMaxAge(String regReqMaxAge){
		this.regReqMaxAge = regReqMaxAge;
	}
 	public String getRegReqMinAge(){
		return this.regReqMinAge;
	}
	public void setRegReqMinAge(String regReqMinAge){
		this.regReqMinAge = regReqMinAge;
	}
 	public String getRegistrantSearchUrlAdr(){
		return this.registrantSearchUrlAdr;
	}
	public void setRegistrantSearchUrlAdr(String registrantSearchUrlAdr){
		this.registrantSearchUrlAdr = registrantSearchUrlAdr;
	}
 	public String getRegistrationUrlAdr(){
		return this.registrationUrlAdr;
	}
	public void setRegistrationUrlAdr(String registrationUrlAdr){
		this.registrationUrlAdr = registrationUrlAdr;
	}
 	public String getResultsUrlAdr(){
		return this.resultsUrlAdr;
	}
	public void setResultsUrlAdr(String resultsUrlAdr){
		this.resultsUrlAdr = resultsUrlAdr;
	}
 	public String getSalesEndDate(){
		return this.salesEndDate;
	}
	public void setSalesEndDate(String salesEndDate){
		this.salesEndDate = salesEndDate;
	}
 	public String getSalesStartDate(){
		return this.salesStartDate;
	}
	public void setSalesStartDate(String salesStartDate){
		this.salesStartDate = salesStartDate;
	}
 	public String getSalesStatus(){
		return this.salesStatus;
	}
	public void setSalesStatus(String salesStatus){
		this.salesStatus = salesStatus;
	}
 	public Number getSearchScore(){
		return this.searchScore;
	}
	public void setSearchScore(Number searchScore){
		this.searchScore = searchScore;
	}
 	public String getShowContact(){
		return this.showContact;
	}
	public void setShowContact(String showContact){
		this.showContact = showContact;
	}
 	public String getSorCreateDtm(){
		return this.sorCreateDtm;
	}
	public void setSorCreateDtm(String sorCreateDtm){
		this.sorCreateDtm = sorCreateDtm;
	}
 	public String getSorCreateUserId(){
		return this.sorCreateUserId;
	}
	public void setSorCreateUserId(String sorCreateUserId){
		this.sorCreateUserId = sorCreateUserId;
	}
 	public String getSorId(){
		return this.sorId;
	}
	public void setSorId(String sorId){
		this.sorId = sorId;
	}
 	public SourceSystem getSourceSystem(){
		return this.sourceSystem;
	}
	public void setSourceSystem(SourceSystem sourceSystem){
		this.sourceSystem = sourceSystem;
	}
 	public String getTeamUrlAdr(){
		return this.teamUrlAdr;
	}
	public void setTeamUrlAdr(String teamUrlAdr){
		this.teamUrlAdr = teamUrlAdr;
	}
 	public String getTimezone(){
		return this.timezone;
	}
	public void setTimezone(String timezone){
		this.timezone = timezone;
	}
 	public String getTimezoneAbb(){
		return this.timezoneAbb;
	}
	public void setTimezoneAbb(String timezoneAbb){
		this.timezoneAbb = timezoneAbb;
	}
 	public String getTimezoneName(){
		return this.timezoneName;
	}
	public void setTimezoneName(String timezoneName){
		this.timezoneName = timezoneName;
	}
 	public String getUrlAdr(){
		return this.urlAdr;
	}
	public void setUrlAdr(String urlAdr){
		this.urlAdr = urlAdr;
	}
}
