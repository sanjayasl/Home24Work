package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Brand {

    @SerializedName("id")
    private String id;
    @SerializedName("logos")
    private ArrayList<String> logos;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;

    public Brand(String id, ArrayList<String> logos, String title, String description) {
        this.id = id;
        this.logos = logos;
        this.title = title;
        this.description = description;
    }

}
