package com.jiulu.lotus.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.jiulu.lotus.base.FixedThreadPool;
import com.jiulu.lotus.base.PriorityTaskQueue;
import com.jiulu.lotus.base.ThreadPool;
import com.jiulu.lotus.cache.Cache;
import com.jiulu.lotus.cache.DiskCache;
import com.jiulu.lotus.cache.MemoryCache;

/**
 * 图片加载引擎
 */

public class ImageLoaderEngine{
    private Context mContext;
    private Cache<String,Bitmap> mMemoryCache;
    private Cache<String,String> mDiskCache;    //key->url   value->filename
    private DeliveryResult mDelivery;

    private ThreadPool mThreadPool;
    private boolean mRunning;

    public ImageLoaderEngine with(Context context) {
        mContext = context;
        return this;
    }

    public ImageLoaderEngine load(String url){
        return this;
    }

    public ImageLoaderEngine into(ImageView target){
        init();
        startThreadPool();

        return this;
    }

    private void startThreadPool() {
        if(mRunning){
            return;
        }

        if(mThreadPool != null){
            mThreadPool.shutdown();
        }else {
            mThreadPool = new FixedThreadPool();
        }

        mRunning = true;
        mThreadPool.start();
    }


    private ImageLoaderEngine init() {
        if(mMemoryCache == null){
            mMemoryCache = new MemoryCache();
        }

        if(mDiskCache == null){
            mDiskCache = new DiskCache();
        }

        if(mDelivery == null){
            mDelivery = new DeliveryResult();
        }

        return this;
    }



}
