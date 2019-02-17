package java8.静态默认方法;

/**
 * Created by shejiewei on 2019/2/17.
 */
interface FourWheel{
    default void print(){
        System.out.println("我是一辆四轮车");
    }
}
