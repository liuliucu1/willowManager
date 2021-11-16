package com.example.test.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.Primitives;

import java.lang.reflect.Type;


public class GJson {
    /**
     * @description: 进行各种字符串转换
     **/
    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return Primitives.wrap(classOfT).cast(new Gson().fromJson(json, classOfT));
    }

    public static <T> T fromJson(String json, Type typeOf) {
        return new Gson().fromJson(json, typeOf);
    }

    public static String toJson(Object src) {
        return new Gson().toJson(src);
    }

}

