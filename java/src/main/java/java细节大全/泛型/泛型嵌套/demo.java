package java细节大全.泛型.泛型嵌套;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class demo<S>
{
    private  S info;
  public demo(S info)
  {
      this.info=info;
  }
    public S getInfo() {
        return info;
    }

    public void setInfo(S info) {
        this.info = info;
    }

     public static void main(String[] args) {
         Info<String, Integer> info = new Info<>("aa", 30);
         demo<Info<String, Integer>> demo = new demo<>(info);
         System.out.println(demo.getInfo().getKey());
         System.out.println(demo.getInfo().getValue());

     }
}
