package 设计模式.监听模式.V2;

/**
 * Created by shejiewei on 2019/5/9.
 */
public class DemoListener1 implements  DemoListener
{
    @Override
    public void handleEvent(DemoEvent dm) {
          System.out.println("this is demolistener1");
          System.out.println("收听到事件消息,触发者"+dm.getSource());
           dm.say();

    }
}
