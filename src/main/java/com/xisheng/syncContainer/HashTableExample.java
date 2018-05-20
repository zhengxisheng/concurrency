package com.xisheng.syncContainer;

import com.xisheng.annotation.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhengxisheng on 2018/5/20.
 * HashTable almost all the method add synchronized
 *
 */
@NoThreadSafe
@Slf4j
public class HashTableExample {

    //请求总数
    public static int clientTotal = 5000;
    //并发数
    public static int threadTotal = 200;

    private static Map<Integer,Integer> map = new Hashtable<>();

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", map.size());
    }
    private static void add(int i){
        map.put(i,i);
    }
}
