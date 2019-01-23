package 监听模式;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class RemindWashingHandsEvent {
    private Child child;

    public RemindWashingHandsEvent(Child child){
        this.child = child;
    }
}