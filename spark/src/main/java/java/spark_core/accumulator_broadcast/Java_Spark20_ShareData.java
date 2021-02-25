package java.spark_core.accumulator_broadcast;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/**
 使用累加器求和示例
 */

/*
累加器用来对信息进行聚合，通常在向 Spark传递函数时，比如使用 map() 函数或者用 filter() 传条件时，
可以使用驱动器程序中定义的变量，但是集群中运行的每个任务都会得到这些变量的一份新的副本，更新这些副本
的值也不会影响驱动器中的对应变量。如果我们想实现所有分片处理时更新共享变量的功能，那么累加器可以实现
我们想要的效果。
 */


/*
累加器的用法如下所示。
通过在驱动器中调用SparkContext.accumulator(initialValue)方法，创建出存有初始值的累加器。
返回值为 org.apache.spark.Accumulator[T] 对象，其中 T 是初始值 initialValue 的类型。
Spark闭包里的执行器代码可以使用累加器的 += 方法(在Java中是 add)增加累加器的值。
驱动器程序可以调用累加器的value属性(在Java中使用value()或setValue())来访问累加器的值。

注意：工作节点上的任务不能访问累加器的值。从这些任务的角度来看，累加器是一个只写变量。
对于要在行动操作中使用的累加器，Spark只会把每个任务对各累加器的修改应用一次。
因此，如果想要一个无论在失败还是重复计算时都绝对可靠的累加器，我们必须把它放在 foreach() 这样的行动操作中。
转化操作中累加器可能会发生不止一次更新
 */

public class Java_Spark20_ShareData {
    public static void main(String[] args) {
        // 1.初始化spark配置信息并建立与spark的连接
        SparkConf config = new SparkConf().setMaster("local[*]").setAppName("Spark20_ShareData");
        JavaSparkContext sc = new JavaSparkContext(config);

        // val dataRDD: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
        JavaRDD<Integer> dataRDD = sc.parallelize(Arrays.asList(1, 2, 3, 4), 2);
        AtomicReference<Integer> sum = new AtomicReference<>(0);
        //使用累加器来共享变量，来累加数据

        //创建累加器对象
        //var accumulator: LongAccumulator = sc.longAccumulator
        Accumulator<Integer> accumulator = sc.accumulator(0);

        dataRDD.foreach(t -> {
            //执行累加器的累加功能
            accumulator.add(t);
            sum.set(sum.get() + t);
        });

        //获取累加器的值
        System.out.println("未使用累加器求和：" + sum);
        System.out.println("有使用累加器求和：" + accumulator.value());
        //释放资源
        sc.stop();
    }
}
