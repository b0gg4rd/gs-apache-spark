package net.coatli.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class GsSparkApplication {

  public static void main( final String[] args ) {

    final String logFile = "./src/main/resources/README.md";

    final SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();

    final Dataset<String> logData = spark.read().textFile(logFile).cache();

    final long numAs = logData.filter(s -> s.contains("a")).count();

    final long numBs = logData.filter(s -> s.contains("b")).count();

    System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

    spark.stop();

  }

}
