package 多线程.生产者消费者.v1;

/**
 * Created by shejiewei on 2019/2/12.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        List<PCData> queue = new ArrayList<PCData>();
        int length = 10;
        Producer p1 = new Producer(queue,length);
        Producer p2 = new Producer(queue,length);
        Producer p3 = new Producer(queue,length);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);

    }
}
