package com.example.ningqiangzhao.study.utils;

/**
 * @description: author:zhaoningqiang
 * @time 16/6/6/下午3:23
 */

import android.util.Log;


import android.util.Log;


/**
 * @description:
 * @author: zhaoningqiang
 * @time: 2015/3/17/16:48
 */
public class ULog {
    private static boolean useLog = true;
    public static final String DEFAULT_TAG = "DEFAULT";

    public static void v(String tag, String msg) {
        if (useLog) {
            Log.v(tag, msg);
        }
    }

    public static void v(String msg) {
        if (useLog) {
            Log.v(DEFAULT_TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (useLog) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (useLog) {
            Log.d(DEFAULT_TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (useLog) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if (useLog) {
            Log.e(DEFAULT_TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (useLog) {
            Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable throwable) {
        if (useLog) {
            Log.w(tag, msg, throwable);
        }
    }

    public static void w(String msg) {
        if (useLog) {
            Log.w(DEFAULT_TAG, msg);
        }
    }

}
