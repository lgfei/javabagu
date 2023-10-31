package com.lgfei.javabagu.concurrent;

/**
 * 用Synchronized实现多线程顺序打印1~100
 * 虽然能按顺序打印，但是3个线程分配不均匀（旱的旱死涝的涝死）
 */
public class SequencePrintBySynchronized {
    private static int i = 1;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++){
            new Thread(new Printer(i)).start();
        }
    }

    static class Printer implements Runnable {

        private final int index;

        public Printer(int index){
            this.index = index;
        }

        @Override
        public void run() {
            while (true){
                synchronized (Printer.class){
                    if(i >= 101){
                        break;
                    }
                    System.out.println("Printer-" + index + ":" + i);
                    i++;
                }
            }
        }
    }
}

