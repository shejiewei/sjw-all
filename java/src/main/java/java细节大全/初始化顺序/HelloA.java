package java细节大全.初始化顺序;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class HelloA {

    public HelloA(){
        System.out.println("A构造方法");
    }
    {
        System.out.println("I am A");
    }
    static {
        System.out.println("Static A");
    }

}
