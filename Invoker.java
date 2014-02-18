package com.consumer;

public class Invoker {

    private PollingQueue pollingQueue;

    public Invoker() {
        pollingQueue = new PollingQueue();
        Thread tProd = new Producer("Producer");
//        new Producer("Two").start();
//        new Producer("Three").start();
        tProd.setPriority(5);
        tProd.start();
        
        new Consumer("Consumer 1").start();
        new Consumer("Consumer 2").start();
        new Consumer("Consumer 3").start();
        new Consumer("Consumer 4").start();
        new Consumer("Consumer 5").start();
        new Consumer("Consumer 6").start();
        new Consumer("Consumer 7").start();
        new Consumer("Consumer 8").start();
        new Consumer("Consumer 9").start();
        new Consumer("Consumer 10").start();
    }

    public class Consumer extends Thread {

        private int val = 0;
        String name;

        Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 8; i++) {
                pollingQueue.remove(name);
            }
        }

    }

    public class Producer extends Thread {

        private int val = 0;
        String name;

        Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 60; i++) {
                pollingQueue.add(name, i);
            }
        }

    }

    public static void main(String[] args) {
        new Invoker();
    }

}
