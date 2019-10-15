package core;

import bean.DataResult;
import bean.DataSet;

/**
 * Created by shejiewei on 2019/10/14.
 */
public class core {

     public static void main(String[] args) {
          DataSet<String> text=new DataResult();
         String str="aa bb cc dd cc d er e r et r  r yt  t  wer a d sf d g dfs gf   h h re w ";

         text=text.map().print();
         text.setData(str);
         text.exec();
     }

}
