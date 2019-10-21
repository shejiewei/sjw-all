package operator;

import java.util.HashMap;
import java.util.List;

/**
 * Created by shejiewei on 2019/10/14.
 */
public class MapOperator extends Operator {
   private HashMap<String,Integer> recordMap=new HashMap();

    public MapOperator(Object data, List list) {
        super(data, list);
    }

    public Object map(String input,String split){
        String[] splits = input.split(split);
        for(int i=0;i<splits.length;i++)
        {
            Integer times = recordMap.get(splits[i]);
            if (times == null) {
                recordMap.put(splits[i], 1);
            }
            else {
                times++;
                recordMap.put(splits[i], times);
            }
        }
        return  recordMap;
    }

}
