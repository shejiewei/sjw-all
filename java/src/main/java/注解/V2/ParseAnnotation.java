package 注解.V2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class ParseAnnotation {

    public static void main(String[] args) {
        try {
            //1.使用类加载器加载类
            Class c = Class.forName("注解.V2.UseAnnotation");
            //2.找到类上的注解
            boolean exist = c.isAnnotationPresent(Description.class);
            if(exist){
                //3.拿到注解实例
                Description d = (Description)c.getAnnotation(Description.class);
                System.out.println(d.value());
            }

            //4.找到方法上的注解
            Method[] ms = c.getMethods();
            for(Method m : ms){
                if(m.isAnnotationPresent(Description.class)){
                    Description d = (Description)m.getAnnotation(Description.class);
                    System.out.println(d.value());
                }
            }
            //5.方法注解的另一种解析方式
            for(Method m : ms){
                Annotation[] ans = m.getAnnotations();
                for(Annotation an : ans){
                    Description d = (Description)an;
                    System.out.println(d.value());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
