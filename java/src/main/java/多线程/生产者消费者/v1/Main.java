package 多线程.生产者消费者.v1;

/**
 * Created by shejiewei on 2019/2/12.
 */


import java.util.LinkedList;

import java.util.Queue;


public class Main {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 10;
        Producer p = new Producer(queue, maxSize);
        Consumer c = new Consumer(queue, maxSize);
        Thread pT = new Thread(p);
        Thread pC = new Thread(c);
        pT.start();
        pC.start();

    }
}
