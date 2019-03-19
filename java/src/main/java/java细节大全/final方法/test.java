package java细节大全.final方法;

/**
 * Created by shejiewei on 2019/3/19.
 */
public class test {
    final void sayhi(){
        System.out.println("hi");
    }

    final  void sayhi(String name){
        System.out.println("hi"+name);
    }

    public static void main(String[] args){
      //final可以重载
        test test = new test();
        test.sayhi();
        test.sayhi("haha");
    }
}
