package bean;

import operator.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shejiewei on 2019/10/14.
 */
public abstract class DataSet<T> {

    private T data;

    public String type ;//存放返回類型

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private  List<Operator> operators=new ArrayList<>();
    public DataSet(){

    }

    public DataSet(T data, List<Operator> operators){
        this.data=data;
        this.operators=operators;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Operator> getOperators() {
        return operators;
    }

    public GroupByOperator groupBy(int position) {
        return new GroupByOperator(this,position,getType() );

    }


    public void setOperators(List<Operator> operators) {
        this.operators = operators;
    }

    public FlatMapOperator flatMap(){
     /*   if (flatMapper == null) {
            throw new NullPointerException("FlatMap function must not be null.");
        }*/

        String callLocation = "main";//Utils.getCallLocationName();
        String resultType ="Tuple2<String, Integer>";
        return new FlatMapOperator(this, resultType);
/*
        MapOperator mapOperator = new MapOperator(this.data,this.operators);

        //
       // operators.add(mapOperator);
        return  mapOperator;*/
    }
    public PrintOperator print(){
        PrintOperator printOperator = new PrintOperator(this.data,this.operators);
       // operators.add(printOperator);
        return printOperator;
    }

    public SumOperator sum(){
        SumOperator sumOperator = new SumOperator(this.data,this.operators);
      //  operators.add(sumOperator);
        return sumOperator;
    }

    public void exec(){

        AggregateOperator aggregateOperator = (AggregateOperator) this;
        DataSet dataSet = aggregateOperator.getGroupByOperator().getDataSet();



        System.out.println(this.getData());


    }
}
