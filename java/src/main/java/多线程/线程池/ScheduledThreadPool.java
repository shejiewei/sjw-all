package 多线程.线程池;

/**
 * Created by shejiewei on 2019/3/10.
 */
import java.util.concurrent.Executors;
  import java.util.concurrent.ScheduledExecutorService;
  import java.util.concurrent.TimeUnit;

          public class ScheduledThreadPool {
      public static void main(String[] args) {
                 //创建一个定长线程池，支持定时及周期性任务执行——延迟执行
                 ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
                 //延迟1秒执行
                 scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                                 System.out.println("延迟1秒执行");
                            }
        }, 1, TimeUnit.SECONDS);
             }
 }
