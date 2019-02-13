package 多线程.生产者消费者.v1;

/**
 * Created by shejiewei on 2019/2/12.
 */
public class PCData {
    private long value;
    public void set(long value){
        this.value = value;

    }
    public long get(){
        return value;
    }
}