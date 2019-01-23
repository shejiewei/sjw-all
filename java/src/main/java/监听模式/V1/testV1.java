package 监听模式.V1;

/**
 * Created by shejiewei on 2019/1/23.
 */
public class testV1 {
     public static void main(String[] args) {
         me m = new me("sjw");
         m.addListener(new shuayaListener());
         m.qichuan();
     }

}
