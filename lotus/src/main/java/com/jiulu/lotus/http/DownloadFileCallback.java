package com.jiulu.lotus.http;

import java.io.File;

/**
 * Created by zhanghongjun on 2017/11/19.
 */

public interface DownloadFileCallback {
    void downloadSuccess(File file);

    void downloadFailed();
}
