package com.jiulu.lotus.base;

/**
 * Created by zhanghongjun on 2017/11/18.
 */

public interface TaskQueue<E> {
    E poll();

    void add(E e);

    void add(E e,int priority);

    boolean isEmpty();

    int size();
}
