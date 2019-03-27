package java细节大全.泛型.泛型统一传入的参数;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class demo {

    public static<T> void add(Info<T> i1,Info<T> i2){
        System.out.println(i1.getVar()+"+"+i2.getVar());
    }

     public static void main(String[] args) {
         Info<String> s1 = new Info<>();
         Info<String> s2 = new Info<>();
         s1.setVar("aa");
         s2.setVar("cc");
         add(s1,s2);

     }
}
