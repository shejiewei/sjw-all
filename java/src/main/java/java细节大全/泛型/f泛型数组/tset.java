package java细节大全.泛型.f泛型数组;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class tset {
    public  static  <T> T[] fun1(T ...args){
        return args;
    }

    public static<T> void fun2(T param[]){
        for (T t:param){
            System.out.println(t+",");
        }
    }
    public static void main(String[] args) {

        Integer i[]=fun1(1,2,3,4,5);
        fun2(i);


      }
}
