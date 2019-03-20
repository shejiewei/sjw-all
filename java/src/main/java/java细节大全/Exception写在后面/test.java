package java细节大全.Exception写在后面;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class test {

    public int div(int a,int b){
        int result;
        try{
            result=a/b;
            return  result;
        }
        catch (Exception e)
        {
            System.out.println("Exception");
        }
      /*  catch (NullPointerException e){
            System.out.println("Exception");
        }*/



       return 0;
    }


    public static void main(String[] args){



    }

}
