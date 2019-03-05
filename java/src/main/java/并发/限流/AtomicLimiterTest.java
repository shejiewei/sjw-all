package 并发.限流;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2019/3/5.
 */
public class AtomicLimiterTest {


    public static final AtomicInteger atomic=new AtomicInteger(0);

    private  void actomicLimiter(){
        if (atomic.get()>=3){
            System.out.println(LocalDateTime.now()+"-"+Thread.currentThread()+"拒绝");

        }
        else {
            atomic.incrementAndGet();

            System.out.println(LocalDateTime.now() + "-" + Thread.currentThread().getName() + "通过");

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                atomic.decrementAndGet();
            }


        }}

        @Test
        public void testAtomic() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i=0;i<5;i++){
            executorService.execute(this::actomicLimiter);
        }
        TimeUnit.SECONDS.sleep(5);
        }


    }




