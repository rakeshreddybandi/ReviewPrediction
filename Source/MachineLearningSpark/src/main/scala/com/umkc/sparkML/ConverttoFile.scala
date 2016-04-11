package com.umkc.sparkML

import java.io.{PrintWriter, File}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by Rakesh on 4/8/2016.
  */
object ConverttoFile {
  def main(args: Array[String]) {
    System.setProperty("hadoop.home.dir","F:\\winutils")
    Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    //if (args.length != 2) {
    //  println("Usage: /path/to/spark/bin/spark-submit --driver-memory 2g --class MovieLensALS " +
    //   "target/scala-*/movielens-als-ssembly-*.jar movieLensHomeDir personalRatingsFile")
    // sys.exit(1)
    //}

    // set up environment

    val conf = new SparkConf()
      .setAppName("MovieLensALS")
      .set("spark.executor.memory", "2g").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    val prod = new PrintWriter(new File("AmazonTrainData/cameras/products.dat"))
    val reviewspw = new PrintWriter(new File("AmazonTrainData/cameras/reviews.dat"))
    // Create the DataFrame
    val df = sqlContext.read.json("AmazonReviews/*.json")
    df.registerTempTable("TableReviews")
    // Show the content of the DataFrame
    df.show()
    // age  name
    // null Michael
    // 30   Andy
    // 19   Justin

    // Print the schema in a tree format
    df.printSchema()
    // root
    // |-- age: long (nullable = true)
    // |-- name: string (nullable = true)
   val reviews= df.select("Reviews")
   // df.select("name", "age").write.format("parquet").save("namesAndAges.parquet")
    reviews.printSchema()

    val nameAndAddress = sqlContext.sql("SELECT ProductInfo.ProductID, ProductInfo.Name,ProductInfo.Price,ProductInfo.ImgURL,ProductInfo.Features FROM TableReviews")
    nameAndAddress.collect.foreach( x=>{println
print(x(1))
      //val productss = df.filter("ProductInfo.productID=x._1").select("Reviews")
      //val nameAndAddress = sqlContext.sql("SELECT Reviews. FROM TableReviews WHERE ProductInfo.ProductID="+x(0))
     /* val productssArr = productss.map(row => row.getSeq[org.apache.spark.sql.Row](0))
      productssArr.foreach(productss => {productss.map(row =>

        print(row.getString(1), row.getString(0))+"\n")
        //val nameAndAddress = sqlContext.sql("SELECT ProductInfo.ProductID, ProductInfo.Price, ProductInfo.ProductID FROM TableReviews")
        //nameAndAddress.collect.foreach(println)
        print("\n")

      })*/


      val filtered=df.filter(df("ProductInfo.ProductID") === x(0))


      val schools = filtered.select("Reviews").collect()

      val schoolsArr= schools.map(row =>row.getSeq[org.apache.spark.sql.Row](0))
      schoolsArr.foreach(schools => {schools.map(row=>
        //print(x(0),row.getString(1))
        prod.write(x(0)+"::"+x(1)+"::"+x(2)+"::"+x(3)+"::"+x(4)+"::"+row.getString(4)+"::"+row.getString(3)+ "\n")
      ) }
      )


    }
    )




   val schools = df.select("Reviews").collect()
    val schoolsArr = schools.map(row => row.getSeq[org.apache.spark.sql.Row](0))
    schoolsArr.foreach(schools => {schools.map(row =>

     // print(row.getString(2), row.getString(5))+"\n")
      reviewspw.write(row.getString(4)+"::"+row.getString(5)+"::"+row.getString(1)+"::"+row.getString(0)+"::"+row.getString(3)+"::"+row.getString(2)+"\n")

    )
      })
    prod.close()
    reviewspw.close()
  }
}
