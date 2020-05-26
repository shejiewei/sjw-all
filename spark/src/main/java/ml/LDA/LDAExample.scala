/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ml.LDA

// scalastyle:off println
// $example on$
import org.apache.spark.ml.clustering.LDA
// $example off$
import org.apache.spark.sql.SparkSession

/**
 * An example demonstrating LDA.
 * Run with
 * {{{
 * bin/run-example ml.LDAExample
 * }}}
 */
object LDAExample {
  def main(args: Array[String]): Unit = {
    // Creates a SparkSession
    val spark = SparkSession
      .builder
      .appName(s"${this.getClass.getSimpleName}")
          .master("local")
      .getOrCreate()

    // $example on$
    // Loads data.
    val dataset = spark.read.format("libsvm")
      .load("data/mllib/sample_lda_libsvm_data.txt")
    /**
      * k: 主题数，或者聚类中心数
      * DocConcentration：文章分布的超参数(Dirichlet分布的参数)，必需>1.0，值越大，推断出的分布越平滑
      * TopicConcentration：主题分布的超参数(Dirichlet分布的参数)，必需>1.0，值越大，推断出的分布越平滑
      * MaxIterations：迭代次数，需充分迭代，至少20次以上
      * setSeed：随机种子
      * CheckpointInterval：迭代计算时检查点的间隔
      * Optimizer：优化计算方法，目前支持"em", "online" ，em方法更占内存，迭代次数多内存可能不够会抛出stack异常
      */


    // Trains a LDA model.
    val lda = new LDA().setK(10).setMaxIter(10)
    val model = lda.fit(dataset)
    /**生成的model不仅存储了推断的主题，还包括模型的评价方法。*/
    //---------------------------------2 模型评价-------------------------------------

    //模型的评价指标：ogLikelihood，logPerplexity
    //（1）根据训练集的模型分布计算的log likelihood，越大越好。

    val ll = model.logLikelihood(dataset)

    //(2)perplexity评估,越小越好
    val lp = model.logPerplexity(dataset)
    println(s"The lower bound on the log likelihood of the entire corpus: $ll")
    println(s"The upper bound on perplexity: $lp")
    //---------------------------------3 模型及描述------------------------------
    //模型通过describeTopics、topicsMatrix来描述

    //（1）描述各个主题最终的前maxTermsPerTopic个词语（最重要的词向量）及其权重
      // Describe topics.


    /**主题    主题包含最重要的词语序号                     各词语的权重
        +-----+-------------+------------------------------------------+
        |topic|termIndices  |termWeights                               |
        +-----+-------------+------------------------------------------+
        |0    |[5, 4, 0, 1] |[0.21169509638828377, 0.19142090510443274]|
        |1    |[5, 6, 1, 2] |[0.12521929515791688, 0.10175547561034966]|
        |2    |[3, 10, 6, 9]|[0.19885345685860667, 0.18794498802657686]|
        +-----+-------------+------------------------------------------+
      */

    //（2） topicsMatrix: 主题-词分布，相当于phi。

    val topics = model.describeTopics(3)
    println("The topics described by their top-weighted terms:")
    topics.show(false)

    // Shows the result.
    val transformed = model.transform(dataset)
    transformed.show(false)
    // $example off$

    spark.stop()
  }
}
// scalastyle:on println
