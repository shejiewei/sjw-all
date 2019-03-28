package java细节大全.面向接口编程;

/**
 * Created by shejiewei on 2019/3/28.
 */
public class Mp3 implements  IMobileStorage
{
    @Override
    public void Read() {
        System.out.println("read from mp3");
    }

    @Override
    public void Write() {
         System.out.println("write from mp3");
    }
}
