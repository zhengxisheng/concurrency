package com.xisheng.immutable;

import com.xisheng.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhengxisheng on 2018/5/20.
 *
 * 不可变对象
 *
 * Collections.unmodifiableMap 返回一个可读的 unmodifiableMap
 *
 * 底层更改值的方法类似put，直接抛出异常
 */
@Slf4j
@ThreadSafe
public class ImmutableExample1 {

    private static Map<Integer,Integer> map = new HashMap<>();

    static {
        map.put(1,2);
        map.put(2,3);
        map.put(4,5);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,3);
        log.info("{}",map.get(1));
    }
}
