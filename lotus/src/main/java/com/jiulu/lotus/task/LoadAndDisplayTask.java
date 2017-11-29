package com.jiulu.lotus.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import com.jiulu.lotus.core.DeliveryEngine;
import com.jiulu.lotus.core.ImageLoaderEngine;
import com.jiulu.lotus.http.FileStorageManager;
import com.jiulu.lotus.http.HttpHelper;
import com.jiulu.lotus.tool.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 加载并显示图片
 */

public class LoadAndDisplayTask implements Runnable{
    AtomicBoolean mCancel = new AtomicBoolean(false);
    String mUrl;
    Call mCall;
    ImageView mTarget;
    ImageLoaderEngine mEngine;

    public LoadAndDisplayTask(String url,ImageView target,ImageLoaderEngine engine){
        mUrl = url;
        mTarget = target;
        mEngine = engine;
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

            if(isCancel()){
                return;
            }

            File file = FileStorageManager.getInstance().getFileByUrl(mUrl);
            boolean result = FileUtils.writeFile(response.body().byteStream(), file);
            if(result){
                final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                //1 缓存
                mEngine.putCache(mUrl,bitmap,file.getAbsolutePath());

                //2 显示
                if(!isCancel()){
                    DeliveryEngine delivery = mEngine.getDelivery();
                    delivery.display(bitmap,mTarget);
                }
            }
        } catch (IOException e) {

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
        if(!isCancel()){
            runCall();
        }
    }
}
