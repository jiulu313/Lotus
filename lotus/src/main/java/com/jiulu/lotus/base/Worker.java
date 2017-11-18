package com.jiulu.lotus.base;

import java.util.WeakHashMap;

/**
 * Created by zhanghongjun on 2017/11/18.
 * 工作线程
 */

public class Worker extends Thread{
    private boolean mRunning = false;
    private TaskQueue<Runnable> mTaskQueue;
    private String mName;   //工作线程名字


    public Worker(TaskQueue<Runnable> taskQueue){
        this(taskQueue,Thread.currentThread().getId() + "");
    }

    public Worker(TaskQueue<Runnable> taskQueue,String name){
        super(name);
        mTaskQueue = taskQueue;
        mName = name;
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
                        mRunning = false;
                        break;
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
