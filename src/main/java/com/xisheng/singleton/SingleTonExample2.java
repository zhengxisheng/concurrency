package com.xisheng.singleton;

import com.xisheng.annotation.ThreadSafe;

/**
 * Created by zhengxisheng on 2018/5/20.
 * 懒汉模式
 */
@ThreadSafe
public class SingleTonExample2 {

    private SingleTonExample2(){}

    private static SingleTonExample2 instance = null;

    public static synchronized SingleTonExample2 getInstance(){
        if (instance == null){
            instance = new SingleTonExample2();
        }
        return instance;
    }
}
