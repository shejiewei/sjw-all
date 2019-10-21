package core;

import bean.DataSet;
import bean.DataSource;
import operator.AggregateOperator;
import operator.FlatMapOperator;
import operator.GroupByOperator;
import operator.Map;

/**
 * Created by shejiewei on 2019/10/14.
 */
public class core {

     public static void main(String[] args) {
          DataSet<String> text=new DataSource();
         String str="aa bb cc dd cc d er e r et r  r yt  t  wer a d sf d g dfs gf  h h re w ";
         text.setData(str);
         FlatMapOperator flatMapOperator = text.flatMap();

         GroupByOperator groupByOperator = flatMapOperator.groupBy(0);

         AggregateOperator sum = groupByOperator.sum(0);

 /*        System.out.println(sum.getOperator());
         System.out.println(sum.getGroupByOperator().getDataSet().flatMap().getData());
         System.out.println(sum.getAllocation());
         System.out.println(sum.getData());

         System.out.println(groupByOperator.getDataSet());
         System.out.println(groupByOperator.getType());

         System.out.println(flatMapOperator.getType());
         System.out.println(flatMapOperator.gettDataSet().getData());*/

         //sum.exec();

         Map mapOperator = new Map();
         Object map = mapOperator.map(str, " ");
         System.out.println(map.toString());
     }

}
