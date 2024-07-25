package com.pub.secure.classes;

import android.util.Log;

public class ApiKeyManager {

    private static ApiKeyManager instance;
    private String apiKey;
    private String baseURL;

    private ApiKeyManager() {
        // Private constructor to prevent instantiation from outside
    }

    public static synchronized ApiKeyManager getInstance() {
        if (instance == null) {
            instance = new ApiKeyManager();
        }
        return instance;
    }

    public void setApiKey(String apiKey) {
        Log.d( "setApiKey__",apiKey);
        this.apiKey = apiKey;
    }

    public void setBaseUrl(String url) {
        this.baseURL = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseURL;
    }
}