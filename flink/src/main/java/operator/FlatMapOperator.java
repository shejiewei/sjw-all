package operator;

import bean.DataSet;

/**
 * Created by shejiewei on 2019/10/21.
 */
public class FlatMapOperator extends DataSet {
    DataSet tDataSet;

    public <T> FlatMapOperator(DataSet<T> tDataSet, String resultType) {
        this.tDataSet=tDataSet;

        super.type=resultType;
    }

    public DataSet gettDataSet() {
        return tDataSet;
    }

    public void settDataSet(DataSet tDataSet) {
        this.tDataSet = tDataSet;
    }


}
