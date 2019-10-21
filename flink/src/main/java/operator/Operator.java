package operator;

import bean.DataSet;

import java.util.List;

/**
 * Created by shejiewei on 2019/10/14.
 */
public abstract class Operator extends DataSet {
    Operator(Object data, List list) {
        super(data, list);
    }
}
