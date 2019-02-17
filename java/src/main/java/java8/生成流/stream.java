package java8.生成流;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by shejiewei on 2019/2/17.
 */
public class stream {
     public static void main(String[] args) {
        //输出不是空的字符串
         List<String> strings = Arrays.asList("a", "", "c", "ef");
         List<String> collect = strings.stream().filter(string -> !string.isEmpty())
                 .collect(Collectors.toList());
         for (String s:collect)
         {
             System.out.println(s);
         }
      collect.forEach(System.out::print);

         //获取平方数

         List<Integer> integers = Arrays.asList(2, 3, 4, 54, 5, 2, 4);

         List<Integer> integers1 = integers;
         integers1.stream().map(i->i*i)
                 .distinct()
                 .collect(Collectors.toList());
         System.out.println("");
         integers.forEach(System.out::print);

         //limit
         Random random=new Random();

         random.ints().limit(10).sorted().forEach(
                 System.out::print
         );

         //parallelStream
         long count = strings.parallelStream().filter(string -> string.isEmpty()
         ).count();

         System.out.println("\n"+count);

         //合并字符串
         String collect1 = strings.stream().filter(string -> !string.isEmpty())
                 .collect(Collectors.joining(","));
         System.out.println("\n"+collect1);

         //统计数字

         IntSummaryStatistics stats = integers.stream().mapToInt(x -> x)
                 .summaryStatistics();
         System.out.println("\n最大数:"+stats.getMax());
         System.out.println("最小"+stats.getMin());


     }

}
