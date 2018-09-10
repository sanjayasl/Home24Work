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

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
