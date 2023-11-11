package model

case class Airplane(airlineName: String, flightNumber: String, arrivalTime: String, estimatedArrivalTime: String) {
  
  override def toString: String = s"$airlineName, $flightNumber, $arrivalTime, $estimatedArrivalTime"


}