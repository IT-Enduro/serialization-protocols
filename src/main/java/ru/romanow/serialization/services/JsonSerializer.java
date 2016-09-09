package ru.romanow.serialization.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by ronin on 09.09.16
 */
public class JsonSerializer {

    private static final Gson gson =
            new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }
}
