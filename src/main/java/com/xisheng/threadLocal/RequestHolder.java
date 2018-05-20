package com.xisheng.threadLocal;

/**
 * Created by zhengxisheng on 2018/5/20.
 */
public class RequestHolder {

    /**
     * ThreadLocal 线程封闭 以当前线程号为key
     */
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    public static void remove(){
        requestHolder.remove();
    }
}
