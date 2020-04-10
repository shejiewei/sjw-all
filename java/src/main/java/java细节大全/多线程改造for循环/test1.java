package java细节大全.多线程改造for循环;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2020/4/10.
 */
public class test1 {
     public static void main(String[] args) {
         List entryList=new ArrayList<>();
         for (int i=0;i<11;i++){
             entryList.add(i);
         }
         System.out.print(entryList.get(121));
     }
}
