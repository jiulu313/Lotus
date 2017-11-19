package com.jiulu.lotus.cache;


import android.content.Context;
import android.util.LruCache;

import com.jiulu.lotus.tool.FileUtils;


/**
 * 磁盘缓存
 */

public class DiskCache implements Cache<String,String>{
    private LruCache<String,String> mDiskCache;

    public DiskCache(){
        int cacheSize = 100 * 1024 * 1024; // 100M
        mDiskCache = new LruCache<String, String>(cacheSize){
            @Override
            protected int sizeOf(String key, String filepath) {
                return (int) FileUtils.getFileSize(filepath);
            }
        };
    }

    @Override
    public void save(String key, String filepath) {
        mDiskCache.put(key,filepath);
    }

    @Override
    public String get(String key) {
        return mDiskCache.get(key);
    }
}
