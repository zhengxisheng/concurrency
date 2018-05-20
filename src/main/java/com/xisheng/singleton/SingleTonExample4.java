package com.xisheng.singleton;

import com.xisheng.annotation.NoThreadSafe;

/**
 * Created by zhengxisheng on 2018/5/20.
 * 懒汉模式-双重锁
 * 1.memory = allocate()
 * 2.initInstance()初始化对象
 * 3.instance = memory 设置instance指向刚分配的内存
 *
 * jvm和cpu优化,发生了指令重排序
 * 1.memory = allocate()
 * 2.instance = memory 设置instance指向刚分配的内存
 * 3.initInstance()初始化对象
 */
@NoThreadSafe
public class SingleTonExample4 {

    private SingleTonExample4(){}

    private static SingleTonExample4 instance = null;

    public static SingleTonExample4 getInstance(){
        if (instance == null){
            //do sometimg here
            synchronized (SingleTonExample4.class){
                if (instance == null){
                    instance = new SingleTonExample4();
                }
            }
        }
        return instance;
    }
}
