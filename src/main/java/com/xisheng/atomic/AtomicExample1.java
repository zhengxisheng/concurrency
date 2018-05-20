package com.xisheng.atomic;

import com.xisheng.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhengxisheng on 2018/5/9.
 * 使用AtomicInteger实现线程安全
 * 底层是基于CAS实现线程安全 compareAndSwapInt
 * 会出现ABA问题,可以使用增加版本号或者使用AtomicStampReference类
 *
 */
@Slf4j
@ThreadSafe
public class AtomicExample1 {

    //请求总数
    public static int clientTotal = 5000;
    //并发执行线程数
    public static int threadTotal = 200;
    //计数器
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //信号量
        Semaphore semaphore = new Semaphore(threadTotal);
        //控制主线程执行
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i=0;i<clientTotal;i++){
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
    public static void add(){
        count.incrementAndGet();
    }
}
