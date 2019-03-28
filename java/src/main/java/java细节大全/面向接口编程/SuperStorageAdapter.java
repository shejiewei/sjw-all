package java细节大全.面向接口编程;

/**
 * Created by shejiewei on 2019/3/28.
 */
public class SuperStorageAdapter implements  IMobileStorage {
    private SuperStorage s;

    public SuperStorage getS() {
        return s;
    }

    public void setS(SuperStorage s) {
        this.s = s;
    }

    @Override
    public void Read() {
        this.s.rd();
    }

    @Override
    public void Write() {
      this.s.wt();
    }

     public static void main(String[] args) {
         Computer computer = new Computer();
         SuperStorage superStorage = new SuperStorage();

         SuperStorageAdapter superStorageAdapter = new SuperStorageAdapter();
         superStorageAdapter.setS(superStorage);
         computer.set_flashDisk(superStorageAdapter);
         computer.WriteData();
         computer.ReadData();



     }
}
