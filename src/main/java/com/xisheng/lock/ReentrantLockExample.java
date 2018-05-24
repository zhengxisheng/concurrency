package com.xisheng.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhengxisheng on 2018/5/21.
 *
 * 1.ReentrantLoak  AQS volatile state = 0/1判断是否获得锁
 *
 * 2.底层维护双向队列存贮阻塞线程，通过CAS进行值的交换
 *
 * 3.ReentrantLock 可以使用公平锁和非公平锁
 *
 * 4.公平锁tryAcquire会查看队列中是否有等待线程,非公平锁不会
 *
 */
@Slf4j
public class ReentrantLockExample {

    public static int clientTotal = 5000;
    public static int threadTotal = 200;
    private final static Lock lock = new ReentrantLock();
    private static int count = 0;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add();
                        semaphore.release();
                    } catch (InterruptedException e) {
                        log.error("exception",e);
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add(){
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
}
