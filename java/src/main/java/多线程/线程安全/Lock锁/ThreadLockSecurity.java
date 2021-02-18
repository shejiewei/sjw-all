package 多线程.线程安全.Lock锁;

/**
 * Created by shejiewei on 2021/2/15.
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLockSecurity {
    static int tickets = 15;

    class SellTickets implements Runnable {
        Lock lock = new ReentrantLock();

        @Override
        public void run() {
            // Lock锁机制
            while (tickets > 0) {
                try {
                    lock.lock();
                    if (tickets <= 0) {
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + "--->售出第：  " + tickets + " 票");
                    tickets--;
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } finally {
                    lock.unlock();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (tickets <= 0) {
                System.out.println(Thread.currentThread().getName() + "--->售票结束！");
            }

        }
    }


    public static void main(String[] args) {
        SellTickets sell = new ThreadLockSecurity().new SellTickets();
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