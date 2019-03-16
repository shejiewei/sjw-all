package 多线程.基础.V2;

/**
 * Created by shejiewei on 2019/3/16.
 */
public class ThreadTest {




    public static void main(String[] args) throws InterruptedException {


        ThreadDemo a = new ThreadDemo("A");
        ThreadDemo b = new ThreadDemo("B");
        ThreadDemo c = new ThreadDemo("B");
       /* a.setPriority(1);
        b.setPriority(10);
        a.join();
        b.join();*/
   /*    a.start();
       b.start();
       c.start();*/

        runna runna = new runna();
        Thread thread = new Thread(runna);
        thread.start();


    }


}
