package 设计模式.监听模式.V2;

import java.util.EventObject;

/**
 * Created by shejiewei on 2019/5/9.
 */
public class DemoEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DemoEvent(Object source) {
        super(source);
    }
    public void say(){
        System.out.println("This  is say method");
    }

}
