package com.jiulu.lotus.http;

import java.io.File;

/**
 * 下载器
 */

public class HttpDownloader {
    private static HttpDownloader sInstance;

    private HttpDownloader(){
    }

    public static HttpDownloader getInstance(){
        if(sInstance == null){
            synchronized (HttpDownloader.class){
                if(sInstance == null){
                    sInstance = new HttpDownloader();
                }
            }
        }

        return sInstance;
    }

    public File download(String url){


        return null;
    }



}
