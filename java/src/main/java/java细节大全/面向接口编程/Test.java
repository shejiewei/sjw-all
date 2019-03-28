package java细节大全.面向接口编程;

/**
 * Created by shejiewei on 2019/3/28.
 */
public class Test {

     public static void main(String[] args) {
        IMobileStorage mp3=new Mp3();
         IMobileStorage flashDisk = new FlashDisk();
         Computer computer = new Computer();
         computer.set_flashDisk(mp3);
         computer.WriteData();
         computer.set_flashDisk(flashDisk);
         computer.WriteData();
     }
}
