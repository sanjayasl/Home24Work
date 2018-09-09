package com.sanjaya.home24.api;

import com.google.gson.annotations.SerializedName;
import com.sanjaya.home24.datamodel.Embedded;
import com.sanjaya.home24.datamodel.Links;

public class ArticleResponse {

    @SerializedName("articlesCount")
    private int articlesCount;

    @SerializedName("resourceType")
    private String resourceType;

    @SerializedName("_embedded")
    private Embedded embedded;

    @SerializedName("_links")
    private Links links;

    public ArticleResponse(int articlesCount, String resourceType, Embedded embedded, Links links) {
        this.articlesCount = articlesCount;
        this.resourceType = resourceType;
        this.embedded = embedded;
        this.links = links;
    }

    public int getArticlesCount() {
        return articlesCount;
    }

    public void setArticlesCount(int articlesCount) {
        this.articlesCount = articlesCount;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Embedded getEmbedded() {
        return embedded;
    }

    public void setEmbedded(Embedded embedded) {
        this.embedded = embedded;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}
