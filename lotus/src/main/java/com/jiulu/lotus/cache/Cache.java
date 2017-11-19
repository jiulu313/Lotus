package com.jiulu.lotus.cache;

/**
 * Created by zhanghongjun on 2017/11/19.
 */

public interface Cache<K,V> {
    void save(K key,V value);
    V get(K key);
}
