package java8.类型推断;

/**
 * Created by shejiewei on 2019/2/16.
 */
public class java8Test
{
     public static void main(String[] args) {
        java8Test test=new java8Test();
        MathOperation add=(int a,int b)->a+b;
        MathOperation sub=(a,b)->a-b;
        MathOperation multi=(int a,int b)->{
            return a*b;
        };
         MathOperation division=(a,b)->a/b;
         //将函数作为参数传递
         System.out.println("10+5="+test.operate(10,5,add));
         System.out.println("10-5="+test.operate(10,5,sub));
         System.out.println("10*5="+test.operate(10,5,multi));
         System.out.println("10*5="+test.operate(10,5,division));

        GreetingMessage greet1=Message ->
                System.out.println("greet1"+Message);
        GreetingMessage greet2=(Message)->
                System.out.println("greet2"+Message);
        greet1.sayMessage("11");
        greet2.sayMessage("22");

     }
      interface  MathOperation{
         int operation(int a,int b);
      }
      interface GreetingMessage{
         void sayMessage(String Message);
      }
      private int operate(int a,int b,MathOperation mathOperation)
      {
          return  mathOperation.operation(a,b);
      }

}
