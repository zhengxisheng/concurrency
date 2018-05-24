package com.xisheng.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhengxisheng on 2018/5/24.
 */
@Slf4j
public class CyclicBarrierExample1 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
        log.info("callback is running");
    });

    public static void main(String[] args) throws Exception{
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++){
            final int num = i;
            Thread.sleep(1000);
            executor.execute(()->{
                try {
                    race(num);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        executor.shutdown();
    }
    private static void race(int num) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready",num);
        cyclicBarrier.await();
        log.info("{} continue",num);
    }
}
