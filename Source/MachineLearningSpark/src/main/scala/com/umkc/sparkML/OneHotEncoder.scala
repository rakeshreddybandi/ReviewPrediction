package com.umkc.sparkML

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.ml.feature.{IndexToString, StringIndexer}
/**
  * Created by Rakesh on 4/10/2016.
  */
object OneHotEncoder {
  def main(args: Array[String]) {
    System.setProperty("hadoop.home.dir","F:\\winutils")
    val conf = new SparkConf()
      .setAppName("MovieLensALS")
      .set("spark.executor.memory", "2g").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    /*val df = sqlContext.createDataFrame(
      Seq((0, "a"), (1, "b"), (2, "c"), (3, "a"), (4, "a"), (5, "c"))
    ).toDF("id", "category")
    val df2 = sqlContext.createDataFrame(
      Seq((0, "a"), (1, "b"), (2, "d"), (3, "b"), (4, "b"), (5, "b"))
    ).toDF("id", "category")

    val indexer = new StringIndexer()
      .setInputCol("category")
      .setOutputCol("categoryIndex")

    val indexed = indexer.fit(df).transform(df)
    indexed.show()

    indexed.explain()*/
    case class Flight(id: String, category: String)
    def parseFlight(str: String): Flight = {
      val line = str.split("::")
      Flight(line(0), line(1))
    }
    val textRDD = sc.textFile("ReviewTestData/reviews.txt")
    // MapPartitionsRDD[1] at textFile

    // parse the RDD of csv lines into an RDD of flight classes
    val flightsRDD = textRDD.map(parseFlight).cache()
    flightsRDD.first()

    var carrierMap: Map[String, Int] = Map()
    var index: Int = 0

    flightsRDD.map(flight => flight.id).distinct.collect.foreach(x => { carrierMap += (x -> index); index += 1 })
    carrierMap.toString
    carrierMap.foreach(f=>{
      //println(f)
    })

    val x:Int=carrierMap.getOrElse("R1ZG2I7DL3J0S3",0)
    println(x)

    /*indexer.setHandleInvalid("skip")
    val indexed2=indexer.fit(df2).transform(df2)
    indexed2.show()

    val converter = new IndexToString()
      .setInputCol("categoryIndex")
      .setOutputCol("originalCategory")

    val converted = converter.transform(indexed)
    converted.select("id", "originalCategory").show()*/
  }
}
