package java8.静态默认方法;

/**
 * Created by shejiewei on 2019/2/17.
 */
interface Vehicle {
    default void print(){
        System.out.println("我是一辆车");
    }
    static void sound(){
        System.out.println("按喇叭");
    }
}
