package com.jiulu.lotus.core;

import android.content.Context;
import android.content.pm.FeatureInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.widget.ImageView;

import com.jiulu.lotus.base.FixedThreadPool;
import com.jiulu.lotus.base.ThreadPool;
import com.jiulu.lotus.cache.Cache;
import com.jiulu.lotus.cache.DiskCache;
import com.jiulu.lotus.cache.MemoryCache;
import com.jiulu.lotus.http.FileStorageManager;
import com.jiulu.lotus.http.HttpHelper;
import com.jiulu.lotus.task.LoadAndDisplayTask;

import java.io.File;

/**
 * 图片加载引擎
 */

public class ImageLoaderEngine{
    protected Context mContext;
    protected Cache<String,Bitmap> mMemoryCache;
    protected Cache<String,String> mDiskCache;    //key->url   value->filename

    protected ThreadPool mThreadPool;
    protected boolean mRunning;

    protected DeliveryEngine mDelivery;


    private String mUrl;
    private ImageView mTarget;


    public ImageLoaderEngine with(Context context) {
        mContext = context;
        FileStorageManager.getInstance().init(mContext);
        return this;
    }

    public ImageLoaderEngine load(String url){
        mUrl = url;
        return this;
    }

    public ImageLoaderEngine into(ImageView target){
        mTarget = target;

        //1 初始化
        init();

        //2 启动线程池
        startThreadPool();

        //3 生成任务
        createLoadAndDisplayTask();

        return this;
    }

    public DeliveryEngine getDelivery(){
        return mDelivery;
    }

    //生成任务
    private void createLoadAndDisplayTask() {
        Bitmap bitmap = mMemoryCache.get(mUrl);
        if(bitmap != null){
            //直接显示
            mDelivery.display(bitmap,mTarget);
            return;
        }

        String filename = mDiskCache.get(mUrl);
        if(!TextUtils.isEmpty(filename)){
            File file = new File(filename);
            if(file.exists() && !TextUtils.isEmpty(filename)){
                bitmap = BitmapFactory.decodeFile(filename);
                mDelivery.display(bitmap,mTarget);
                putMemoryCache(mUrl,bitmap);
                return;
            }
        }

        LoadAndDisplayTask task = new LoadAndDisplayTask(mUrl,mTarget,this);
        mThreadPool.execute(task);
    }

    //缓存到内存和磁盘缓存
    public void putCache(String url,Bitmap bitmap,String filename){
        putMemoryCache(url,bitmap);
        putDiskCache(url,filename);
    }

    //缓存到内存缓存
    public void putMemoryCache(String url,Bitmap bitmap){
        mMemoryCache.save(url,bitmap);
    }

    //缓存到磁盘缓存
    public void putDiskCache(String url,String filename){
        mDiskCache.save(url,filename);
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
            mDelivery = new DeliveryEngine();
        }

        return this;
    }



}
