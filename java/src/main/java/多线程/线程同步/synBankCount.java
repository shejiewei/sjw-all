package 多线程.线程同步;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class synBankCount {

//同步方法块 使用synchronized关键字来修饰static方法，此时调用该方法将会锁住整个类。
    private int count=0;

    public synchronized void addMoney(int money){
        count+=money;
        System.out.println("存入："+money);
        System.out.println("账户的余额是："+count);
    }
    public synchronized void getMoney(int money){
        if(count<money){
            System.out.println("余额不足");
            System.out.println("账户的余额是："+count);
            return;
        }
        count-=money;

        System.out.println("取出"+money);
        System.out.println("账户的余额是："+count);

    }


}
