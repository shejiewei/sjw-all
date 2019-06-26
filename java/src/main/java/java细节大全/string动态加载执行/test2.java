package java细节大全.string动态加载执行;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2019/6/25.
 */
public class test2 {

     public static void main(String[] args) {

         List<Range> rangeList =new ArrayList<>();
         Range range1 = new Range();
         range1.setTo(10.0);
         Range range2 = new Range();
         range2.setFrom(10.0);
         range2.setTo(20.0);
         Range range4 = new Range();
         range4.setFrom(10.0);
         range4.setTo(30.0);
         Range range3 = new Range();
         range3.setFrom(30.0);
         StringBuilder str=new StringBuilder();
         rangeList.add(range1);
         rangeList.add(range2);
         rangeList.add(range3);
         rangeList.add(range4);
        // str.append("double value=2.0\n");

   for (int i=0;i<rangeList.size();i++) {
    if (rangeList.get(i).getFrom() != null && rangeList.get(i).getTo() == null) {
        System.out.println(rangeList.get(i).getFrom()+":"+rangeList.get(i).getTo());
       }
     }
      /*   if (rangeList.get(0).getFrom()==null&&rangeList.get(0).getTo()!=null)
         {
             str.append("if(value<="+rangeList.get(0).getTo()+"){");
             str.append("return  \"<="+rangeList.get(0).getTo()+"\";}\n");
         }
         for (int i = 1; i < rangeList.size()-1; i++) {
             Range range = rangeList.get(i);
             if (range.getFrom()!=null&&range.getTo()!=null)
             {
                 str.append("else if("+range.getFrom()+"<value&&value<="+range.getTo()+"){");
                 str.append("return  \""+range.getFrom()+"-"+range.getTo()+"\";}\n");
             }
         }
         if (rangeList.get(rangeList.size()-1).getFrom()!=null&&rangeList.get(rangeList.size()-1).getTo()==null)
         {
             str.append("else if(value>"+rangeList.get(rangeList.size()-1).getFrom()+"){");
             str.append("return \">"+rangeList.get(rangeList.size()-1).getFrom()+"\";}\n");
         }*/


    /*     if (0 <= value && value <= 2) {
             return "0-10";
         } else if (2 < value && value <= 20) {
             return "10-20";
         } else
             return ">20";
     }*/
        /* System.out.println(str.toString());


         String sss="int value=1;\n" +
                 "System.out.println(value);\n";
         GroovyShellss shell=new GroovyShellss();


         shell.evaluate(sss);*/
      }
}
