package java细节大全.内部类;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class OutClass2 {
    private String mname = "aa";
    //  静态内部类只能访问外部类中的静态变量，静态内部类的初始化不依赖于外部类
    static int mAge = 12;

    static class InnerClass {
        String name;
        int age;

        private void getName() {

            //  name=mname;
            age = mAge;
            System.out.println("name=" + name + ",age=" + age);
        }
    }


    public static void main(String[] args){

        InnerClass innerClass = new InnerClass();
        innerClass.getName();

    }
}