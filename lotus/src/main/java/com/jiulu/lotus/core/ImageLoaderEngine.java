package com.jiulu.lotus.core;

import android.content.Context;
import android.widget.ImageView;

import com.jiulu.lotus.cache.DiskCache;
import com.jiulu.lotus.cache.MemoryCache;

/**
 * 图片加载引擎
 */

public class ImageLoaderEngine{
    private Context mContext;
    private MemoryCache mMemoryCache;
    private DiskCache mDiskCache;
    private DeliveryResult mDelivery;


    public ImageLoaderEngine load(String url){
        return this;
    }

    public ImageLoaderEngine into(ImageView target){
        return this;
    }

    public ImageLoaderEngine init(Context context) {
        mContext = context;
        return this;
    }
}
