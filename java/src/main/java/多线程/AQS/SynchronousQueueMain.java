package 多线程.AQS;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by shejiewei on 2019/3/13.
 */
public class SynchronousQueueMain {

    public static void main(String[] args){
        SynchronousQueue<Object> sc = new SynchronousQueue<>();

        new Thread(()->{
            while (true){

                try {
                    sc.put(new Random().nextInt(50));
                    System.out.println("添加操作完毕");
                    Thread.sleep(1000);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(
                ()->{
                    while (true){
                        try {
                            System.out.println("______________sc.take:"+sc.take());
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();



    }

}
