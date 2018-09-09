package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Links {

    @SerializedName("self")
    public Self self;

    @SerializedName("next")
    public Next next;

    @SerializedName("webShopUrl")
    public WebShop webShop;

    @SerializedName("articles")
    public ArrayList<Article> articles;

    public Links(Self self, WebShop webShop) {
        this.self = self;
        this.webShop = webShop;
    }

    public Links(Self self, Next next, ArrayList<Article> articles) {
        this.self = self;
        this.next = next;
        this.articles = articles;
    }

    public static class Self {

        @SerializedName("href")
        private String href;

        public Self(String href) {
            this.href = href;
        }
    }

    public static class Next {

        @SerializedName("href")
        private String href;

        public Next(String href) {
            this.href = href;
        }
    }

    public static class WebShop {

        @SerializedName("href")
        private String href;

        public WebShop(String href) {
            this.href = href;
        }
    }

}