package java细节大全.defaultInterface;

/**
 * Created by shejiewei on 2019/3/29.
 */
public interface InterfaceA {
    default String f(){
        return "this is InterfaceA";
    }

  static  String getName(){return "InterfaceA";}

  String getAge();
}
