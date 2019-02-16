package 设计模式.监听模式;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class Kid{
    private String name;
    private List<Listener> liteners;

    public Kid(String name) {
        this.name = name;
        this.liteners = new ArrayList<>();
    }

    public void eat(){

        for(Listener listener:liteners){
            if(listener instanceof WashingHandsListener){
                WashingHandsListener washingHandsListener = (WashingHandsListener) listener;
                washingHandsListener.fireAfterEventInvoked(new WashingHandsEvent(this,"洗手"));
            }
        }
        System.out.println("吃饭...");
    }

    public void addListener(Listener listener){
        liteners.add(listener);
    }

}
