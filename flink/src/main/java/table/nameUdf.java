package table;

import org.apache.flink.table.functions.ScalarFunction;

/**
 * Created by shejiewei on 2020/4/28.
 */
public class nameUdf extends ScalarFunction {

    public String DATE_FORMAT;

    public nameUdf() {
        this.DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }

    public nameUdf(String dateFormat) {
        this.DATE_FORMAT = dateFormat;
    }

    public String eval(String name) {
        try {

            return name+"udf";
        } catch (Exception e) {
            return null;
        }
    }

}
