package 多线程.生产者消费者.v2;

/**
 * Created by shejiewei on 2019/2/12.
 */
import java.util.List;
import java.util.Random;
/**
 * 生产者
 * @author ctk
 *
 */
public class Producter implements Runnable{
    private List<PCData> queue;
    private int len;
    public Producter(List<PCData> queue,int len){
        this.queue = queue;
        this.len = len;
    }
    @Override
    public void run() {
        try{
            while(true){
                if(Thread.currentThread().isInterrupted())
                    break;
                Random r = new Random();
                PCData data = new PCData();
                data.setData(r.nextInt(500));
                Main.lock.lock();
                if(queue.size() >= len)
                {
                    Main.empty.signalAll();
                    Main.full.await();
                }
                Thread.sleep(1000);
                queue.add(data);
                Main.lock.unlock();
                System.out.println("生产者ID:"+Thread.currentThread().getId()+" 生产了:"+data.getData());
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}