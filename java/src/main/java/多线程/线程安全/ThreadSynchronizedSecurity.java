package 多线程.线程安全;

/**
 * Created by shejiewei on 2021/2/15.
 */
public class ThreadSynchronizedSecurity {

    static int tickets = 15;

    class SellTickets implements Runnable {
        @Override
        public void run() {
            while (tickets > 0) {
                // 同步代码块
                synchronized (this) {
                    if (tickets <= 0) {
                        return;
                    }
                    System.out.println(Thread.currentThread().getName() + "--->售出第：  " + tickets + " 票");
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tickets--;
                }
                if (tickets <= 0) {
                    System.out.println(Thread.currentThread().getName() + "--->售票结束！");
                }
            }
        }
    }

    public static void main(String[] args) {
        SellTickets sell = new ThreadSynchronizedSecurity().new SellTickets();
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