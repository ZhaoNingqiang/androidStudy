package com.example.thread;

import java.util.Random;

/**
 * Created by zhaoningqiang on 16/6/5.
 * 线程范围内共享数据
 */
public class ThreadScopeShareData {
    private static int data = 0;
    public static void main(String agrs[]){
        for (int  i = 0 ; i < 2 ; i ++){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(System.currentTimeMillis());
                    data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put data = " + data);
                    new A().get();
                    new B().get();

                }
            }).start();
        }


    }
   static class A{
       public void get(){
           System.out.println("A from "+Thread.currentThread().getName() +" get data = " + data);
       }
   }

    static class B{
        public void get(){
            System.out.println("B from "+Thread.currentThread().getName() +" get data = " + data);
        }
    }
}
