package com.example.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaoningqiang on 16/7/8.
 */

public class CountdownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end  = new CountDownLatch(3);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0 ; i < 3; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {

                        System.out.println(Thread.currentThread().getName()+" 正在等待 "+start.getCount());
                        start.await();
                        Thread.sleep((long) (Math.random()*10000));
                        System.out.println(Thread.currentThread().getName()+"  已经到达 "+start.getCount());
                        end.countDown();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();

        Thread.sleep((long) (Math.random()*10000));
        System.out.println(Thread.currentThread().getName()+"  马上发布命令 "+end.getCount());
        start.countDown();
        System.out.println(Thread.currentThread().getName()+"  等待发布发布结果 "+end.getCount());
        end.await();
        System.out.println(Thread.currentThread().getName()+"  发布结果 "+end.getCount());






    }
}
