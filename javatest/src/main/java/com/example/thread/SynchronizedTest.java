package com.example.thread;

/**
 * Created by zhaoningqiang on 16/6/5.
 */
public class SynchronizedTest {

    public static void main(String args[]){

        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i = 0 ; i < 50 ;i ++){
                   business.sub();
               }
            }
        }).start();


        for (int i = 0 ; i < 50 ;i ++){
            business.main();
        }


    }



     static class Business{
         private boolean isShouldSub = true;


        public synchronized void main(){
            if (isShouldSub){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0 ; i < 20 ; i++){
                System.out.println("main index = "+i);
            }
            isShouldSub = true;
            notify();
        }


        public synchronized void sub(){
            if (!isShouldSub){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i = 0 ;i <10 ; i++){
                System.out.println("sub index = "+i);
            }
            isShouldSub = false;
            notify();
        }
    }
}
