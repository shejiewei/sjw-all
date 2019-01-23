package 监听模式.V1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class me {

    private String name;
    private List<Listener> listeners;


    public me(String name) {
        this.name=name;
        this.listeners=new ArrayList<>();

    }


    public void qichuan(){
        System.out.println("起床了");
        for (Listener listener:listeners){
            if (listener instanceof Listener)
            {
                shuayaListener shuaya= (shuayaListener) listener;
                shuaya.fireAfterEventInvoked( new qichuanEvent(this,"起床事件发生了"));
            }
        }

       System.out.println("接到起床事件后,刷牙完成了");
    }

    public void addListener(Listener listener){
        listeners.add(listener);
        System.out.println("有listener对me注册了监听");

    }





}
