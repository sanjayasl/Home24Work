package com.sanjaya.home24.datamodel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.sanjaya.home24.db.Home24TypeConverters;

import java.util.ArrayList;

@Entity(primaryKeys = {"sku"})
@TypeConverters(Home24TypeConverters.class)
public class Article {

    @SerializedName("assemblyService")
    private String assemblyService;
    @SerializedName("availability")
    private String availability;
//    @SerializedName("brand")
//    private Brand brand;
//    @SerializedName("delivery")
//    private Delivery delivery;
    @SerializedName("description")
    private String description;
    @SerializedName("energyClass")
    private String energyClass;
    @SerializedName("manufacturePrice")
    private String manufacturePrice;
//    @SerializedName("media")
//    private ArrayList<Media> media;
//    @SerializedName("prevPrice")
//    private Price prevPrice;
//    @SerializedName("price")
//    private Price price;
    @SerializedName("sku")
    @NonNull
    private String sku;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
//    @SerializedName("_links")
//    private Links links;
//    @SerializedName("_metadata")
//    private Metadata metadata;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    public String getSku() {
        return sku;
    }

    public void setSku(@NonNull String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getEnergyClass() {
        return energyClass;
    }

    public void setEnergyClass(String energyClass) {
        this.energyClass = energyClass;
    }

    public String getManufacturePrice() {
        return manufacturePrice;
    }

    public void setManufacturePrice(String manufacturePrice) {
        this.manufacturePrice = manufacturePrice;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAssemblyService() {
        return assemblyService;
    }

    public void setAssemblyService(String assemblyService) {
        this.assemblyService = assemblyService;
    }
}
