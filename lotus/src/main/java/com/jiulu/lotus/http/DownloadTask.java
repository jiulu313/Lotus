package com.jiulu.lotus.http;

import java.io.File;

/**
 * Created by zhanghongjun on 2017/11/19.
 */

public class DownloadTask implements Runnable{
    DownloadFileCallback mCallback;
    String mUrl;

    public DownloadTask(String url,DownloadFileCallback callback){
        mUrl = url;
        mCallback = callback;
    }

    @Override
    public void run() {
        File file = HttpHelper.getInstance().syncRequest(mUrl);
        if(file != null && file.exists()){
            mCallback.downloadSuccess(file);
        }else {
            mCallback.downloadFailed();
        }
    }
}
