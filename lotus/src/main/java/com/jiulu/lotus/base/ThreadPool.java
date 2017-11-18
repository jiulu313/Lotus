package com.jiulu.lotus.base;

/**
 * Created by zhanghongjun on 2017/11/18.
 */

public interface ThreadPool {
    void start();
    void shutdown();
    void execute(Runnable runnable);
}
