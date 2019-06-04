/*
package geoSpark

import org.datasyslab.geospark.spatialRDD.PointRDD;
import org.datasyslab.geospark.spatialRDD.PointRDD;
/**
  * Created by shejiewei on 2019/6/4.
  */
  *
   *
  Suppose we have a checkin.csv CSV file at Path /Download/checkin.csv as follows:


-88.331492,32.324142,hotel
-88.175933,32.360763,gas
-88.388954,32.357073,bar
-88.221102,32.35078,restaurant
object point {


  def main(args: Array[String]): Unit = {
val pointRDDInputLocation = "/Download/checkin.csv"
val pointRDDOffset = 0 // The point long/lat starts from Column 0
val pointRDDSplitter = FileDataSplitter.CSV
val carryOtherAttributes = true // Carry Column 2 (hotel, gas, bar...)
var objectRDD = new PointRDD(sc, pointRDDInputLocation, pointRDDOffset, pointRDDSplitter, carryOtherAttributes)
  }
}
*/
