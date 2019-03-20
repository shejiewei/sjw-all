package java细节大全.初始化顺序;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class HelloB extends  HelloA{
  
    public HelloB(){
        System.out.println("HelloB构造方法");
    }
    {
        System.out.println("I am HElloB");
    }
    static {
        System.out.println("static B");
                
    }

    public static void main(String[] args){
//1.静态代码块 --> 2.普通代码块 --> 3.构造方法
     //   需要明白的是，1是类级别的，2和3是实例级别的，所以在父子类关系中，上述的执行顺序为：

    //    父类静态代码块-->子类静态代码块-->父类普通代码块-->父类构造方法-->子类代码块-->子类构造方法；

    //    也就是上到下（父类到子类）先走完 类级别的（静态的）--> 再依次走完父类的所有实例级别代码 --> 再走子类所有实例级别代码



        HelloB helloB = new HelloB();

    }
}
