package com.jiulu.lotus.http;

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




}
