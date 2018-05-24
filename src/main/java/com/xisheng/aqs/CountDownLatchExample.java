package com.xisheng.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhengxisheng on 2018/5/24.
 *
 */
@Slf4j
public class CountDownLatchExample {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0;i < threadCount; i++){
            final int num = i;
            executorService.execute(()->{
                try {
                    test(num);
                } catch (Exception e) {
                    log.error("exception",e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("finish");
        executorService.shutdown();
    }
    private static void test(int num) throws Exception{
        Thread.sleep(1000);
        log.info("threadNum:{}",num);
        Thread.sleep(1000);
    }
}
