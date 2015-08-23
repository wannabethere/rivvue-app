
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
    "imageUrlAdr",
    "imageName",
    "linkUrl",
    "linkTarget",
    "imageCaptionTxt",
    "imageType"
})
public class AssetImage {

    @JsonProperty("imageUrlAdr")
    private String imageUrlAdr;
    @JsonProperty("imageName")
    private String imageName;
    @JsonProperty("linkUrl")
    private String linkUrl;
    @JsonProperty("linkTarget")
    private String linkTarget;
    @JsonProperty("imageCaptionTxt")
    private String imageCaptionTxt;
    @JsonProperty("imageType")
    private String imageType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
     *     The imageName
     */
    // @JsonProperty("imageName")
    public String getImageName() {
        return imageName;
    }

    /**
     * 
     * @param imageName
     *     The imageName
     */
    // @JsonProperty("imageName")
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     * 
     * @return
     *     The linkUrl
     */
    // @JsonProperty("linkUrl")
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 
     * @param linkUrl
     *     The linkUrl
     */
    // @JsonProperty("linkUrl")
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * 
     * @return
     *     The linkTarget
     */
    // @JsonProperty("linkTarget")
    public String getLinkTarget() {
        return linkTarget;
    }

    /**
     * 
     * @param linkTarget
     *     The linkTarget
     */
    // @JsonProperty("linkTarget")
    public void setLinkTarget(String linkTarget) {
        this.linkTarget = linkTarget;
    }

    /**
     * 
     * @return
     *     The imageCaptionTxt
     */
    // @JsonProperty("imageCaptionTxt")
    public String getImageCaptionTxt() {
        return imageCaptionTxt;
    }

    /**
     * 
     * @param imageCaptionTxt
     *     The imageCaptionTxt
     */
    // @JsonProperty("imageCaptionTxt")
    public void setImageCaptionTxt(String imageCaptionTxt) {
        this.imageCaptionTxt = imageCaptionTxt;
    }

    /**
     * 
     * @return
     *     The imageType
     */
    // @JsonProperty("imageType")
    public String getImageType() {
        return imageType;
    }

    /**
     * 
     * @param imageType
     *     The imageType
     */
    // @JsonProperty("imageType")
    public void setImageType(String imageType) {
        this.imageType = imageType;
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
