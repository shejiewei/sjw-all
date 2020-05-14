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

// scalastyle:off println
package ml.tfidf

// $example on$
import org.apache.spark.ml.feature.{HashingTF, IDF, Tokenizer}
// $example off$
import org.apache.spark.sql.SparkSession

object TfIdfExample {

  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("TfIdfExample")
        .master("local")
      .getOrCreate()
//-Dspark.master=local

    // $example on$
    val sentenceData = spark.createDataFrame(Seq(
      (0.0, "Hi I heard about Spark"),
      (0.0, "I wish Java could use case classes"),
      (1.0, "Logistic regression models are neat")
    )).toDF("label", "sentence")

    //  scala> sentenceData.show
    //  +-----+--------------------+
    //  |label|            sentence|
    //  +-----+--------------------+
    //  |    0|Hi I heard about ...|
    //  |    0|I wish Java could...|
    //  |    1|Logistic regressi...|
    //  +-----+--------------------+

    //句子转化成单词数组

    val tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")
    val wordsData = tokenizer.transform(sentenceData)


  wordsData.show()
    // scala> wordsData.show
    //  +-----+--------------------+--------------------+
    //  |label|            sentence|               words|
    //  +-----+--------------------+--------------------+
    //  |    0|Hi I heard about ...|ArrayBuffer(hi, i...|
    //  |    0|I wish Java could...|ArrayBuffer(i, wi...|
    //  |    1|Logistic regressi...|ArrayBuffer(logis...|
    //  +-----+--------------------+--------------------+

    // hashing计算TF值,同时还把停用词(stop words)过滤掉了. setNumFeatures(20)表最多20个词
    val hashingTF = new HashingTF()
      .setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(20)
    // scala> featurizedData.show
    //  +-----+--------------------+--------------------+--------------------+
    //  |label|            sentence|               words|         rawFeatures|
    //  +-----+--------------------+--------------------+--------------------+
    //  |    0|Hi I heard about ...|ArrayBuffer(hi, i...|(20,[5,6,9],[2.0,...|
    //  |    0|I wish Java could...|ArrayBuffer(i, wi...|(20,[3,5,12,14,18...|
    //  |    1|Logistic regressi...|ArrayBuffer(logis...|(20,[5,12,14,18],...|
    //  +-----+--------------------+--------------------+--------------------+

    val featurizedData = hashingTF.transform(wordsData)
    //featurizedData.show()
    // alternatively, CountVectorizer can also be used to get term frequency vectors
    featurizedData.select("rawFeatures").show()

    val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
    val idfModel = idf.fit(featurizedData)

    val rescaledData = idfModel.transform(featurizedData)
    rescaledData.show()
    rescaledData.select("label", "features").show()
    // $example off$
    // 提取该数据中稀疏向量的数据,稀疏向量:SparseVector(size,indices,values)
    // rescaledData.select("features").rdd.map(row => row.getAs[linalg.Vector](0)).map(x => x.toSparse.indices).collect
    //rescaledData.select("features", "label").take(3).foreach(println)

    //  [(20,[5,6,9],[0.0,0.6931471805599453,1.3862943611198906]),0]
    //  [(20,[3,5,12,14,18],[1.3862943611198906,0.0,0.28768207245178085,0.28768207245178085,0.28768207245178085]),0]
    //  [(20,[5,12,14,18],[0.0,0.5753641449035617,0.28768207245178085,0.28768207245178085]),1]
    // 其中,20是标签总数,下一项是单词对应的hashing ID.最后是TF-IDF结果

    spark.stop()
  }
}
// scalastyle:on println
