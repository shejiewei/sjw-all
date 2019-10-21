package core;

import bean.DataSource;
import bean.DataSet;
import operator.FlatMapOperator;

/**
 * Created by shejiewei on 2019/10/14.
 */
public class core {

     public static void main(String[] args) {
          DataSet<String> text=new DataSource();
         String str="aa bb cc dd cc d er e r et r  r yt  t  wer a d sf d g dfs gf  h h re w ";
         text.setData(str);
         FlatMapOperator flatMapOperator = text.flatMap();

         System.out.println(flatMapOperator.getResultType());
         System.out.println(flatMapOperator.gettDataSet().getData());



         text.exec();
     }

}
