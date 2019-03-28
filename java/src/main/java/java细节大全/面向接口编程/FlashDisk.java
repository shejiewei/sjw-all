package java细节大全.面向接口编程;

/**
 * Created by shejiewei on 2019/3/28.
 */
public class FlashDisk implements IMobileStorage {
    @Override
    public void Read() {
        System.out.println("Read from FlashDisk");
    }

    @Override
    public void Write() {

        System.out.println("write from FlashDisk");
    }
}
