package com.lgfei.javabagu.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用ReentrantLock实现多线程顺序打印1~100
 * 既能按顺序打印，而且3个线程轮流执行
 */
public class SequencePrintByReentrantLock {

    private static int i = 0;
    private static final ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) {
        final List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            Condition condition = LOCK.newCondition();
            conditions.add(condition);
            new Printer(i, conditions).start();
        }
    }

    static class Printer extends Thread {

        private int index;
        private List<Condition> conditions;

        public Printer(int index, List<Condition> conditions){
            super("Printer-" + index);
            this.index = index;
            this.conditions = conditions;
        }

        private void signalNext(){
            int nextIndex = (index + 1) % conditions.size();
            conditions.get(nextIndex).signal();
        }

        @Override
        public void run() {
            while (true){
                LOCK.lock();
                try {
                    if(i % 3 != index) {
                        conditions.get(index).await();
                    }
                    if( i > 100){
                        signalNext();
                        return;
                    }
                    System.out.println(this.getName() + ":" + i);
                    i++;
                    signalNext();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    LOCK.unlock();
                }
            }
        }
    }
}
