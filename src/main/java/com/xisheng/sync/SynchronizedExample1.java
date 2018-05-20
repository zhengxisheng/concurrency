package com.xisheng.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhengxisheng on 2018/5/20.
 */

/**
 * synchronized修改一个代码块,修饰一个非静态方法,属于对象锁,作用于调用对象
 */
@Slf4j
public class SynchronizedExample1 {

    //修饰一个代码块
    public void test1(int j){
        synchronized (this){
            for (int i = 0;i < 10; i++){
                log.info("test1 {}- {}",j,i);
            }
        }
    }
    //修饰一个方法
    public synchronized void test2(int j){
        for (int i =0;i < 10; i++){
            log.info("test2 {}- {}",j,i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronizedExample2.test1(1);
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                synchronizedExample1.test2(2);
            }
        });
        executorService.shutdown();
    }
}
