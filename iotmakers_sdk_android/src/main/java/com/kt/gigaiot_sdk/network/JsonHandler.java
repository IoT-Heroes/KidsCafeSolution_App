package com.kt.gigaiot_sdk.network;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by ceoko on 15. 4. 6..
 */
public class JsonHandler {

    private static Gson GSON = new Gson();

    public static String toJson(Object src){

        return GSON.toJson(src);
    }

    public static <T> T fromJson(String resultJson, Type typeOfT){
        return GSON.fromJson(resultJson, typeOfT);
    }
}
