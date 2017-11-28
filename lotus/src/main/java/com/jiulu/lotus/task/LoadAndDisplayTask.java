package com.jiulu.lotus.task;

import android.widget.ImageView;

import com.jiulu.lotus.http.DownloadFileCallback;
import com.jiulu.lotus.http.DownloadTask;


/**
 * 加载并显示图片
 */

public class LoadAndDisplayTask extends DownloadTask{
    private ImageView mTarget;

    public LoadAndDisplayTask(String url, DownloadFileCallback callback) {
        super(url, callback);
        buildCall();
    }

    @Override
    public void run() {



        super.run();
    }
}
