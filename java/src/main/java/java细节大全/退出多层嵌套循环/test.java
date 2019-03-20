package java细节大全.退出多层嵌套循环;

/**
 * Created by shejiewei on 2019/3/20.
 */
public class test
{

    public static void main(String[] args){

     for (int i=0;i<10;i++){
         flag:
            for (int j=11;j<10;j++){
             System.out.println(j);
             if (j>3){
                 break flag;
             }

         }
     }

    }
}
