
package java细节大全.多线程改造for循环;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class test2 {
    public static void main(String[] args) {
    	
        int y=5;
        List entryList=new ArrayList<>();
        int rowIndex = 0;
        int colIndex = 0;
        Map gridMap=new HashMap<>();
        for (int i=0;i<10;i++){
            for(int j=0;j<5;j++)
            {
                point1 point1 = new point1();
                point1.setX(i);
                point1.setY(j);
                Integer indexKey = colIndex + (rowIndex * y);
                gridMap.put(indexKey, point1);
                colIndex++;
            }
            rowIndex++;
            colIndex = 0;
        }

        int step=2;
        int num = 10 /step;
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int k=0;k<num;k++)
        {
            int start=k*step;
            int end=start+step;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                    fun( start,  end,  y,gridMap);

                    } catch (Exception e) {
                    }
                }
            };
            pool.execute(run);
        }

        //pool.shutdown();
        fun(num*step,  entryList.size() ,y,gridMap);
    }
    public static void  fun(int start, int end, int y,Map gridMap){
      /*  for (int i=start;i<end;i++){
            //  System.out.println(entryList.get(i));
            int o = (int)entryList.get(i);
            int i1 = o + 100;
            entryList.set(i,i);
        }*/
        int rowIndex = start;
        int colIndex = 0;

        for (int i=start;i<end;i++){
            for(int j=0;j<5;j++)
            {
                Integer indexKey = colIndex + (rowIndex * y);
                System.out.println(((point1)gridMap.get(indexKey)).getX()+","+((point1)gridMap.get(indexKey)).getY());
                colIndex++;
            }
            rowIndex++;
            colIndex = 0;
        }
    }
}
