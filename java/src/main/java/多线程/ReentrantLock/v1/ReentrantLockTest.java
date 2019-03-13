package 多线程.ReentrantLock.v1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shejiewei on 2019/3/13.
 */
public class ReentrantLockTest extends Thread{

    public static  ReentrantLock reentrantLock=new ReentrantLock();
    public static String name;
    private  static  int i=0;
    public ReentrantLockTest(String name)
    {
        this.name=name;
    }
    @Override
    public void run(){
        for (int j=0;j<100;j++){
            reentrantLock.lock();
            try {
                System.out.println(this.getName() + "::" + j);
                i++;
            }finally {
                reentrantLock.unlock();
            }

        }

    }


  public static void main(String[] args) throws InterruptedException {

      ReentrantLockTest a = new ReentrantLockTest("A");
      ReentrantLockTest b = new ReentrantLockTest("B");

      a.start();
      b.start();
      a.join();
      b.join();

      System.out.println(i);

  }

}
