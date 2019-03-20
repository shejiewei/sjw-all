package 多线程.线程同步;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class CASBankCount {

    private volatile int count=0;

    public void addMoney(int money){
        count+=money;
        System.out.println("存入："+money);
        System.out.println("账户的余额是："+count);
    }
    public void getMoney(int money){
        if(count<money){
            System.out.println("余额不足");
            System.out.println("账户的余额是："+count);
            return;
        }
        count-=money;

        System.out.println("取出"+money);
        System.out.println("账户的余额是："+count);

    }

public static void main(String[] args){

    final  CASBankCount bankCount=new CASBankCount();
    new Thread(
            new Runnable() {
                @Override
                public void run() {
                    while(true){
                        bankCount.addMoney(1000);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

    ).start();
    new Thread(
            new Runnable() {
                @Override
                public void run() {
                    while (true){
                        bankCount.getMoney(100);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

    ).start();

    new Thread(
            new Runnable() {
                @Override
                public void run() {
                    while (true){
                        bankCount.getMoney(100);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

    ).start();
    new Thread(
            new Runnable() {
                @Override
                public void run() {
                    while (true){
                        bankCount.getMoney(100);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

    ).start();


}
}
