package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("mimeType")
    private String mimeType;
    @SerializedName("priority")
    private int priority;
    @SerializedName("size")
    private String size;
    @SerializedName("type")
    private String type;
    @SerializedName("uri")
    private String uri;

    public Media(String mimeType, int priority, String size, String type, String uri) {
        this.mimeType = mimeType;
        this.priority = priority;
        this.size = size;
        this.type = type;
        this.uri = uri;
    }

}
