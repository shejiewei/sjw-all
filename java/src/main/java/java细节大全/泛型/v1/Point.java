package java细节大全.泛型.v1;

/**
 * Created by shejiewei on 2019/3/27.
 */
 class Point<T> {
    private T var;

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }

     public static void main(String[] args) {

         Point<String> point = new Point<>();

         point.setVar("aa");
         System.out.println(point.getVar());
     }
}
