package model

case class Flight(flightNumber: Long, airlineName: String, arrivalTime: String, estimatedArrivalTime: String) {
  
  override def toString: String = s"$flightNumber, $airlineName, $arrivalTime, $estimatedArrivalTime"


}