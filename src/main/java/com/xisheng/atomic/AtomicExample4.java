package com.xisheng.atomic;

import com.xisheng.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by zhengxisheng on 2018/5/20.
 */
@Slf4j
@ThreadSafe
public class AtomicExample4 {

    public static AtomicBoolean isHappend = new AtomicBoolean(false);

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

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
                        test();
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
    }
    private static void test(){
        if (isHappend.compareAndSet(false,true)){
            log.info("execute");
        }
    }
}
