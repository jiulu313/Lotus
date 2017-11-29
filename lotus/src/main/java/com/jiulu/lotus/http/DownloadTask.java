package com.jiulu.lotus.http;

import android.support.v4.app.FragmentActivity;

import com.jiulu.lotus.tool.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by zhanghongjun on 2017/11/19.
 */

public class DownloadTask implements Runnable {
    AtomicBoolean mCancel = new AtomicBoolean(false);
    DownloadFileCallback mCallback;
    String mUrl;
    Call mCall;

    public DownloadTask(String url, DownloadFileCallback callback) {
        mUrl = url;
        mCallback = callback;
    }

    //取消任务
    public void cancel(){
        if(mCancel.get()){
            cancelCall();
            mCancel.set(true);
        }
    }

    public boolean isCancel(){
        return mCancel.get();
    }

    protected void runCall() {
        if (mCall == null) {
            buildCall();
        }

        try {
            Response response = mCall.execute();
            if (!response.isSuccessful()) {
                //失败
                return;
            }

            File file = FileStorageManager.getInstance().getFileByUrl(mUrl);
            boolean result = FileUtils.writeFile(response.body().byteStream(), file);
            if(result){
                mCallback.downloadSuccess(file);
            }else {
                mCallback.downloadFailed();
            }
        } catch (IOException e) {
            e.printStackTrace();
            mCallback.downloadFailed();
        }
    }

    protected void buildCall() {
        mCall = HttpHelper.getInstance().buildCall(mUrl);
    }

    protected void cancelCall(){
        if(mCall != null){
            mCall.cancel();
        }
    }

    @Override
    public void run() {
        runCall();
    }
}
