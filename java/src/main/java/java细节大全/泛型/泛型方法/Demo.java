package java细节大全.泛型.泛型方法;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class Demo  implements InterfaceDemo{
    @Override
    public <T> T fun(T t) {
        return t;
    }


     public static void main(String[] args) {
         Demo demo = new Demo();
         String aaa = demo.fun("aaa");
         int i=demo.fun(1);
         System.out.println(aaa);
         System.out.println(i);
     }
}
