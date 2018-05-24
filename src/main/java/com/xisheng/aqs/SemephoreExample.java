package com.xisheng.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhengxisheng on 2018/5/24.
 */
@Slf4j
public class SemephoreExample {

    private static final int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++){
            final int num = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test(num);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
    }
    private static void test(int num) throws Exception{
        log.info("thread num is :{}",num);
        Thread.sleep(1000);
    }
}
