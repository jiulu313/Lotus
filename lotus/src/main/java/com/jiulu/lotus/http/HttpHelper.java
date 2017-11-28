package com.jiulu.lotus.http;

import android.content.Context;
import android.content.pm.FeatureInfo;

import com.jiulu.lotus.cache.DiskCache;
import com.jiulu.lotus.cache.MemoryCache;
import com.jiulu.lotus.tool.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhanghongjun on 2017/11/19.
 */

public class HttpHelper {
    private static HttpHelper sInstance;
    private OkHttpClient mOkHttpClient;

    private HttpHelper(){
    }

    public static HttpHelper getInstance(){
        if(sInstance == null){
            synchronized (HttpHelper.class){
                if(sInstance == null){
                    sInstance = new HttpHelper();
                }
            }
        }
        return sInstance;
    }

    public void init(Context context){
        mOkHttpClient = new OkHttpClient();
    }

    //同步请求
    public File syncRequest(String url){
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = mOkHttpClient.newCall(request).execute();
            if(!response.isSuccessful()){
                //失败
                return null;
            }

            File file = FileStorageManager.getInstance().getFileByUrl(url);
            boolean res = FileUtils.writeFile(response.body().byteStream(),file);
            return res ? file : null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //生成Call
    public Call buildCall(String url){
        Request request = new Request.Builder().url(url).build();
        return mOkHttpClient.newCall(request);
    }

}
