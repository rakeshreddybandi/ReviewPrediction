package com.umkc.sparkML

import java.io._
import java.net.ServerSocket
import java.nio.file.{Paths, Files}


import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer
import scala.io.Source

/**
  * Created by Rakesh on 4/10/2016.
  */
object ReviewAnalysis {
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


    //socket
     try {
       val listener = new ServerSocket(5252);

       val accept=listener.accept();
       val in = new BufferedReader(new InputStreamReader(accept.getInputStream)).readLine
       println(in.toString())
     //  val prod = new PrintWriter(new File("ourratings.dat"))

    //  prod.write(in.toString)


    // load personal ratings

    val myRatings = loadRatings("myRating.txt")
    val myRatingsRDD = sc.parallelize(myRatings, 1)

    // load ratings and movie titles
    //add here
    // sc is an existing SparkContext.
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    // this is used to implicitly convert an RDD to a DataFrame.
    import sqlContext.implicits._
    case class Person(ReviewID: String, Title: String,Content: String,Author: String,Rating: String,Date: String)

    // Create an RDD of Person objects and register it as a table.
    // val people = sc.textFile("AmazonTrainData/cameras/reviews.dat").map(_.split("::")).map(p => Person(p(0), p(1),p(2),p(3),p(4),p(5))).toDF()
    // people.registerTempTable("people")
    val movieLensHomeDir = "AmazonTrainData"

    //code to transform the string data
    case class ReviewCase(ReviewID: String, Title: String,Content: String,Author: String,Rating: String,Date: String)

    def parseReview(str: String): ReviewCase = {
      val line = str.split("::")

      ReviewCase(line(0), line(1), line(2), line(3), line(4), line(5))
    }
    val ReviewtextRDD = sc.textFile(movieLensHomeDir+"/cameras/reviews.dat")

    // parse the RDD of csv lines into an RDD of Review classes
    val ReviewsRDD = ReviewtextRDD.map(parseReview).cache()
    ReviewsRDD.first()

    var ReviewIDMap: Map[String, Int] = Map()
    var index1: Int = 0

    ReviewsRDD.map(rev => rev.ReviewID).distinct.collect.foreach(x => { ReviewIDMap += (x -> index1); index1 += 1 })
    ReviewIDMap.toString
    ReviewIDMap.foreach(f=>{
      //println(f)
    })

    val x:Int=ReviewIDMap.getOrElse("R1ZG2I7DL3J0S3",0)


    //code to convert productID to int
    case class ProductCase(ProductID: String, Name: String,Price: String,ImgURL: String,Features: String,ReviewID: String,Rating:Double)

    def parseProduct(str: String): ProductCase = {
      val line = str.split("::")
      ProductCase(line(0), line(1), line(2), line(3), line(4), line(5),line(6).toDouble)
    }
    val ProducttextRDD = sc.textFile(movieLensHomeDir+"/cameras/products.dat")
    // parse the RDD of csv lines into an RDD of Review classes
    val ProductRDD = ProducttextRDD.map(parseProduct).cache()
    ProductRDD.first()

    var ProductIDMap: Map[String, Int] = Map()
    var index2: Int = 0

    ProductRDD.map(prod => prod.ProductID).distinct.collect.foreach(x => { ProductIDMap += (x -> index2); index2 += 1 })
    ProductIDMap.toString
    ProductIDMap.foreach(f=>{
      //println(f)
    })

    val x1:Int=ProductIDMap.getOrElse("1435460855",0)
    println(x1)

    val movies = ReviewsRDD.map(rev => {

      (ReviewIDMap(rev.ReviewID),rev.ReviewID )

    }).collect().toMap


    val ratings=ProductRDD.map(prod=>{
      Rating(ProductIDMap(prod.ProductID), ReviewIDMap(prod.ReviewID), prod.Rating)
    })


    //mycode
    if (!Files.exists(Paths.get("models/cameras"))) {

      val numPartitions = 4
      val training = ratings.union(myRatingsRDD)
        .repartition(numPartitions)
        .cache()
      // Build the recommendation model using ALS
      val rank = 12
      val numIterations = 20
      val model = ALS.train(training, rank, numIterations, 0.01)



      model.save(sc,"models/cameras/")
    }
    else{
      println("Model file already exists, skipping Naive Bayes model formation..")
    }
    //model.save(sc,"camerasmodel")

    val ldmodel=MatrixFactorizationModel.load(sc,"models/cameras/")
    val myRatedMovieIds = myRatings.map(_.product).toSet
    val candidates = sc.parallelize(movies.keys.filter(!myRatedMovieIds.contains(_)).toSeq)
    val recommendations = ldmodel
      .predict(candidates.map((0, _)))
      .collect()
      .sortBy(-_.rating)
      .take(10)

    var i = 1
    var str="Hi"
    println("Reviews recommended for you:")


    val temp = new PrintWriter(new File("temp.dat"))
    val builder = StringBuilder.newBuilder
    var a =new ListBuffer[String]()
    recommendations.foreach { r =>
      println("%2d".format(i) + ": " + movies(r.product))
      //print reviews

    //  a+=movies(r.product)

       ReviewsRDD.filter(f=>f.ReviewID.equals(movies(r.product))).foreach(f=>{
              println(f.Content)
              // val x11=f.Content

         a+=f.Content

            })

      i += 1
    }


    /*ReviewsRDD.foreach(rev => {
      if (rev.ReviewID.equals(alist(5))) {
        println(rev.Content)
        builder.append(rev.Content+"\n")

      }
    })
*/
    println(builder.toString())
    // clean up


    //  val out = new PrintStream(accept.getOutputStream)
     val out = new PrintStream(accept.getOutputStream)
       //println(str)
       out.println("Hello")
       out.flush()
       out.close()
       accept.close();
       listener.close();
       sc.stop()
     }
     catch {
       case e: IOException =>
         System.err.println("Could not listen on port: 9999.");
         System.exit(-1)
     }
  }

  def loadRatings(path: String): Seq[Rating] = {
    val lines = Source.fromFile(path).getLines()
    val ratings = lines.map { line =>
      val fields = line.split("::")
      Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble)
    }.filter(_.rating > 0.0)
    if (ratings.isEmpty) {
      sys.error("No ratings provided.")
    } else {
      ratings.toSeq
    }


    /* val ratings = sc.textFile(new File(movieLensHomeDir, "products.txt").toString).map { line =>
       val fields = line.split("::")
       // format: (timestamp % 10, Rating(userId, movieId, rating))
       (1, Rating(fields(0).toInt, fields(5).toInt, fields(6).toDouble))
     }

    val movies = sc.textFile(new File(movieLensHomeDir, "reviews.txt").toString).map { line =>
       val fields = line.split("::")
       // format: (movieId, movieName)
       (fields(0).toInt, fields(1))
     }.collect().toMap*/

    /* val numRatings = ratings.count()
     val numUsers = ratings.map(_._2.user).distinct().count()
     val numMovies = ratings.map(_._2.product).distinct().count()

     println("Got " + numRatings + " ratings from "
       + numUsers + " users on " + numMovies + " movies.")

     // split ratings into train (60%), validation (20%), and test (20%) based on the
     // last digit of the timestamp, add myRatings to train, and cache them

     val numPartitions = 4
     val training = ratings.filter(x => x._1 < 6)
       .values
       .union(myRatingsRDD)
       .repartition(numPartitions)
       .cache()
     val validation = ratings.filter(x => x._1 >= 6 && x._1 < 8)
       .values
       .repartition(numPartitions)
       .cache()
     val test = ratings.filter(x => x._1 >= 8).values.cache()

     val numTraining = training.count()
     val numValidation = validation.count()
     val numTest = test.count()

     println("Training: " + numTraining + ", validation: " + numValidation + ", test: " + numTest)

     // train models and evaluate them on the validation set

     val ranks = List(8, 12)
     val lambdas = List(0.1, 10.0)
     val numIters = List(10, 20)
     var bestModel: Option[MatrixFactorizationModel] = None
     var bestValidationRmse = Double.MaxValue
     var bestRank = 0
     var bestLambda = -1.0
     var bestNumIter = -1
     for (rank <- ranks; lambda <- lambdas; numIter <- numIters) {
       val model = ALS.train(training, rank, numIter, lambda)
       val validationRmse = computeRmse(model, validation, numValidation)
       println("RMSE (validation) = " + validationRmse + " for the model trained with rank = "
         + rank + ", lambda = " + lambda + ", and numIter = " + numIter + ".")
       if (validationRmse < bestValidationRmse) {
         bestModel = Some(model)
         bestValidationRmse = validationRmse
         bestRank = rank
         bestLambda = lambda
         bestNumIter = numIter
       }
     }

     // evaluate the best model on the test set

     val testRmse = computeRmse(bestModel.get, test, numTest)

     println("The best model was trained with rank = " + bestRank + " and lambda = " + bestLambda
       + ", and numIter = " + bestNumIter + ", and its RMSE on the test set is " + testRmse + ".")

     // create a naive baseline and compare it with the best model

     val meanRating = training.union(validation).map(_.rating).mean
     val baselineRmse =
       math.sqrt(test.map(x => (meanRating - x.rating) * (meanRating - x.rating)).mean)
     val improvement = (baselineRmse - testRmse) / baselineRmse * 100
     println("The best model improves the baseline by " + "%1.2f".format(improvement) + "%.")

     // make personalized recommendations

     val myRatedMovieIds = myRatings.map(_.product).toSet
     val candidates = sc.parallelize(movies.keys.filter(!myRatedMovieIds.contains(_)).toSeq)
     val recommendations = bestModel.get
       .predict(candidates.map((0, _)))
       .collect()
       .sortBy(-_.rating)
       .take(5)

     var i = 1
     val str=new StringBuilder()
     println("Restaurants recommended for you:")
     // SocketClient.sendCommandToRobot("Hello1")
     recommendations.foreach { r =>
       println("%2d".format(i) + ": " + movies(r.product))
       str.append("\n%2d".format(i) + ": " + movies(r.product))
       i += 1

     }
     val stat=SocketClient.sendCommandToRobot(str.toString())

     // clean up
     // sc.stop()
   }

   /** Compute RMSE (Root Mean Squared Error). */
   def computeRmse(model: MatrixFactorizationModel, data: RDD[Rating], n: Long): Double = {
     val predictions: RDD[Rating] = model.predict(data.map(x => (x.user, x.product)))
     val predictionsAndRatings = predictions.map(x => ((x.user, x.product), x.rating))
       .join(data.map(x => ((x.user, x.product), x.rating)))
       .values
     math.sqrt(predictionsAndRatings.map(x => (x._1 - x._2) * (x._1 - x._2)).reduce(_ + _) / n)
   }

   /** Load ratings from file. */
   def loadRatings(path: String): Seq[Rating] = {
     val lines = Source.fromFile(path).getLines()
     val ratings = lines.map { line =>
       val fields = line.split("::")
       Rating(fields(0).toInt, fields(1).toInt, fields(2).toDouble)
     }.filter(_.rating > 0.0)
     if (ratings.isEmpty) {
       sys.error("No ratings provided.")
     } else {
       ratings.toSeq
     }*/
  }
}
