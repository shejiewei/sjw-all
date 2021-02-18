package 多线程.线程安全.Lock锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shejiewei on 2021/2/15.
 */
public class test
{   static int tickets = 15;
   public class Sell implements  Runnable{



       Lock lock=   new ReentrantLock();
        @Override
        public void run() {
            while (tickets > 0) {
                if (tickets <= 0)
                    return;

                try {
                    lock.lock();

                    System.out.println("窗口" + Thread.currentThread().getName() + "卖出第" + tickets + "张票");
                    tickets--;
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

     public static void main(String[] args) {
        Sell sell =new test().new Sell();
         Thread thread1 = new Thread(sell, "1号窗口");
         Thread thread2 = new Thread(sell, "2号窗口");
         Thread thread3 = new Thread(sell, "3号窗口");
         Thread thread4 = new Thread(sell, "4号窗口");
         thread1.start();
         thread2.start();
         thread3.start();
         thread4.start();

      }

}
