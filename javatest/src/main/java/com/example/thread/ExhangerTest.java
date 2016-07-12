package com.example.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhaoningqiang on 16/7/8.
 */

public class ExhangerTest {
    public static void main(String[] args){
        ExecutorService executorService = Executors.newCachedThreadPool();
        Exchanger<String> exchage = new Exchanger<String>();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = "zhaoningqinag";
                    String data2 = exchage.exchange(data1);
                    System.out.println(data1 +" exchange "+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = "lijianfeng";
                    String data2 = exchage.exchange(data1);
                    System.out.println(data1 +" exchange "+data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        executorService.shutdown();
    }
}
