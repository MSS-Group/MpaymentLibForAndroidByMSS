package com.mss.testlib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.mss.testlib.utils.constants.Util;


public class SharedPreferencesManager {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public SharedPreferencesManager() {
    }

    public SharedPreferencesManager(Context context) {
        sharedPref = context.getSharedPreferences(Util.SHARED_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public SharedPreferencesManager(Context context, String type) {
        sharedPref = context.getSharedPreferences(Util.SHARED_NAME_UUID, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public void putString(String key,String value){
        editor.putString(key,value);
        editor.commit();
    }


    public String getString(String key){
        return sharedPref.getString(key, Util.NOT_SAVED);
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }
}
