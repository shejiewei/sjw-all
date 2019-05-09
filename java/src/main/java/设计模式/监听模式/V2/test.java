package 设计模式.监听模式.V2;

/**
 * Created by shejiewei on 2019/5/9.
 */
public class test {
     public static void main(String[] args) {

         DemoListener1 demoListener1 = new DemoListener1();
         DemoSource ds=new DemoSource();
         ds.addListener(demoListener1);
         ds.addListener(new DemoListener() {
             @Override
             public void handleEvent(DemoEvent dm) {
                 System.out.println("来自匿名内部类的触发:"+dm.getSource());
             }
         });
         ds.notifyDemoEvent();
     }

}
