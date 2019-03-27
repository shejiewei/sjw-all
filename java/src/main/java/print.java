/**
 * Created by shejiewei on 2019/3/14.
 */
public class print {

     public static void main(String[] args) {

         boolean flag;

        int j;
         for (int i=1;i<1000;i++)
         {
             flag=false;
             for ( j=2;j<i;j++){
                 if(i%j==0)
                 {
                     flag=false;
                     break;

                 }
             }
             if (flag=true)
             {
                 System.out.println(j);
             }

         }


      }
}
