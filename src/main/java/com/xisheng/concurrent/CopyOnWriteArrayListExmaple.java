package com.xisheng.concurrent;

import com.xisheng.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by zhengxisheng on 2018/5/27.
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExmaple {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static List<Integer> list = new CopyOnWriteArrayList();

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Semaphore semaphore = new Semaphore(threadTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i < clientTotal; i++){
            final int num = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        add(num);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        log.info("exception",e);
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}",list.size());
    }

    private static void add(int num){
        list.add(num);
    }
}
