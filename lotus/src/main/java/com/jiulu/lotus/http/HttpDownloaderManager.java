package com.jiulu.lotus.http;

import com.jiulu.lotus.base.FixedThreadPool;
import com.jiulu.lotus.base.ThreadPool;

/**
 * 下载器
 */

public class HttpDownloaderManager {
    private static HttpDownloaderManager sInstance;

    private ThreadPool mThreadPool;

    private HttpDownloaderManager(){
        if(mThreadPool == null){
            mThreadPool = new FixedThreadPool();
            mThreadPool.start();
        }
    }

    public static HttpDownloaderManager getInstance(){
        if(sInstance == null){
            synchronized (HttpDownloaderManager.class){
                if(sInstance == null){
                    sInstance = new HttpDownloaderManager();
                }
            }
        }

        return sInstance;
    }

    public void download(String url,DownloadFileCallback callback){
        DownloadTask task = new DownloadTask(url,callback);
        mThreadPool.execute(task);
    }



}
