package com.jiulu.lotus.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;

import com.jiulu.lotus.base.FixedThreadPool;
import com.jiulu.lotus.base.ThreadPool;
import com.jiulu.lotus.cache.DiskCache;
import com.jiulu.lotus.cache.MemoryCache;
import com.jiulu.lotus.http.DownloadFileCallback;
import com.jiulu.lotus.http.HttpDownloaderManager;
import com.jiulu.lotus.tool.Logger;
import com.jiulu.lotus.tool.Precondition;
import com.jiulu.lotus.tool.UrlUtils;

import java.io.File;


/**
 * Created by zhanghongjun on 2017/11/19.
 */

public class Lotus {
    public static final String TAG = "Lotus";
    private static Lotus sInstance;

    //唯一
    private ImageLoaderEngine mImageLoaderEngine;
    private Context mContext;

    static Lotus self() {
        if (sInstance == null) {
            synchronized (Lotus.class) {
                if (sInstance == null) {
                    sInstance = new Lotus();
                }
            }
        }
        return sInstance;
    }

    private Lotus() {
        mImageLoaderEngine = new ImageLoaderEngine();
    }

    public static ImageLoaderEngine with(Context context) {
        return getImageLoaderEngine().with(context);
    }

    public ImageLoaderEngine load(String url) {
        return getImageLoaderEngine().load(url);
    }

    public ImageLoaderEngine into(ImageView target){
        return getImageLoaderEngine().into(target);
    }

    public static ImageLoaderEngine getImageLoaderEngine(){
        return self().mImageLoaderEngine;
    }

//    public static Lotus configuration(Configuration configuration) {
//        self().mConfiguration = configuration;
//        return self();
//    }

//    public void into(ImageView targetImageView) {
//        self().mTargetImageView = targetImageView;
//
//        //1 检查条件
//        //self().checkCondition();
//
//        //2 检查内存缓存中有没有
//        Bitmap bitmap = self().mMemoryCache.get(self().mUrl);
//        if (bitmap == null) {
//            String filepath = self().mDiskCache.get(self().mUrl);
//            if(TextUtils.isEmpty(filepath)){
//                //3 磁盘缓存中也没有，从网络获取
//                self().getBitmapFromHttp(self().mUrl);
//                return;
//            }else {
//                bitmap = BitmapFactory.decodeFile(filepath);
//            }
//        }
//
//        if(bitmap != null){
//            self().showImage(self().mUrl);
//        }
//    }

//    private void showImage(final String url) {
//        mUiHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                Bitmap bitmap = mMemoryCache.get(url);
//                if(bitmap == null){
//                    Logger.e("Lotus","bitmap 为null url=" + url);
//                }
//                if(mTargetImageView != null){
//                    mTargetImageView.setImageBitmap(bitmap);
//                }
//            }
//        });
//    }

//    //从网络获取bitmap
//    private void getBitmapFromHttp(final String url) {
//        HttpDownloaderManager.getInstance().download(url, new DownloadFileCallback() {
//            @Override
//            public void downloadSuccess(File file) {
//                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//                mDiskCache.save(url,file.getAbsolutePath());
//                mMemoryCache.save(url,bitmap);
//                showImage(url);
//            }
//
//            @Override
//            public void downloadFailed() {
//                Logger.e(TAG,"url=" + mUrl + "下载失败");
//            }
//        });
//    }

//    private void checkCondition() {
//        Precondition.checkNotNull(mConfiguration, "Configuration must not be null");
//        Precondition.checkNotNull(mContext, "mContext must not be null");
//        Precondition.checkArgument(UrlUtils.isValidUrl(mUrl), "url is invalid");
//    }

//    class UIHandler extends Handler {
//        public UIHandler() {
//            super(Looper.getMainLooper());
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            onHandleMessage(msg);
//        }
//    }

//    private void onHandleMessage(Message msg) {
//
//
//    }

}
