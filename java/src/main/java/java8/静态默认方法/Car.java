package java8.静态默认方法;

/**
 * Created by shejiewei on 2019/2/17.
 */
public class Car implements FourWheel,Vehicle {
    @Override
    public void print() {
        Vehicle.super.print();
        FourWheel.super.print();
        Vehicle.sound();
    }
}
