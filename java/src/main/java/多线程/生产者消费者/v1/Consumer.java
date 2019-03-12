package 多线程.生产者消费者.v1;

/**
 * Created by shejiewei on 2019/2/12.
 */



import java.util.List;
import java.util.Queue;

/**
 * 消费者
 *
 * @author ctk
 *
 */

public class Consumer implements Runnable{

    private Queue<Integer> queue;
    private int maxSize;

    public Consumer(Queue<Integer> queue, int maxSize){
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                while (queue.isEmpty()){
                    System.out.println("Queue is Empty");
                    try{
                        queue.wait();
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                }
                int v = queue.remove();
                System.out.println("Consume " + v);
                queue.notifyAll();
            }
        }
    }
}
