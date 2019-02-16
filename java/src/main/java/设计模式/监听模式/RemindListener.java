package 设计模式.监听模式;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class RemindListener {
    public void remind(RemindWashingHandsEvent remindWashingHandsEvent){
        System.out.println("listen to mom, washing hands before eating...");
    }
}
