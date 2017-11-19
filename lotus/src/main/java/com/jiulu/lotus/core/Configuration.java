package com.jiulu.lotus.core;

import android.graphics.Bitmap;

import com.jiulu.lotus.cache.Cache;

import java.io.File;

/**
 * Created by zhanghongjun on 2017/11/19.
 */

public class Configuration {
    private Cache<String, Long> mDiskCache;
    private int mDiskCacheSize;
    private File mDiskCacheDir;

    private Cache<String, Bitmap> mMemoryCache;
    private int mMemoryCacheSize;

    //私有构造函数
    private Configuration(Builder builder) {
        mDiskCache = builder.diskCache;
        mDiskCacheSize = builder.diskCacheSize;
        mMemoryCache = builder.memoryCache;
        mMemoryCacheSize = builder.memoryCacheSize;
        mDiskCacheDir = builder.diskCacheDir;
    }

    public class Builder {
        Cache<String, Long> diskCache;
        int diskCacheSize;
        File diskCacheDir;

        Cache<String, Bitmap> memoryCache;
        int memoryCacheSize;

        public Configuration build(Builder builder) {
            return new Configuration(builder);
        }

        public Builder diskCache(Cache<String, Long> diskCache) {
            this.diskCache = diskCache;
            return this;
        }

        public Builder diskCacheSize(int diskCacheSize) {
            this.diskCacheSize = diskCacheSize;
            return this;
        }

        public Builder memoryCache(Cache<String, Bitmap> memoryCache) {
            this.memoryCache = memoryCache;
            return this;
        }

        public Builder memoryCacheSize(int memoryCacheSize){
            this.memoryCacheSize = memoryCacheSize;
            return this;
        }

        public Builder diskCacheDir(File diskCacheDir){
            this.diskCacheDir = diskCacheDir;
            return this;
        }
    }
}
