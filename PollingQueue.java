package com.consumer;

import java.util.ArrayList;
import java.util.List;

public class PollingQueue {

    private int BUFFER_SIZE = 60;
    private static List<Integer> queue;
    private boolean isFull;
    private boolean isEmpty;
    private int itemCount;

    public PollingQueue() {
        itemCount = 0;
        queue = new ArrayList<Integer>();
        isFull = false;
        isEmpty = true;

    }

    public synchronized void add(String name, int value) {
        while (isFull) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("wake adding");
            }
        }
        queue.add(value);
        System.out.println(name + " - Adding : " + value);
        itemCount++;
        isEmpty = false;
        notify();
        if (itemCount == BUFFER_SIZE) {
            isFull = true;
        }
    }

    public synchronized void remove(String name) {
        while (isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("wake removing");
            }
        }
//        if (queue.size() > 0) {
            System.out.println(name + " - Consuming : " + queue.remove(0));
            itemCount--;
            isFull = false;
            notify();
            if (itemCount == 0) {
                isEmpty = true;
            }
//        }
    }
}
