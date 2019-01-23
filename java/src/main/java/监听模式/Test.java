package 监听模式;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class Test {
    public static void main(String[] args) {
        Kid xiaoming = new Kid("xiaoming");
        xiaoming.addListener(new WashingHandsListener());
        xiaoming.eat();
    }
}