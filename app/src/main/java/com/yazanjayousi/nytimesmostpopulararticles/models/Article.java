package com.yazanjayousi.nytimesmostpopulararticles.models;

public class Article {

    private String url;
  
    private String adxKeywords;

    private String section;

    private Long id;

    private String byline;

    private String title;

    private String _abstract;

    private String publishedDate;

    private String mediaType;

    private String caption;

    private String thumpUrl;

    private String imageUrl;

    private int position;

    public Article() {
    }

    public Article(int position,String url, String adxKeywords, String section, Long id, String byline, String title,
                   String _abstract, String publishedDate, String mediaType, String caption, String thumpUrl, String imageUrl) {
        this.url = url;
        this.adxKeywords = adxKeywords;
        this.section = section;
        this.id = id;
        this.byline = byline;
        this.title = title;
        this._abstract = _abstract;
        this.publishedDate = publishedDate;
        this.mediaType = mediaType;
        this.caption = caption;
        this.thumpUrl = thumpUrl;
        this.imageUrl = imageUrl;
        this.position=position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getThumpUrl() {
        return thumpUrl;
    }

    public void setThumpUrl(String thumpUrl) {
        this.thumpUrl = thumpUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAdxKeywords() {
        return adxKeywords;
    }

    public void setAdxKeywords(String adxKeywords) {
        this.adxKeywords = adxKeywords;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String get_abstract() {
        return _abstract;
    }

    public void set_abstract(String _abstract) {
        this._abstract = _abstract;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url='" + url + '\'' +
                ", adxKeywords='" + adxKeywords + '\'' +
                ", section='" + section + '\'' +
                ", id=" + id +
                ", byline='" + byline + '\'' +
                ", title='" + title + '\'' +
                ", _abstract='" + _abstract + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", caption='" + caption + '\'' +
                ", thumpUrl='" + thumpUrl + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
