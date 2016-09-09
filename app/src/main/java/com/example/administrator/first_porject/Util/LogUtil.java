package com.example.administrator.first_porject.Util;

import android.util.Log;

public class LogUtil {

    public static boolean isOpenDebugLog = true;

    public static void logD(String tag, String msg) {
        if (isOpenDebugLog)
            Log.d(tag, msg);
    }


}