package com.example.myrepository.utils;

import android.util.Log;

public class LogUtil {

    private static final boolean isDebug = true;
    public static final String TAG_ERROR = "error";
    public static final String TAG = "日志---";
    public static final String TAG_HTTP = "http";

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }


    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

    private LogUtil() throws Exception {
        throw new AssertionError();
    }

}
