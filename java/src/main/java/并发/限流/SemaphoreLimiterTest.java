package 并发.限流;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/3/5.
 */
public class SemaphoreLimiterTest {


    private static final Semaphore semaphore=new Semaphore(3);

    private void SemaphoreLimiter() {
        if (semaphore.getQueueLength()>5){
             System.out.println(LocalDateTime.now()+"-"+Thread.currentThread().getName()+"-拒绝");
        } else {

            try {
                semaphore.acquire();
                System.out.println(LocalDateTime.now()+"-"+Thread.currentThread().getName()+"通过");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                semaphore.release();
            }



        }

    }

    @Test
    public  void testSemaphere() throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(10);

        for (int i=0;i<10;i++){
            service.execute(this::SemaphoreLimiter);
        }
        TimeUnit.SECONDS.sleep(5);

    }


}
