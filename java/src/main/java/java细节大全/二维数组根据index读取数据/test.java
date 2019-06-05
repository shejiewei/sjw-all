/*
package java细节大全.二维数组根据index读取数据;

*/
/**
 * Created by shejiewei on 2019/6/5.
 *//*

public class test {
     public static void main(String[] args) {
         public static Double getDataAsym(Array array, int i, int j, int offset, int increment)
         {
             Index dataIndex = Index.factory(array.getShape());
             int[] location = {offset + i*increment,offset + j*increment};
             dataIndex.set(location);
             switch (array.getDataType())
             {
                 case INT:
                     return new Double((Integer)array.getObject(dataIndex));
                 case SHORT:
                     return new Double((Short)array.getObject(dataIndex));
                 case FLOAT:
                     return new Double((Float)array.getObject(dataIndex));
                 case DOUBLE:
                     return new Double((Double)array.getObject(dataIndex));
                 case LONG:
                     return new Double((Long)array.getObject(dataIndex));
                 default:
                     return (Double) array.getObject(dataIndex);
             }	}
      }
}
*/
