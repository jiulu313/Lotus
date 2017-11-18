package com.jiulu.lotus.base;

import java.util.WeakHashMap;

/**
 * Created by zhanghongjun on 2017/11/18.
 * 工作线程
 */

public class Worker extends Thread{
    private boolean mRunning = false;
    private TaskQueue<Runnable> mTaskQueue;


    public Worker(TaskQueue<Runnable> taskQueue){
        mTaskQueue = taskQueue;
    }

    public void start(){
        mRunning = true;
        super.start();
    }

    public void shutdown(){
        mRunning = false;
        interrupt();
    }

    @Override
    public void run() {
        while (mRunning){
            Runnable task = null;
            synchronized (mTaskQueue){
                if(mTaskQueue.isEmpty()){
                    try {
                        mTaskQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        mRunning = false;
                    }
                }

                task = mTaskQueue.poll();
            }

            if(task != null){
                task.run();
            }
        }
    }
}
