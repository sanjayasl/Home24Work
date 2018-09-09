package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

public class Delivery {

    @SerializedName("deliveredBy")
    private String deliveredBy;
    @SerializedName("terms")
    private String terms;
    @SerializedName("text")
    private String text;
    @SerializedName("time")
    private Time time;
    @SerializedName("type")
    private String type;
    @SerializedName("typeLabelLink")
    private String typeLabelLink;

    public Delivery(String deliveredBy, String terms, String text, Time time, String type, String typeLabelLink) {
        this.deliveredBy = deliveredBy;
        this.terms = terms;
        this.text = text;
        this.time = time;
        this.type = type;
        this.typeLabelLink = typeLabelLink;
    }

}
