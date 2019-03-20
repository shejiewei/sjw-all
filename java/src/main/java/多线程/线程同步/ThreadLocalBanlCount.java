package 多线程.线程同步;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class ThreadLocalBanlCount {

      private static ThreadLocal<Integer> count=new ThreadLocal<Integer>(){
          protected  Integer intialValue(){
              return 0;
          }
      };

    public void addMoney(int money){
        count.set(count.get()+money);
        System.out.println("存入："+money);
        System.out.println("账户的余额是："+count.get());
    }
    public void getMoney(int money){
        if(count.get()<money){
            System.out.println("余额不足");
            System.out.println("账户的余额是："+count);
            return;
        }
        count.set(count.get()-money);

        System.out.println("取出"+money);
        System.out.println("账户的余额是："+count.get());

    }

    public static void main(String[] args) {

    //    由于ThreadLocal管理的局部变量对于每个线程都会产生一份单独的拷贝，因此ThreadLocal适合用来管理与线程相关的关联状态，
        // 典型的管理局部变量是private static类型的，比如用户ID、事物ID，我们的服务器应用框架对于每一个请求都是用一个单独的线程中处理，
        // 所以事物ID对每一个线程是唯一的，此时用ThreadLocal来管理这个事物ID，就可以从每个线程中获取事物ID了。

        final ThreadLocalBanlCount bankCount = new ThreadLocalBanlCount();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
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
                        while (true) {
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
