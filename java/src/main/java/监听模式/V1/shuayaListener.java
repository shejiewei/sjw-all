package 监听模式.V1;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class shuayaListener implements Listener {


    @Override
    public void fireAfterEventInvoked(Event event) {
        qichuanEvent qcEvent = (qichuanEvent) event;
        System.out.println(qcEvent.getEventName()+"了,要刷牙");
        System.out.println("刷牙");
    }
}
