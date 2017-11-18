package com.jiulu.lotus.tool;

import android.util.Log;

/**
 * Created by zhanghongjun on 2017/11/18.
 */

public class Logger {
    public static String TAG = Logger.class.getSimpleName();
    public static boolean DEBUG = true;

    private Logger(){
    }

    private static boolean isDebug(){
        return DEBUG;
    }

    public static void d(String tag,String message){
        if(isDebug()){
            Log.d(tag,message);
        }
    }

    public static void e(String tag,String message){
        if(isDebug()){
            Log.e(tag,message);
        }
    }

    public static void i(String tag,String message){
        if(isDebug()){
            Log.i(tag,message);
        }
    }

    public static void w(String tag,String message){
        if(isDebug()){
            Log.w(tag,message);
        }
    }
}
