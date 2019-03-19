package java细节大全.子类不能调用父类私有变量;

/**
 * Created by shejiewei on 2019/3/19.
 */
public class Child extends  Person {
    public String grade;

    Child(String grade,String name){
        super(name);
        this.grade=grade;
    }

    public static void main(String[] args){
        Person p=new Child("12","aa");
        //System.out.println(p.name);
        Child p2=new Child("11","aa");
        System.out.println(p2.grade+p2.age+p2.name);
    }


}
