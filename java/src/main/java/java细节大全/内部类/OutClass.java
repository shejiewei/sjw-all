package java细节大全.内部类;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class OutClass {
   // 内部类里面可以直接访问外部类的静态和非静态变量，包括private变量
    private String mname="aa";

    static int mAge=12;
    class  InnerClass{
        String name;
        int age;
        private void getName(){

            name=mname;
            age=mAge;
            System.out.println("name="+name+",age="+age);
    }
    }


    public static void main(String[] args){

        InnerClass innerClass = new OutClass().new InnerClass();
        innerClass.getName();

         OutClass outClass=  new OutClass();

        InnerClass innerClass1 = outClass.new InnerClass();
        innerClass1.getName();

    }

}
