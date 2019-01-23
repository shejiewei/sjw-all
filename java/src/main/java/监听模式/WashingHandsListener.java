package 监听模式;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class WashingHandsListener implements Listener{
    @Override
    public void fireAfterEventInvoked(Event event) {
        WashingHandsEvent washingHandsEvent = (WashingHandsEvent) event;
        System.out.println("饭前准备"+ washingHandsEvent.getEventName());
    }
}