package java细节大全.string动态加载执行;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shejiewei on 2019/6/24.
 */
public class test {



    /**
     * 动态加载方法
     * @author wangyfc
     *
     */

    /*    public static Object executeExpression(String jexlExp,Map<String,Object> map){
            JexlEngine jexl=new JexlEngine();
            Expression e = jexl.createExpression(jexlExp);
            JexlContext jc = new MapContext();
            for(String key:map.keySet()){
                jc.set(key, map.get(key));
            }
            if(null==e.evaluate(jc)){
                return "";
            }
            return e.evaluate(jc);
        }
*/

         public static void main(String[] args) {

                 Map<String, Object> map = new HashMap<>();
                 map.put("alive", "coding every day");
                 map.put("out", System.out);
                 String expression = "out.print(alive)";
                 int a=10;
                 String test="if(a>0) System.out.println(a)";




               //  executeExpression(expression, map);
                 // ReflectUtils.executeExpression(expression, map);

         }

}
