package 多线程.notify;

/**
 * Created by shejiewei on 2019/3/10.
 */
public interface AbstractStorage {
    void consume(int num);
    void produce(int num);
}