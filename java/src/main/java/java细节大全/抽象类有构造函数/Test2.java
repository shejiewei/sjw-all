package java细节大全.抽象类有构造函数;

import 注解.V2.Test;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class Test2 extends  test {
    String age;
    String test;
    Test2(String name,String age) {
        super(name);

      this.age=age;
    }

    public static void main(String[] args){
          String name;
        Test2 test2 = new Test2("aa","13");


    }
}
