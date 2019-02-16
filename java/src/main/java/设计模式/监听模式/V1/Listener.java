package 设计模式.监听模式.V1;

/**
 * Created by shejiewei on 2019/1/23.
 */
public interface Listener extends java.util.EventListener{
    public void fireAfterEventInvoked(Event event);
}
