package 多线程.线程同步;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class synBankTest {
    public static void main(String[] args){
        //final  BankCount bankCount=new BankCount();
       final  synBankCount bankCount=new synBankCount();
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
