package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

public class Time {

    @SerializedName("amount")
    private String amount;
    @SerializedName("renderAs")
    private String renderAs;
    @SerializedName("units")
    private String units;

    public Time(String amount, String renderAs, String units) {
        this.amount = amount;
        this.renderAs = renderAs;
        this.units = units;
    }

}
