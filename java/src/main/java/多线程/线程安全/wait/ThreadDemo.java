package 多线程.线程安全.wait;

/**
 * Created by shejiewei on 2021/2/15.
 */
public class ThreadDemo {

    static final Object obj = new Object();

    //第一个子线程
    static class ThreadA implements Runnable {
        @Override
        public void run() {
            int count = 5;
            while (count > 0) {
                synchronized (ThreadDemo.obj) {
                    System.out.println("A-----" + count);
                    count--;
                    synchronized (ThreadDemo.obj) {
                        //notify()方法会唤醒因为调用对象的wait()而处于等待状态的线程，从而使得该线程有机会获取对象锁。
                        //调用notify()后，当前线程并不会立即释放锁，而是继续执行当前代码，直到synchronized中的代码全部执行完毕，
                        ThreadDemo.obj.notify();
                        try {
                            ThreadDemo.obj.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }
            }
        }

    }

    static class ThreadB implements Runnable {
        @Override
        public void run() {
            int count = 5;
            while (count > 0) {
                synchronized (ThreadDemo.obj) {
                    System.out.println("B-----" + count);
                    count--;
                    synchronized (ThreadDemo.obj) {
                        //notify()方法会唤醒因为调用对象的wait()而处于等待状态的线程，从而使得该线程有机会获取对象锁。
                        //调用notify()后，当前线程并不会立即释放锁，而是继续执行当前代码，直到synchronized中的代码全部执行完毕，
                        ThreadDemo.obj.notify();
                        try {
                            ThreadDemo.obj.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ThreadB()).start();
        new Thread(new ThreadA()).start();

    }

}