package com.engin.utils;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by zhaoningqiang on 16/6/19.
 */

public class LogUtil {
    static String TAG = LogUtil.class.getSimpleName();
    static File outFile = new File("/Users/zhaoningqiang/Desktop/outLog/vlog.txt");

    static {
//        if (!outFile.exists()) {
//            try {
//                outFile.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public static void writeToLocal(String tag, String log, boolean append) {
        try {
            FileWriter writer = new FileWriter(outFile, append);
            writer.write(tag + "  :  " + log);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void writeToLocal(String tag, String log) {
        writeToLocal(tag, log, true);
    }

    public static void writeToLocal(String log) {
        writeToLocal(TAG, log, true);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(TAG, msg);
    }
}
