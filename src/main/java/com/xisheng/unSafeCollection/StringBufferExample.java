package com.xisheng.unSafeCollection;

import com.xisheng.annotation.NoThreadSafe;
import com.xisheng.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by zhengxisheng on 2018/5/20.
 * stringbuffer synchronizd同步方法
 *
 */
@ThreadSafe
@Slf4j
public class StringBufferExample {

    //请求总数
    public static int clientTotal = 5000;
    //并发数
    public static int threadTotal = 200;

    private static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", stringBuffer.length());
    }
    private static void add(){
        stringBuffer.append("1");
    }
}
