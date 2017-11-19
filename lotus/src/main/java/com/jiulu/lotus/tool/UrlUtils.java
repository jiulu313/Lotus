package com.jiulu.lotus.tool;

import android.text.TextUtils;

/**
 * Created by zhanghongjun on 2017/11/19.
 */

public class UrlUtils {


    //检查url是否合法
    public static boolean isValidUrl(String url){
        if(TextUtils.isEmpty(url)){
            return false;
        }

        if(!url.startsWith("http://") || !url.startsWith("https://")){
            return false;
        }

        return true;
    }

}
