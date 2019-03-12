package 多线程.线程池;

/**
 * Created by shejiewei on 2019/3/10.
 */
import java.util.concurrent.ExecutorService;
  import java.util.concurrent.Executors;

public class ThreadPoolExecutorTest {
      public static void main(String[] args) {
               //创建一个可缓存线程池
                 ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                 for (int i = 0; i < 10; i++) {
                         try {
                                 //sleep可明显看到使用的是线程池里面以前的线程，没有创建新的线程
                                 Thread.sleep(1000);
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                            }
                        cachedThreadPool.execute(new Runnable() {
                 public void run() {
                                         //打印正在执行的缓存线程信息
                                         System.out.println(Thread.currentThread().getName()+"正在被执行");
                                     }
             });
                     }
            }
 }
