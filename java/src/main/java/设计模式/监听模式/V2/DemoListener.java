package 设计模式.监听模式.V2;

import java.util.EventListener;

/**
 * Created by shejiewei on 2019/5/9.
 */
public interface DemoListener extends EventListener {

    public void handleEvent(DemoEvent dm);

}
