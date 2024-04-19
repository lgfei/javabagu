package com.lgfei.javabagu.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureExample {
    public static void main(String[] args) {
        sum();
        eventDriven();
    }

    /*
     * 将 List 中每个元素乘以 2 ,再求和
     */
    private static void sum(){
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<CompletableFuture<Integer>> futures = nums.stream()
                .map(value -> CompletableFuture.supplyAsync(() -> {
                    int result = value * 2;
                    System.out.println(value + "*2=" + result);
                    return result;
                }))
                .collect(Collectors.toList());
        CompletableFuture<Integer> sumFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApplyAsync(v -> {
                    int sum = futures.stream().mapToInt(CompletableFuture::join).sum();
                    return sum;
                });
        int sum = sumFuture.join();
        System.out.println("sum=" + sum);
    }

    /*
     * 事件驱动编程
     * 开启 3 个工作线程监听主线程的返回结果，主线程完成之后 3 个工作线程异步开始处理自己的任务
     */
    private static void eventDriven(){
        CompletableFuture<String> future = new CompletableFuture<>();
        future.thenAcceptAsync(result -> System.out.println(Thread.currentThread().getName() + " | Event 1 proccessed -> " + result));
        future.thenAcceptAsync(result -> System.out.println(Thread.currentThread().getName() + " | Event 2 proccessed -> " + result));
        future.thenAcceptAsync(result -> System.out.println(Thread.currentThread().getName() + " | Event 3 proccessed -> " + result));
        future.complete(Thread.currentThread().getName() + " | My job is done!");
    }
}
