package bean;

import operator.MapOperator;
import operator.Operator;
import operator.PrintOperator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2019/10/14.
 */
public abstract class DataSet<T> {

    private T data;

    private static List<Operator> operators=new ArrayList<>();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    public MapOperator map(){
        MapOperator mapOperator = new MapOperator();
        operators.add(mapOperator);
        return  mapOperator;
    }
    public PrintOperator print(){
        PrintOperator printOperator = new PrintOperator();
        operators.add(printOperator);
        return printOperator;
    }

    public void exec(){
        for (int i=0;i<operators.size();i++)
        {
            String d=(String)data;
            MapOperator operator = (MapOperator)operators.get(i);
            Object map = operator.map(d, " ");

        }
    }
}
