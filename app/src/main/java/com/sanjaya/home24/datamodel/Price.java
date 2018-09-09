package com.sanjaya.home24.datamodel;

import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("amount")
    private String amount;
    @SerializedName("currency")
    private String currency;
    @SerializedName("isRecommendedRetailPrice")
    private boolean isRecommendedRetailPrice;

    public Price(String amount, String currency, boolean isRecommendedRetailPrice) {
        this.amount = amount;
        this.currency = currency;
        this.isRecommendedRetailPrice = isRecommendedRetailPrice;
    }

}
