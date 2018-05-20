package com.xisheng.singleton;

import com.xisheng.annotation.ThreadSafe;

/**
 * Created by zhengxisheng on 2018/5/20.
 *
 *  懒汉模式 双重锁+volatile禁止指令重排
 *  1.memory = allocate() 分配对象的内存空间
 *  2.initInstance() 初始化对象
 *  3.instance = memory 设置instance指向刚分配的内存
 */
@ThreadSafe
public class SingleTonExample5 {

    private SingleTonExample5(){}

    private volatile static SingleTonExample5 instance = null;

    public static SingleTonExample5 getInstance(){
        if (instance == null){
            synchronized (SingleTonExample5.class){
                instance = new SingleTonExample5();
            }
        }
        return instance;
    }
}
