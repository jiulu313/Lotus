package com.jiulu.lotus.base;

import com.jiulu.lotus.exception.ArgumentException;

/**
 * 固定线程数的线程池
 */

public class FixedThreadPool implements ThreadPool {
    public static final int DEFAULT_WORKER_NUM = 4;
    public static final int MAX_WORKER_NUM = 16;
    public static final int MIN_WORKER_NUM = 1;

    //工作线程数
    private int mWorkerNum;
    //工作线程池
    private Worker[] mWorkers;
    //任务队列
    private PriorityTaskQueue<Runnable> mTaskQueue;
    //线程池状态
    private boolean mRunning;


    public FixedThreadPool() {
        this(DEFAULT_WORKER_NUM);
    }

    public FixedThreadPool(int workerNum) {
        if (workerNum > MAX_WORKER_NUM) {
            workerNum = MAX_WORKER_NUM;
        } else if (workerNum < MIN_WORKER_NUM) {
            workerNum = MIN_WORKER_NUM;
        } else {
            workerNum = workerNum;
        }

        initThreadPool();
    }

    private void initThreadPool() {
        mTaskQueue = new PriorityTaskQueue<>();
        for (int i = 0; i < mWorkerNum; i++) {
            Worker worker = new Worker(mTaskQueue);
            mWorkers[i] = worker;
        }
    }

    @Override
    public void start() {
        if (mRunning) {
            throw new ArgumentException("threadpool is already running");
        }

        mRunning = true;
        for (int i = 0; i < mWorkerNum; i++) {
            mWorkers[i].start();
        }
    }

    @Override
    public void shutdown() {
        mRunning = false;
        for (int i = 0; i < mWorkerNum; i++) {
            mWorkers[i].shutdown();
        }
    }

    @Override
    public void execute(Runnable task) {
        synchronized (mTaskQueue){
            mTaskQueue.add(task);
            mTaskQueue.notifyAll();
        }
    }
}
