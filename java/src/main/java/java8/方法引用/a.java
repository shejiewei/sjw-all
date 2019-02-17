package java8.方法引用;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2019/2/17.
 */
public class a {

     public static void main(String[] args) {
      List names=new ArrayList<>();
         names.add("a");
         names.add("b");
         names.add("c");
         names.add("d");

         names.forEach(System.out::println);
      }
}
