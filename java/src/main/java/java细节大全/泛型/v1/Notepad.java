package java细节大全.泛型.v1;

/**
 * Created by shejiewei on 2019/3/27.
 */
class Notepad<K,V> {

    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
     public static void main(String[] args) {
         Notepad<String, Integer> notepad = new Notepad<>();
         notepad.setKey("aa");
         notepad.setValue(12);

         System.out.println(notepad.getKey()+"+"+notepad.getValue());
     }
}

