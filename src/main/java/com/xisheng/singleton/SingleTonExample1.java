package com.xisheng.singleton;

import com.xisheng.annotation.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by zhengxisheng on 2018/5/20.
 */
@Slf4j
@NoThreadSafe
public class SingleTonExample1 {

    //私有化构造方法
    private SingleTonExample1(){}

    //单例对象
    private static SingleTonExample1 instance = null;

    public static SingleTonExample1 getInstance(){
        if (instance == null){
            instance = new SingleTonExample1();
        }
        return instance;
    }
}
