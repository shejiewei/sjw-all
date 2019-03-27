package java细节大全.泛型.泛型统一传入的参数;

/**
 * Created by shejiewei on 2019/3/27.
 */
 class Info<T> {
     private T var;

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return  this.var.toString();
    }
}
