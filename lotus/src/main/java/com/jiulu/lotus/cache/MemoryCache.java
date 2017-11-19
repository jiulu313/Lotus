package com.jiulu.lotus.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

/**
 * 内存缓存
 */

public class MemoryCache {
    LruCache<String,Bitmap> mLruCache;

    public MemoryCache(){
        long maxMemory = Runtime.getRuntime().maxMemory();
        int cacheSize = (int) (maxMemory / 8);
        mLruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
    }

    public void save(String key,Bitmap bitmap){
        mLruCache.put(key,bitmap);
    }

    public Bitmap get(String key){
        return mLruCache.get(key);
    }

}
