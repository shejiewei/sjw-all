package java细节大全.传入字符串做条件判断;

/**
 * Created by shejiewei on 2019/4/29.
 */
public class test {

    static String ss = "a>0&&a!=-999.9f&&a!=999.9f";

    public static void main(String[] args) {
        //javaassist
        float a = 33.0f;
        if (a > 0 && a != -999.9f && a != 999.9f) {

            System.out.println("true");
        }


    }

}
