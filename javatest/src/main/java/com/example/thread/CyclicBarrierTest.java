package com.example.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaoningqiang on 16/7/7.
 */

public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程:" + Thread.currentThread().getName() + " 即将到达集合点1,当前已有: " + (cyclicBarrier.getNumberWaiting() + 1));
                        cyclicBarrier.await();

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程:" + Thread.currentThread().getName() + " 即将到达集合点2,当前已有: " + (cyclicBarrier.getNumberWaiting() + 1));
                        cyclicBarrier.await();

                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("线程:" + Thread.currentThread().getName() + " 都到集合点3了,继续走,当前已有: " + (cyclicBarrier.getNumberWaiting() + 1));
                        cyclicBarrier.await();
                    } catch (Exception e) {

                    }


                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}
