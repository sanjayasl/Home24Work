package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

public class Metadata {

    @SerializedName("type")
    private String type;

    public Metadata(String type) {
        this.type = type;
    }

}
