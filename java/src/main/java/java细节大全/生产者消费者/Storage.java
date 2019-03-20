package java细节大全.生产者消费者;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class Storage {
    private static final  int MAX_SIZE=1000;
    private List<Object> data=new ArrayList<Object>();

    public synchronized  void produce (int num ){
        if(data.size()+num>MAX_SIZE){
            System.out.println("生产"+num+"在，超出库容量，阻塞，库存"+data.size());
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0;i<num;i++){
            data.add(new Object());
        }

        System.out.println("生产操作-->数量：" + num + "，成功入库~------库存：" + data.size());

        notify();



    }

    public synchronized  void consume(int num){
        if (data.size()-num<0){
            System.out.println("消费操作-->数量：" + num + "，库存不足，消费阻塞！------库存：" + data.size());
        }

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0;i<num;i++){
            data.remove(0);
        }


        System.out.println("消费操作-->数量：" + num + "，消费成功~------库存：" + data.size());
        notify();
    }










}
