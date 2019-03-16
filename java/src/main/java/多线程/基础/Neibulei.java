package 多线程.基础;

/**
 * Created by shejiewei on 2019/3/16.
 */
public class Neibulei {

    public static void main(String[] args) {


        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    System.out.println("runnable is running ... "); // 打印当前线程的名字
                    try {
                        Thread.sleep(1000); // 休息1000ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }) {
            public void run() {
                while (true) {
                    System.out.println("sub is running ... "); // 打印当前线程的名字
                    try {
                        Thread.sleep(1000); // 休息1000ms
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();


    }
}
