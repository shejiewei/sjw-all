package java细节大全.判断object对象是array;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.lang.reflect.Array;

/**
 * Created by shejiewei on 2019/5/20.
 */
public class test {

    public static boolean isArray0(Object obj){
        if(obj == null){
            return false;
        }

        return obj instanceof Array;
    }

    public static boolean isArray1(Object obj) {
        if (obj == null) {
            return false;
        }

        return obj.getClass().isArray();

    }

     public static void main(String[] args) {
        Object test1 = new Integer[]{1,2,3,4,5,6,8,9};
        // Object test1=new ArrayList<>();
         Object num=1;
         SolrClient solr = new HttpSolrClient(urlString);
         if (num.getClass().isEnum())
         {
             System.out.println(num+"is num");
         }

         Object test2 = 5;

         System.out.println(isArray0(test1));//false
         System.out.println(isArray0(test2));//false

         System.out.println(isArray1(test1));//true
         System.out.println(isArray1(test2));//false

      }
}
