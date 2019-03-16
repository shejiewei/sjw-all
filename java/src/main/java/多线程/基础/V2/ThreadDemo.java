package 多线程.基础.V2;

/**
 * Created by shejiewei on 2019/3/16.
 */
public class ThreadDemo extends Thread {
    String name;

    private static Object object=new Object();
    public static int number=0;
    public ThreadDemo(String name){
        this.name=name;
    }
    @Override
    public void run() {



            for (int i=0;i<100;i++) {
                number = number + 1;
                System.out.println("当前线程是+" + Thread.currentThread().getName() + "+" + number);

            }



    }
}
