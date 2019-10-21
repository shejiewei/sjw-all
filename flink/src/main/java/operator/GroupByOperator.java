package operator;

import bean.DataSet;



/**
 * Created by shejiewei on 2019/10/21.
 */
public class GroupByOperator  {
    String type;
    DataSet dataSet;
    int position;
    public <T> GroupByOperator(DataSet<T> tDataSet, int position, String type) {
    this.dataSet=tDataSet;
    this.position=position;
    this.type=type;
    }

    public AggregateOperator sum(int field) {
        return new AggregateOperator(this,"SUM", field, "FlatMap");
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
