package com.xisheng.immutable;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengxisheng on 2018/5/20.
 *
 * 不可变对象
 *
 * final修饰的常量不可变值不可变
 *
 * final修饰的引用型变量指向的对象不可变，对象的属性可变
 */
@Slf4j
public class ImmutableExample {

    private final static Map<Integer,Integer> map = new HashMap<>();
    private final static Integer num = 1;
    static {
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
    }
    public static void main(String[] args) {
        //num=2;
        //map = new HashMap<>();
        map.put(1,3);
        log.info("{}",map.get(1));
    }
//    public void test(final int a){
//        a=2;
//    }
}
