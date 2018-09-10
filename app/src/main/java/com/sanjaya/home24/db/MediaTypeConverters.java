package com.sanjaya.home24.db;

import android.arch.persistence.room.TypeConverter;

import com.sanjaya.home24.datamodel.Media;

import java.util.ArrayList;
import java.util.List;

public class MediaTypeConverters {
    @TypeConverter
    public List<Media> gettingListFromString(String generateUrls){
        List<Media> mediaList = new ArrayList<>();

        String[] array = generateUrls.split(",");
        for(String url : array){
            mediaList.add(new Media("image/png", 0, null, null, url));
        }
        return mediaList;
    }

    @TypeConverter
    public String writingStringFromList(List<Media> mediaList){
        String generateUrls = "";
        for(Media media : mediaList){
            generateUrls = generateUrls + (generateUrls.equalsIgnoreCase("") ? "" : ",") + media.getUri();
        }

        return generateUrls;
    }
}
