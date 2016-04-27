package com.example.data.entity.pojos;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a630703 on 22/04/2016.
 */
public class ComicPojo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("digitalId")
    @Expose
    private Integer digitalId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("issueNumber")
    @Expose
    private Integer issueNumber;
    @SerializedName("variantDescription")
    @Expose
    private String variantDescription;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("isbn")
    @Expose
    private String isbn;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("diamondCode")
    @Expose
    private String diamondCode;
    @SerializedName("ean")
    @Expose
    private String ean;
    @SerializedName("issn")
    @Expose
    private String issn;
    @SerializedName("format")
    @Expose
    private String format;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    @SerializedName("urls")
    @Expose
    private List<UrlPojo> urls = new ArrayList<UrlPojo>();
    @SerializedName("dates")
    @Expose
    private List<DatePojo> dates = new ArrayList<DatePojo>();
    @SerializedName("prices")
    @Expose
    private List<PricePojo> prices = new ArrayList<PricePojo>();
    @SerializedName("thumbnail")
    @Expose
    private ThumbnailPojo thumbnail;
    @SerializedName("creators")
    @Expose
    private CreatorsPojo creators;
    @SerializedName("characters")
    @Expose
    private CharactersPojo characters;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The digitalId
     */
    public Integer getDigitalId() {
        return digitalId;
    }

    /**
     *
     * @param digitalId
     * The digitalId
     */
    public void setDigitalId(Integer digitalId) {
        this.digitalId = digitalId;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The issueNumber
     */
    public Integer getIssueNumber() {
        return issueNumber;
    }

    /**
     *
     * @param issueNumber
     * The issueNumber
     */
    public void setIssueNumber(Integer issueNumber) {
        this.issueNumber = issueNumber;
    }

    /**
     *
     * @return
     * The variantDescription
     */
    public String getVariantDescription() {
        return variantDescription;
    }

    /**
     *
     * @param variantDescription
     * The variantDescription
     */
    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     * The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     *
     * @return
     * The isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     *
     * @param isbn
     * The isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     *
     * @return
     * The upc
     */
    public String getUpc() {
        return upc;
    }

    /**
     *
     * @param upc
     * The upc
     */
    public void setUpc(String upc) {
        this.upc = upc;
    }

    /**
     *
     * @return
     * The diamondCode
     */
    public String getDiamondCode() {
        return diamondCode;
    }

    /**
     *
     * @param diamondCode
     * The diamondCode
     */
    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    /**
     *
     * @return
     * The ean
     */
    public String getEan() {
        return ean;
    }

    /**
     *
     * @param ean
     * The ean
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     *
     * @return
     * The issn
     */
    public String getIssn() {
        return issn;
    }

    /**
     *
     * @param issn
     * The issn
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    /**
     *
     * @return
     * The format
     */
    public String getFormat() {
        return format;
    }

    /**
     *
     * @param format
     * The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     *
     * @return
     * The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     *
     * @param pageCount
     * The pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     *
     * @return
     * The resourceURI
     */
    public String getResourceURI() {
        return resourceURI;
    }

    /**
     *
     * @param resourceURI
     * The resourceURI
     */
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    /**
     *
     * @return
     * The urls
     */
    public List<UrlPojo> getUrls() {
        return urls;
    }

    /**
     *
     * @param urls
     * The urls
     */
    public void setUrls(List<UrlPojo> urls) {
        this.urls = urls;
    }

    /**
     *
     * @return
     * The dates
     */
    public List<DatePojo> getDates() {
        return dates;
    }

    /**
     *
     * @param dates
     * The dates
     */
    public void setDates(List<DatePojo> dates) {
        this.dates = dates;
    }

    /**
     *
     * @return
     * The prices
     */
    public List<PricePojo> getPrices() {
        return prices;
    }

    /**
     *
     * @param prices
     * The prices
     */
    public void setPrices(List<PricePojo> prices) {
        this.prices = prices;
    }

    /**
     *
     * @return
     * The thumbnail
     */
    public ThumbnailPojo getThumbnail() {
        return thumbnail;
    }

    /**
     *
     * @param thumbnail
     * The thumbnail
     */
    public void setThumbnail(ThumbnailPojo thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     *
     * @return
     * The creators
     */
    public CreatorsPojo getCreators() {
        return creators;
    }

    /**
     *
     * @param creators
     * The creators
     */
    public void setCreators(CreatorsPojo creators) {
        this.creators = creators;
    }

    /**
     *
     * @return
     * The characters
     */
    public CharactersPojo getCharacters() {
        return characters;
    }

    /**
     *
     * @param characters
     * The characters
     */
    public void setCharacters(CharactersPojo characters) {
        this.characters = characters;
    }

}
