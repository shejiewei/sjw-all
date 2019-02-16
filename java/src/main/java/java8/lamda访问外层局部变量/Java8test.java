package java8.lamda访问外层局部变量;

/**
 * Created by shejiewei on 2019/2/16.
 */
public class Java8test {

     public static void main(String[] args) {
      final int num=1;
      Converter<Integer,String> conv=i ->
              System.out.println(String.valueOf(i+num));
       conv.convert(2);
      }
      private interface Converter<T1,T2>{
         void convert(int i);
      }

}
