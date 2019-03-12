package 多线程.futuretask;

/**
 * Created by shejiewei on 2019/3/10.
 */
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class demo {



    /**
     * Callalbe和Runnable的区别
     *
     * Runnable run方法是被线程调用的，在run方法是异步执行的
     *
     * Callable的call方法，不是异步执行的，是由Future的run方法调用的
     *
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        Callable<Integer> call = new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println("正在计算结果...");
                Thread.sleep(3000);
                return 1;
            }
        };

        FutureTask<Integer> task = new FutureTask<>(call);

        Thread thread = new Thread(task);
        thread.start();

        // do something
        System.out.println(" 干点别的...");

        Integer result = task.get();

        System.out.println("拿到的结果为：" + result);

    }

}