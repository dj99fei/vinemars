package com.vine.vinemars.utils;

import android.util.Log;

import com.vine.vinemars.BuildConfig;


/**
 * Created by chengfei on 14-10-14.
 */
public class LogUtils {

    public static final void e(String tag, Exception e) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, e.getMessage());
        }
    }
    public static final void d(String tag, String format, Object ... params) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, String.format(format, params));
        }
    }
}
