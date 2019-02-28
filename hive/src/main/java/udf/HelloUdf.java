package udf;

/**
 * Created by Administrator on 2019/2/28.
 */
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;


public class HelloUdf extends UDF {
    public Text evaluate (final Text t) {
        if (t == null) return null;

        return new Text("Hello:" + t);
    }
    public static void main(String [] args){
        System.out.println(new HelloUdf().evaluate(new Text("hero")));
    }
}
