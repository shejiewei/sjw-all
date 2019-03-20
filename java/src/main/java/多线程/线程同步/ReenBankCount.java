package 多线程.线程同步;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class ReenBankCount {


   private Lock lock=new ReentrantLock();
    private int count=0;

    public void addMoney(int money){
        lock.lock();
        try {
            count += money;
            System.out.println("存入：" + money);
            System.out.println("账户的余额是：" + count);
        }finally {
            lock.unlock();
        }

        }
    public void getMoney(int money){
         lock.lock();
        try {
            if (count < money) {
                System.out.println("余额不足");
                System.out.println("账户的余额是：" + count);
                return;
            }
            count -= money;

            System.out.println("取出" + money);
            System.out.println("账户的余额是：" + count);
        }finally {
            lock.unlock();
        }
    }




}
