package 多线程.基础.V1;

/**
 * Created by shejiewei on 2019/1/24.
 */

class thread11 extends  Thread{

    public String name;
    public thread11(String name){

        this.name=name;
    }
    public  void run(){
        System.out.println(Thread.currentThread().getName()+"线程启动");

        for (int i=0;i<50;i++){
            System.out.println(Thread.currentThread().getName()+"线程输出"+i);

            if(i==30)
            {
                this.yield();
            }
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"线程结束");
    }

}


public class join {
     public static void main(String[] args) throws InterruptedException {
         thread11 A = new thread11("A");
         thread11 b = new thread11("B");
         A.start();
         b.start();
         A.join();

         b.join();
      System.out.println("主线程结束");

     }
}
