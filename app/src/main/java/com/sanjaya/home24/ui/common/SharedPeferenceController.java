package com.sanjaya.home24.ui.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.sanjaya.home24.MainActivity;

public class SharedPeferenceController {
    private static final String HOME24_PREFS = "home24_preference";
    private final SharedPreferences prefs;

    public SharedPeferenceController(MainActivity mainActivity) {
        this.prefs = mainActivity.getSharedPreferences(HOME24_PREFS, Context.MODE_PRIVATE);
    }

    public void saveStringValue(String key, String value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void saveIntegerValue(String key, int value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void saveBooleanValue(String key, boolean value){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getStringValue(String key){
        return prefs.getString(key, null);
    }

    public int getIntegerValue(String key) {
        return prefs.getInt(key, 0);
    }

    public boolean getBooleanValue(String key){
        return prefs.getBoolean(key, false);
    }

}
