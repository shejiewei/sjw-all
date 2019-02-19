package java8.函数式接口;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by shejiewei on 2019/2/17.
 */
public class functionTest {

     public static void main(String[] args) {
         List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
         Predicate<Integer> predicate=n->true;
       System.out.println("输出所有参数:");
         eval(integers,n->true);
         System.out.println("");
        System.out.println("输出偶数:");
         Predicate<Integer> predicate1=n->n%2==0;
         eval(integers,predicate1);

         Predicate<Integer> predicate2=n->n>3;

         System.out.println("");
         System.out.println("输出大于3的数");
         eval(integers,predicate2);
     }

     public static void eval(List<Integer> list,Predicate<Integer
             > predicate)
     {
         for(Integer i:list){
             if(predicate.test(i))
             {

                 System.out.print(i+"");
             }

         }
     }
}
