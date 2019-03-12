package 多线程.生产者消费者.v1;

/**
 * Created by shejiewei on 2019/2/12.
 */



import java.util.Queue;
import java.util.Random;

/**
 * 生产者
 *
 * @author MacBook
 *
 */
public class Producer implements Runnable{

    private Queue<Integer> queue;
    private int maxSize;

    public Producer(Queue<Integer> queue, int maxSize){
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                while (queue.size() == maxSize){
                    try{
                        System.out.println("Queue is Full");
                        queue.wait();
                    }catch (InterruptedException ie){
                        ie.printStackTrace();
                    }
                }
                Random random = new Random();
                int i = random.nextInt();
                System.out.println("Produce " + i);
                queue.add(i);
                queue.notifyAll();
            }
        }
    }
}
