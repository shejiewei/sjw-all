package java细节大全.生产者消费者;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class test {

    public static void main(String[] args){

        Storage storage = new Storage();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //executorService.submit(new Consumer( 30,storage));
        //executorService.submit(new Consumer( 10,storage));
        //executorService.submit(new Consumer(20,storage));
      executorService.submit(new Producer(100,storage));
      executorService.submit(new Producer(100,storage));
      executorService.submit(new Producer(100,storage));
      executorService.submit(new Producer(100,storage));

        executorService.shutdown();
    }
}
