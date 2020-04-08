package java细节大全.多线程改造for循环;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shejiewei on 2020/4/1.
 */
public class test {

     public static void main(String[] args) {


         List entryList=new ArrayList<>();
         for (int i=0;i<11;i++){
             entryList.add(i);
         }
     
         int sum=0;
         int step=5;
         int num = entryList.size() /step;
         ExecutorService pool = Executors.newCachedThreadPool();
         final CountDownLatch endGate=new CountDownLatch(num);
         for (int k=0;k<num;k++)
         {
        	 int start=k*step;
        	 int end=start+step;
             Runnable run = new Runnable() {
                 public void run() {
                     try {
                    	 fun(start,end,entryList);
                     } catch (Exception e) {
                     }
                     finally {
                         endGate.countDown();
                     }
                 }
             };
             pool.execute(run);
         }
         try {
             endGate.await();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         pool.shutdown();
         fun(num*step,  entryList.size() ,entryList);
       
             System.out.println("+++++++++++++");
        
      }
  public static void  fun(int start, int end, List entryList){
	  
         for (int i=start;i<end;i++){
            System.out.println(entryList.get(i));
            // int o = (int)entryList.get(i);
           //  int i1 = o + 100;
            // entryList.set(i,i);
         }
  }
}
