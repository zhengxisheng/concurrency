package com.xisheng.syncContainer;

import com.xisheng.annotation.NoThreadSafe;

import java.util.Vector;

/**
 * Created by zhengxisheng on 2018/5/20.
 */
@NoThreadSafe
public class VectorExample1 {

    public static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {

            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            });
            thread.start();
            thread1.start();
        }
    }
}
