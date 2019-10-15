package operator;

/**
 * Created by shejiewei on 2019/10/14.
 */
public class PrintOperator extends  Operator {

    public void print(String data){
        String[] split = data.split(",");
        for (int i=0;i<split.length;i++)
        {
            System.out.println(split);
        }
    }

}
