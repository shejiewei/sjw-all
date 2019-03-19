package java细节大全.抽象类有构造函数;

/**
 * Created by shejiewei on 2019/3/20.
 */
public abstract class test {

    public  String name;
    public void sayhi() {

    }

    test(String name){
        this.name=name;
    }

    public static void main(String[] args){
      //  test test = new test();
      //错误，抽象类可以有构造函数，却不能初始化

    }
}
