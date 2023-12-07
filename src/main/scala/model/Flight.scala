package model

case class Flight(flightNumber: String, airlineName: String, arrivalTime: String, estimatedArrivalTime: String) {
  
  override def toString: String = s"$flightNumber, $airlineName, $arrivalTime, $estimatedArrivalTime"


}