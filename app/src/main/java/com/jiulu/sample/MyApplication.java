package com.jiulu.sample;

import android.app.Application;

import com.jiulu.lotus.http.FileStorageManager;

/**
 * Created by zhanghongjun on 2017/11/18.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        FileStorageManager.getInstance().init(this);



    }
}
