package com.xisheng.syncContainer;

import com.xisheng.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhengxisheng on 2018/5/20.
 *
 * vector almost all the method add synchronized
 */
@Slf4j
@ThreadSafe
public class VectorExample {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    private static List<Integer> list = new Vector<>();

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0;i < clientTotal;i++){
            final int num =i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add(num);
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
        log.info("size {}",list.size());
    }
    private static void add(int i){
        list.add(i);
    }
}
