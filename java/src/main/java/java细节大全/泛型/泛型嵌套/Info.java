package java细节大全.泛型.泛型嵌套;

/**
 * Created by shejiewei on 2019/3/27.
 */
 class Info<T,V> {
     private T key;
     private V value;

     public Info(T key,V value){
         this.key=key;
         this.value=value;
     }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
