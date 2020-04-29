package table;

import org.apache.flink.table.functions.ScalarFunction;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shejiewei on 2020/4/28.
 */
public class timeUdf extends ScalarFunction {
    public String DATE_FORMAT;

    public timeUdf() {
        this.DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    }

    public timeUdf(String dateFormat) {
        this.DATE_FORMAT = dateFormat;
    }

    public String eval(String longTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date date = new Date(Long.parseLong(longTime) * 1000);
            return sdf.format(date);
        } catch (Exception e) {
            return null;
        }
    }

}
