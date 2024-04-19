package com.lgfei.javabagu.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureAndCallableExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable1 = () ->{
            System.out.println("Entered callable1");
            Thread.sleep(2000);
            return "return from callable1";
        };

        FutureTask<String> futureTask = new FutureTask<>(callable1);
        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("do something else callable1 is getting executed");
        System.out.println("retrieved:" + futureTask.get());

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable2 = () ->{
            System.out.println("Entered callable2");
            Thread.sleep(2000);
            return "return from callable2";
        };
        System.out.println("Submit callable2");
        Future<String> future = executorService.submit(callable2);

        System.out.println("do something else callable2 is getting executed");
        System.out.println("retrieved:" + future.get());

        executorService.shutdown();
    }
}
