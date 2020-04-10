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
         for (int k=0;k<=num;k++)
         {
        	 int start=k*step;
        	 int end=start+step;
             if (k==num){
              end=entryList.size();
             }
             final int end1=end;
             Runnable run = new Runnable() {
                 public void run() {
                     try {
                    	 fun(start,end1,entryList);
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
        // fun(num*step,  entryList.size() ,entryList);
       
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
/*
    多线程并发导致List的add()失败，元素为null
        原创coderwzc 最后发布于2018-05-05 21:21:54 阅读数 2821  收藏
        展开
*/
/**
 * Appends the specified element to the end of this list.
 *
 * @param e element to be appended to this list
 * @return <tt>true</tt> (as specified by {@link Collection#add})
 *//*

public boolean add(E e) {
            ensureCapacityInternal(size + 1);  // Increments modCount!!
            elementData[size++] = e;
            return true;
        }

        执行add方法时，会先将此容器的大小增加。。即size++，然后将传进的元素赋值给新增的elementData[size++]，即新的内存空间

        但是此时如果在size++后直接来取这个List,而没有让add完成赋值操作，则会导致此List的长度加一，，但是最后一个元素是空（null），所以在获取它进行计算的时候报了空指针异常。
*/
