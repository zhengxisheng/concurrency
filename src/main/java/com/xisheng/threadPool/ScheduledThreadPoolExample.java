package com.xisheng.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhengxisheng on 2018/5/27.
 *
 *  super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue());
 */
@Slf4j
public class ScheduledThreadPoolExample {

    public static void main(String[] args) {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run");
            }
        },3, TimeUnit.SECONDS);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("schedule run at fixedRate");
            }
        },1,5,TimeUnit.SECONDS);
    }
}
