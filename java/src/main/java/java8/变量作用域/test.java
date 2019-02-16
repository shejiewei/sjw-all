package java8.变量作用域;

/**
 * Created by shejiewei on 2019/2/16.
 */
public class test {

     static String testMessage="final";
     public static void main(String[] args) {

         SendMessage greet1=message1 ->
                 System.out.println(message1+testMessage);

         greet1.saySomething("hi,");

         SendMessage greet2=new SendMessage() {
             @Override
             public void saySomething(String message) {
                 System.out.println(message+testMessage);
             }
         };
         greet2.saySomething("hi,");
      }

      interface SendMessage{
         void saySomething(String message);
      }
}
