package 多线程.基础.V2;

/**
 * Created by shejiewei on 2019/3/16.
 */
public class runna implements Runnable {
    @Override
    public void run() {


        System.out.println(Thread.currentThread().getName());
    }
}
