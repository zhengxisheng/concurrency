package com.xisheng.singleton;

import com.xisheng.annotation.ThreadSafe;

/**
 * Created by zhengxisheng on 2018/5/20.
 * 饿汉模式
 * 单例实例在类装载的时候进行创建
 */
@ThreadSafe
public class SingleTonExample3 {

    private SingleTonExample3(){}

    private static SingleTonExample3 instance = new SingleTonExample3();

    public static SingleTonExample3 getInstance(){
        return instance;
    }
}
