package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Filter {

    @SerializedName("id")
    private String id;
    @SerializedName("priority")
    private int priority;
    @SerializedName("title")
    private String title;
    @SerializedName("values")
    private ArrayList<Value> values;
    @SerializedName("_metadata")
    private Metadata metadata;

    public Filter(String id, int priority, String title, ArrayList<Value> values, Metadata metadata) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.values = values;
        this.metadata = metadata;
    }

}
