package 监听模式;

import java.util.EventObject;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class Event extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public Event(Object source) {
        super(source);
    }
}
