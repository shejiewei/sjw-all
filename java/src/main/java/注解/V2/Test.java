package 注解.V2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * Created by shejiewei on 2019/1/23.
 */
public class Test {

    public static void main(String[] args) {
        User u = new User();
        u.setUserName("张三");
        u.setSex(1);
        System.out.println(parseUser(u));
    }

    public static String parseUser(User u){
        StringBuffer sb = new StringBuffer();
        sb.append("select * from ");
        try {
            //1.获取表名
            Class c = Class.forName("注解.V2.User");
            if(c.isAnnotationPresent(Table.class)){
                Table t = (Table)c.getAnnotation(Table.class);
                String tableName = t.value();
                sb.append(tableName);
            }
            sb.append(" where 1=1");
            //2.获取字段名与值
            Field[] fs = c.getDeclaredFields();
            for(Field f : fs){
                //2.1字段名
                String column = "";
                if(f.isAnnotationPresent(Column.class)){
                    Column fld = (Column)f.getAnnotation(Column.class);
                    column = fld.value();
                }
                //2.2字段值
                String fieldName = f.getName();
                String getMethod = "get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
                Method method = c.getMethod(getMethod);
                Object fieldValue = method.invoke(u);
                if(fieldValue instanceof Integer && (Integer)fieldValue == 0){
                    continue;
                }
                if(fieldValue != null){
                    sb.append(" and ").append(column).append("=");
                    if(fieldValue instanceof String){
                        sb.append("'").append(fieldValue).append("'");
                    }else{
                        sb.append(fieldValue);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
