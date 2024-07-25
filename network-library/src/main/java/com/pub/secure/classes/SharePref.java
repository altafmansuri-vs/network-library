package com.pub.secure.classes;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharePref {


    Context context;
    private final String PREF_NAME = "secure_pref";
    SharedPreferences sharedPreferences;

    public SharePref(Context context) {
        if (context == null){
            Log.d("showError__", "Context: NULL ");
            return;
        }
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    }

    public String getString(String value){
        return sharedPreferences.getString(value,"");
    }

    public void setString(String key,String value){
        sharedPreferences.edit().putString(key,value).apply();
    }

    public boolean getBoolean(String value){
        return sharedPreferences.getBoolean(value,false);
    }

    public void setBoolean(String key,Boolean value){
        sharedPreferences.edit().putBoolean(key,value).apply();
    }
}
