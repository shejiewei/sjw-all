package java细节大全.泛型.接口泛型;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class InfoImpl<T> implements Info<T> {
    private  T var;

    public InfoImpl(T var){
        this.var=var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    @Override
    public T getVar() {
        return this.var;
    }

     public static void main(String[] args) {

         InfoImpl<String> a = new InfoImpl<>("aaa");
         System.out.println(a.getVar());

     }
}
