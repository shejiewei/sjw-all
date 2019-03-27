package java细节大全.泛型.泛型方法返回泛型类型实例;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class Info<T extends Number> {
  private T var;

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    @Override
    public String toString() {
      return this.var.toString();
    }
}
