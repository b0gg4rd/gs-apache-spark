package net.coatli.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class GsSparkApplication {

  public static void main( String[] args ) {
    String logFile = "./src/main/resources/README.md";

    JavaSparkContext javaSparkContext = new JavaSparkContext(
        new SparkConf().setAppName("GS Application"));

    JavaRDD<String> logData = javaSparkContext.textFile(logFile).cache();

    long numAs = logData.filter(new Function<String, Boolean>() {
      public Boolean call(String s) {
        return s.contains("a");
      }
    }).count();

    long numBs = logData.filter(new Function<String, Boolean>() {
      public Boolean call(String s) {
        return s.contains("b");
      }
    }).count();

    System.out.println("Lines with a: " + numAs
        + ", lines with b: " + numBs);

    javaSparkContext.stop();
  }

}
