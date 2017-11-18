package com.jiulu.lotus.test;

/**
 * Created by zhanghongjun on 2017/11/18.
 */

public class Student {
    public String name;
    public int store;

    public Student(String name,int store){
        this.name = name;
        this.store = store;
    }

    @Override
    public String toString() {
        return name + "=" + store;
    }
}
