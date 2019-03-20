package java细节大全.生产者消费者;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class Producer implements  Runnable {
    private  Storage storage;
    private int num;

    public Producer(int num,Storage storage){

        this.num=num;
        this.storage=storage;
    }

    @Override
    public void run() {

        storage.produce(num);
    }
}
