package 设计模式.监听模式;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class Child {
    private String name;
    private RemindListener remindListener;

    public Child(String name){
        this.name = name;
    }
    public void eat() {
        if(null!=remindListener){
            remindListener.remind(new RemindWashingHandsEvent(this));
        }
        System.out.println("Child eat...");
    }

    public void addListener(RemindListener listener){
        remindListener = listener;
    }
}
