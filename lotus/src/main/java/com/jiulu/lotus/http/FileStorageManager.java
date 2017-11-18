package com.jiulu.lotus.http;

import android.content.Context;
import android.os.Environment;

import com.jiulu.lotus.tool.Md5Utils;

import java.io.File;
import java.io.IOException;

/**
 * 文件存储管理
 */

public class FileStorageManager {
    private static FileStorageManager sInstance;

    private Context mContext;

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
        mContext = context;
    }

    //根据url生成文件
    public File getFileByUrl(String url){
        File parent;

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            parent = mContext.getExternalCacheDir();
        }else {
            parent = mContext.getCacheDir();
        }

        String fileName = Md5Utils.toMd5(url);
        File file = new File(parent,fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }



}
