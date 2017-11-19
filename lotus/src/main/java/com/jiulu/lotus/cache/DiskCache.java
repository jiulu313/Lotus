package com.jiulu.lotus.cache;


import android.content.Context;
import android.util.LruCache;


/**
 * 磁盘缓存
 */

public class DiskCache {
    private LruCache<String,Long> mDiskCache;

    public DiskCache(){
        int cacheSize = 100 * 1024 * 1024; // 100M
        mDiskCache = new LruCache<String, Long>(cacheSize){
            @Override
            protected int sizeOf(String key, Long value) {
                return value.intValue();
            }
        };
    }


    public void save(String key,Long value){
        mDiskCache.put(key,value);
    }

    public Long get(String key){
        return mDiskCache.get(key);
    }
}
