package java细节大全.多线程改造for循环;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shejiewei on 2020/4/13.
 */
public class aaa {

    int a;

    Map aa=new ConcurrentHashMap();

    public Map getAa() {
        return aa;
    }

    public void setAa(Map aa) {
        this.aa = aa;
    }
}
