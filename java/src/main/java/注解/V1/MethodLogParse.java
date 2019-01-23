package 注解.V1;

import java.lang.reflect.Method;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class MethodLogParse {

    public static void parse(Class classType) throws Exception{
        Method[] array = classType.getMethods();
        for(Method method : array){
            System.out.println("=================="+method.getName()+"=================");
            if(method.isAnnotationPresent(MethodLog.class)){
                MethodLog methodLog = method.getDeclaredAnnotation(MethodLog.class);
                String str = String.valueOf(methodLog.operator());
                String str1 = String.valueOf(methodLog.method());

                System.out.println(str + " " + str1);
            }

        }
    }

    public static void main(String[] args){
        try {
            MethodLogParse.parse(CelebrateController.class);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
