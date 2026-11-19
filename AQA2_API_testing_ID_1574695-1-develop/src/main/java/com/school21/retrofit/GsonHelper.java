package com.school21.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {
    
    public static Gson createGson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();
    }
}
