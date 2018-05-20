package com.xisheng.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhengxisheng on 2018/5/20.
 */

/**
 * synchronized修改一个类,或者修饰一个静态方法,作用于所有对象
 */
@Slf4j
public class SynchronizedExample2 {

    //修改一个类
    public void test1(int j){
        synchronized (SynchronizedExample2.class){
            for (int i = 0;i < 10;i++){
                log.info("test1 {}-{}",j,i);
            }
        }
    }
    //修饰一个静态方法
    public static synchronized void test2(int j){
        for (int i = 0;i < 10;i++){
            log.info("test2 {}-{}",j,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 synchronizedExample1 = new SynchronizedExample2();
        SynchronizedExample2 synchronizedExample2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronizedExample1.test1(1);
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronizedExample2.test2(2);
            }
        });
        executorService.shutdown();
    }
}
