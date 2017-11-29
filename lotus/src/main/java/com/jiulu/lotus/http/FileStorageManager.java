package com.jiulu.lotus.http;

import android.content.Context;
import android.os.Environment;

import com.jiulu.lotus.tool.FileUtils;
import com.jiulu.lotus.tool.Md5Utils;
import com.jiulu.lotus.tool.Precondition;

import java.io.File;
import java.io.IOException;

/**
 * 文件存储管理
 */

public class FileStorageManager {
    private static FileStorageManager sInstance;

    private Context mContext;
    private File mCacheDir;

    public static FileStorageManager getInstance(){
        if(sInstance == null){
            synchronized (FileStorageManager.class){
                if(sInstance == null){
                    sInstance = new FileStorageManager();
                }
            }
        }

        return sInstance;
    }

    public void init(Context context){
        mContext = context.getApplicationContext();
        createCacheDir();
    }

    //根据url生成文件
    public File getFileByUrl(String url){
        String fileName = Md5Utils.toMd5(url);
        File file = new File(mCacheDir,fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                file = null;
            }
        }
        return file;
    }

    //获取磁盘缓存目录
    public File getCacheDir(){
        return mCacheDir;
    }

    //获取缓存目录大小
    public long getCacheDirSize(){
        return FileUtils.getDirSize(mCacheDir);
    }

    //创建磁盘缓存目录
    private void createCacheDir(){
        File parent;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            parent = mContext.getExternalCacheDir();
        }else {
            parent = mContext.getCacheDir();
        }

        String path = parent.getAbsolutePath() + File.separator + "lotusDiskCache";
        mCacheDir = new File(path);
        if(!mCacheDir.exists()){
            mCacheDir.mkdir();
        }
    }


}
