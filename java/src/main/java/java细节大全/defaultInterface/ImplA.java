package java细节大全.defaultInterface;

/**
 * Created by shejiewei on 2019/3/29.
 */
public class ImplA  implements InterfaceA {
    @Override
    public String f() {
        return "f";
    }

    @Override
    public String getAge() {
        return "12";
    }

     public static void main(String[] args) {
        System.out.println(new ImplA().f());
        System.out.println(InterfaceA.getName());
      }
}
