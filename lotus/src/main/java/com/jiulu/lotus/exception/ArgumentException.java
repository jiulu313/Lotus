package com.jiulu.lotus.exception;

/**
 * Created by zhanghongjun on 2017/11/18.
 */

public class ArgumentException extends RuntimeException{
    public ArgumentException(){
        super();
    }

    public ArgumentException(String s){
        super(s);
    }
}
