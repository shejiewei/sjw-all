package java细节大全.生成csv;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2019/8/14.
 */
public class CSVUtils {




    /**
     * 生成csv
     *
     * @param fillData
     *            数据
     * @param filePath
     *            生成的csv文件名
     * @param split
     *            分割符
     * @return
     */
    public static boolean writeCSV(List fillData, String filePath, String split) {
        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            File CSVFile = new File(filePath);
            out = new FileOutputStream(CSVFile);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);

            // 填充数据

                Class objClass = fillData.get(0).getClass();
                Field[] fields = objClass.getDeclaredFields();

                // 添加头部（首行）
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    bw.append(field.getName());
                    if (i != fields.length - 1)
                        bw.append(split);
                }
                bw.append("\r\n");

                // 填充对象属性值
                for (Object obj : fillData) {
                    for (Field field : fields) {
                        Method[] methods = objClass.getDeclaredMethods();
                        for (int k = 0; k < methods.length; k++) {
                            Method method = methods[k];
                            if (method.getName().equalsIgnoreCase("get" + field.getName())) {
                                String property = (String) method.invoke(obj, null);
                                bw.append(property);
                                if (k != methods.length - 1)
                                    bw.append(split);
                            }
                        }
                    }
                    bw.append("\r\n");
                }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;

    }

   public static void main(String[] args) {

       /**
        * 生成csv
        *
        * @param fillData
        *            数据
        * @param filePath
        *            生成的csv文件名
        * @param split
        *            分割符
        * @return
        */

       ArrayList<String> ss = new ArrayList<>();

       ss.add("aa,cc");
       ss.add("aa,cce");
       ss.add("aa,ccd");
       boolean aa = writeCSV(ss, "aa.csv", ",");
       System.out.println(aa);


   }


}
