package 监听模式;

/**
 * Created by shejiewei on 2019/1/23.
 */
public interface Listener extends java.util.EventListener{
    public void fireAfterEventInvoked(Event event);
}
