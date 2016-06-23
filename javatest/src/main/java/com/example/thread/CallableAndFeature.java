package com.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Created by zhaoningqiang on 16/6/14.
 */
public class CallableAndFeature {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future submit = executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String runnable = "Runnable";
            }
        });

        System.out.println("test submit = "+submit);
       Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "Callable";
            }
        });

        System.out.println("test future = "+future.get().toString());
        for (int i = 0; i < 10 ; i++) {
            System.out.println("   test  0000 ---- i "+i);

        }

        CompletionService<Integer> service = new ExecutorCompletionService<Integer>(Executors.newFixedThreadPool(3));
        for (int i = 0; i < 10; i++) {
            final int seq = i;
            service.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    Thread.sleep(seq *100);
                    return seq;
                }
            });
        }

        for (int j = 0; j < 10 ; j++){
            Future<Integer> take = service.take();
            int seq = take.get();
            System.out.println("   seq = "+seq );
        }



        CompletionService<Integer> singleService = new ExecutorCompletionService<Integer>(Executors.newFixedThreadPool(1));
        singleService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                Thread.sleep(3000);
                return 1000;
            }
        });
        Future<Integer> take = singleService.take();
        int seq = take.get();
        System.out.println("1000   seq = "+seq );
        for (int i = 0; i < 10 ; i++) {
            System.out.println("   test   ---- i "+i);

        }

    }
}
