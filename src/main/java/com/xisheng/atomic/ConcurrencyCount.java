package com.xisheng.atomic;

import com.xisheng.annotation.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhengxisheng on 2018/5/9.
 * 累加
 */
@NoThreadSafe
@Slf4j
public class ConcurrencyCount {

    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    //计数器
    public static int count = 0;

    public static void main(String[] args) throws Exception{
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量,控制并发执行线程数
        Semaphore semaphore = new Semaphore(threadTotal);
        //控制主线程执行
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0;i < clientTotal;i++){
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
        count++;
    }
}
