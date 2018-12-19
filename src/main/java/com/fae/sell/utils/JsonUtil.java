package com.fae.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 功能描述: json转换工具类
 *
 * @作者: lj
 * @创建时间: 2018/12/19 16:43
 */
public class JsonUtil {

    public static String toJson(Object o) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(o);
    }
}
