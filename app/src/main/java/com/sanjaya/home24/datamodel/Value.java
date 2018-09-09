package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("id")
    private String id;
    @SerializedName("priority")
    private int priority;
    @SerializedName("title")
    private String title;
    @SerializedName("_metadata")
    private Metadata metadata;

    public Value(String id, int priority, String title, Metadata metadata) {
        this.id = id;
        this.priority = priority;
        this.title = title;
        this.metadata = metadata;
    }

}
