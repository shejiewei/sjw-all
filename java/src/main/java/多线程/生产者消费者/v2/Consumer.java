package 多线程.生产者消费者.v2;

import java.util.List;

/**
 * Created by shejiewei on 2019/2/12.
 */
public class Consumer implements Runnable{
    private List<PCData> queue;
    public Consumer(List<PCData> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted())
                    break;
                PCData data = null;
                Main.lock.lock();
                if (queue.size() == 0){
                    Main.full.signalAll();
                    Main.empty.await();
                }
                Thread.sleep(1000);
                data = queue.remove(0);
                Main.lock.unlock();
                System.out.println("消费者ID:"+Thread.currentThread().getId()+" 消费了:"+data.getData()+" result:"+(data.getData()*data.getData()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
