package operator;

import bean.DataSet;

/**
 * Created by shejiewei on 2019/10/21.
 */
public class FlatMapOperator extends DataSet {
    DataSet tDataSet;
    String resultType;
    public <T> FlatMapOperator(DataSet<T> tDataSet, String resultType) {
        this.tDataSet=tDataSet;
        this.resultType=resultType;

    }

    public DataSet gettDataSet() {
        return tDataSet;
    }

    public void settDataSet(DataSet tDataSet) {
        this.tDataSet = tDataSet;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
