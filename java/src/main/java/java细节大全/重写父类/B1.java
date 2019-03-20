package java细节大全.重写父类;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class B1 extends  A
{
    String nameB="B";
    @Override
    protected  void  getName(){

        System.out.println("子类name"+nameB);
        super.getName();
    }

    public static void main(String[] args){

        B1 b1 = new B1();
        b1.getName();

    }

}
