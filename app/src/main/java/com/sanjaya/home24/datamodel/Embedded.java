package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Embedded {

    @SerializedName("articles")
    private ArrayList<Article> articles;
    @SerializedName("filters")
    private ArrayList<Filter> filters;

    public Embedded(ArrayList<Article> articles, ArrayList<Filter> filters) {
        this.articles = articles;
        this.filters = filters;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public ArrayList<Filter> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<Filter> filters) {
        this.filters = filters;
    }

}
