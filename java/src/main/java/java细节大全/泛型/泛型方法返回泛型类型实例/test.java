package java细节大全.泛型.泛型方法返回泛型类型实例;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class test
{


    public static <T extends Number> Info<T> fun(T param){
        Info<T> tInfo = new Info<>();
        tInfo.setVar(param);
        return tInfo;
    }


   public static void main(String[] args) {

       Info<Integer> fun = fun(30);
       System.out.println(fun);

   }
}
