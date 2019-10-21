package operator;

import bean.DataSet;

/**
 * Created by shejiewei on 2019/10/21.
 */
public class AggregateOperator extends DataSet {
    GroupByOperator groupByOperator;
    String operator;
    int field;
    String allocation;

    public AggregateOperator(GroupByOperator groupByOperator, String sum, int field, String flatMap) {
        this.groupByOperator=groupByOperator;
        this.operator=sum;
        this.field=field;
        this.allocation=flatMap;

    }

    public GroupByOperator getGroupByOperator() {
        return groupByOperator;
    }

    public void setGroupByOperator(GroupByOperator groupByOperator) {
        this.groupByOperator = groupByOperator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public String getAllocation() {
        return allocation;
    }

    public void setAllocation(String allocation) {
        this.allocation = allocation;
    }
}
