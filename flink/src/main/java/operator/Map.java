package operator;

import java.util.HashMap;

/**
 * Created by shejiewei on 2019/10/14.
 */
public class Map {
   private HashMap<String,Integer> recordMap=new HashMap();

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
