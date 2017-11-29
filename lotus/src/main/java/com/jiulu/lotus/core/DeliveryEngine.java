package com.jiulu.lotus.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import com.jiulu.lotus.tool.Utils;

import java.io.File;


/**
 * 分发器
 */

public class DeliveryEngine implements Handler.Callback{
    Handler mUiHandler = new Handler(Looper.getMainLooper(),this);

    public DeliveryEngine(){
    }

    public void display(Bitmap bitmap, ImageView target){
        if(Utils.isOnMainThread()){
            displayImageViewOnMainThread(bitmap,target);
        }else{
            mUiHandler.obtainMessage(0,new DisplayEntry(bitmap,target)).sendToTarget();
        }
    }

    public void display(File file,ImageView target){
        if(Utils.isOnMainThread()){
            displayImageViewOnMainThread(BitmapFactory.decodeFile(file.getAbsolutePath()),target);
        }else {
            DisplayEntry entry = new DisplayEntry();
            entry.bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            entry.target = target;
            mUiHandler.obtainMessage(0,entry).sendToTarget();
        }
    }

    public void display(String filename,ImageView target){
        display(new File(filename),target);
    }

    private void displayImageViewOnMainThread(Bitmap bitmap, ImageView target) {
        target.setImageBitmap(bitmap);
    }

    @Override
    public boolean handleMessage(Message msg) {
        onHandleMessage(msg);
        return false;
    }

    private void onHandleMessage(Message msg) {
        DisplayEntry entry = (DisplayEntry) msg.obj;
        displayImageViewOnMainThread(entry.bitmap,entry.target);
    }
}
