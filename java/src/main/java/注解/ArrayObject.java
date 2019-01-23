package 注解;

import java.lang.reflect.Array;


public class ArrayObject {

    public static void main(String[] args) throws Exception{
        int[] intArray = (int[])Array.newInstance(int.class,3);
        for (int i = 0 ;i < intArray.length;i++){
            Array.set(intArray,i,i + 2);
        }

        for (int i = 0 ; i < intArray.length;i++){
            System.out.println(Array.get(intArray,i));
        }

//        Class clz = int[].class;
        Class clz = Class.forName("[I");
        System.out.println(clz.getTypeName());
        System.out.println(clz.getComponentType().getTypeName());

    }
}
