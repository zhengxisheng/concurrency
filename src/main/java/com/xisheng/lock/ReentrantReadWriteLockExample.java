package com.xisheng.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zhengxisheng on 2018/5/24.
 *
 * 当有写线程时,则写线程独占同步状态
 *
 * 当只有读线程时,则多个读线程可以共享同步状态
 *
 * state被拆分为高16位，低16位
 *
 * 高16位表示当前读锁的占有量，低16位表示写锁的占有量
 *
 * 注意：ReentrantReadWriteLock会出现写线程的饥饿，也就是写线程一直获取不到锁，因为读写互拆
 *
 * stampLock解决了该问题
 */
@Slf4j
public class ReentrantReadWriteLockExample {

    private static Map<String,Object> map = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public Object get(String key){
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }
    public Object put(String key,Object value){
        writeLock.lock();
        try {
            return map.put(key,value);
        } finally {
            writeLock.unlock();
        }
    }

}
