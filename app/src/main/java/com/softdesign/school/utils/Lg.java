package com.softdesign.school.utils;

import android.util.Log;


public class Lg {

    private static final String PREFIX = "HTC ";
    private static final int LOGCAT_BUFFER_SIZE = 3000;
    private static final boolean IS_LOGGER_ENABLED = true;

    private static boolean shouldLog() {
//        return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
//        return true;
        return IS_LOGGER_ENABLED;
    }

    /** Info logger. */
    public static void i (String tag, String text){
        if(checkLength(text)){
            Log.i(PREFIX + tag, calculateBuffer(text,tag,0));
        } else {
            Log.i(PREFIX + tag, text);
        }
    }

    /** Error logger. */
    public static void e (String tag, String text) {
        if(checkLength(text)){
            Log.e(PREFIX + tag, calculateBuffer(text, tag, 1));
        } else {
            Log.e(PREFIX + tag, text);
        }
    }

    /** Warn logger. */
    public static void w (String tag, String text) {
        if(checkLength(text)){
            Log.w(PREFIX + tag, calculateBuffer(text, tag, 2));
        } else {
            Log.w(PREFIX + tag, text);
        }
    }

    /** Debug logger. */
    public static void d (String tag, String text){
        if(checkLength(text)){
            Log.d(PREFIX + tag, calculateBuffer(text, tag, 3));
        } else {
            Log.d(PREFIX + tag, text);
        }
    }

    /**
    * Assert logger
    * The Log.wtf() methods were introduced in API level 8.
    */
    public static void wtf (String tag, String text){
        if(checkLength(text)){
            Log.wtf(PREFIX + tag, calculateBuffer(text, tag, 4));
        } else {
            Log.wtf(PREFIX + tag, text);
        }
    }

    /** Verbose logger  */
    public static void v (String tag, String text){
            if(checkLength(text)){
                Log.v(PREFIX + tag, calculateBuffer(text,tag,5));
            } else {
                Log.v(PREFIX + tag, text);
            }

    }

    /**
    * Calculate length of string with LOGCAT_BUFFER_SIZE.
    * flag 0 - Info Log.
    * flag 1 - Error Log.
    * flag 2 - Warn Log.
    * flag 3 - Debug Log.
    * flag 4 - Assert Log.
    * flag 5 - Verbose Log.
    */
    private static String calculateBuffer(String text, String tag, int flag){
        String s = text;
        while (s.length() > LOGCAT_BUFFER_SIZE){
            String s1 = s.substring(0, LOGCAT_BUFFER_SIZE);
            s = s.substring(LOGCAT_BUFFER_SIZE);
                switch (flag){
                    case 0:Log.i(PREFIX + tag, s1); break;
                    case 1:Log.e(PREFIX + tag, s1); break;
                    case 2:Log.w(PREFIX + tag, s1); break;
                    case 3:Log.d(PREFIX + tag, s1); break;
                    case 4:Log.wtf(PREFIX + tag, s1); break;
                    case 5:Log.v(PREFIX + tag, s1); break;
                        default:Log.e(PREFIX + tag,"Unknown flag"); break;
                }
        }
        return s;
    }

    /** Check if logger is enabled and equals length */
    private static boolean checkLength(String txt){
        return shouldLog() && txt.length() > LOGCAT_BUFFER_SIZE;
    }
}
