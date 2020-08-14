package com.csbr.cloud.flinkserver.utils;

import com.google.gson.Gson;

import java.nio.charset.Charset;

/**
 * @author zhangheng
 * @date 2019/12/17 11:20
 */
public class GsonUtil {
    private final static Gson gson = new Gson();

    public static <T> T fromJson(String value, Class<T> type) {
        return gson.fromJson(value, type);
    }

    public static String toJson(Object value) {
        return gson.toJson(value);
    }

    public static byte[] toJSONBytes(Object value) {
        return gson.toJson(value).getBytes(Charset.forName("UTF-8"));
    }
}
