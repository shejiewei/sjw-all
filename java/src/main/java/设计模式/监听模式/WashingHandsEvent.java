package 设计模式.监听模式;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class WashingHandsEvent extends Event{
    private String eventName;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public WashingHandsEvent(Object source,String eventName) {
        super(source);
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
