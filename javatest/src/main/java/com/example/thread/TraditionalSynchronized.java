package com.example.thread;

/**
 * Created by zhaoningqiang on 16/6/5.
 * 测试线程互斥关键字synchronized
 * 在静态方法中用的是类的字节码对象
 * 非静态方法中用的是this对象
 *
 *
 */
public class TraditionalSynchronized {
    public static void main(String args[]){
        new TraditionalSynchronized().init();

    }

    private void init(){
        final Output output = new Output();
        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    output.print("123456789");
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Output().print("abcdefghijk");
                }
            }
        }.start();
    }


    class Output{
        private synchronized void print(String name){
            int len = name.length();
            for (int i = 0 ; i < len ; i++){
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}
