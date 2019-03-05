package 并发.限流;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/3/5.
 */
public class RateLimiterTest {


            private static final RateLimiter limiter=RateLimiter.create(2);
           private static  int number=10;
              private  void ratelimiter(){
                  double acquire = limiter.acquire(1);
                   number--;
                  System.out.println("当前时间="+ LocalDateTime.now()
                   +"-"+Thread.currentThread().getName()+"-阻塞-"+acquire+"通过");
                 System.out.println(Thread.currentThread()+"+减后的数字是:"+number);
              }


              @Test
          public void testdemo() throws InterruptedException {
                  ExecutorService executorService = Executors.newFixedThreadPool(5);
                 for (int i=0;i<5;i++){
                     executorService.execute(this::ratelimiter);
                 }

                  TimeUnit.SECONDS.sleep(5);
              }
}
