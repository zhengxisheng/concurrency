package com.xisheng.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhengxisheng on 2018/5/24.
 */
@Slf4j
public class ConditionLockExample {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    log.info("wait signal");
                    condition.await();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                log.info("get signal");
                lock.unlock();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                log.info("get lock");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                condition.signalAll();
                log.info("send signal");
                lock.unlock();
            }
        }).start();
    }
}
