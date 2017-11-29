package com.jiulu.lotus.core;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by zhanghongjun on 2017/11/29.
 */

public class DisplayEntry {
    public Bitmap bitmap;
    public ImageView target;

    public DisplayEntry(Bitmap bitmap,ImageView target){
        this.bitmap = bitmap;
        this.target = target;
    }

    public DisplayEntry(){
    }
}
