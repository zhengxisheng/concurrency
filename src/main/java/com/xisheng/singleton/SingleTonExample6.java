package com.xisheng.singleton;

import com.xisheng.annotation.ThreadSafe;

/**
 * Created by zhengxisheng on 2018/5/20.
 *
 * 饿汉模式 - 类加载的时候进行创建
 */
@ThreadSafe
public class SingleTonExample6 {

    private SingleTonExample6(){}

    private static SingleTonExample6 instance = null;

    static {
        new SingleTonExample6();
    }

    public static SingleTonExample6 getInstance(){
        return instance;
    }
}
