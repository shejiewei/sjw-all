package 设计模式.监听模式.V2;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by shejiewei on 2019/5/9.
 */
public class DemoSource {
    private Vector repository=new Vector();

    public void addListener(DemoListener d){
        repository.add(d);
    }

    public void notifyDemoEvent(){
        System.out.println("事件发生,开始通知listener");
        Enumeration elements = repository.elements();
       while (elements.hasMoreElements())
       {
           DemoListener dl= (DemoListener) elements.nextElement();
           dl.handleEvent(new DemoEvent(this));
       }
    }


}
