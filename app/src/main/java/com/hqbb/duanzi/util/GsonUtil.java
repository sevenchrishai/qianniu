package com.hqbb.duanzi.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 * Created by ylh on 2018/1/8 0008.
 */

public class GsonUtil {
    public static <T> T parseGson(String result, Type clazz) {
        try {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(result, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
