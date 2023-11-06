package controller

import model.Airline

object AirlineController {

  val airlines: Array[Airline] = Array(
    new Airline("Air Berlin"),
    new Airline("Lufthansa"),
    new Airline("Air France"),
    new Airline("Air Algerie")
  )

  def isAirlineValid(city: Airline): Boolean = {
    airlines.contains(city)
  }

  def getAirline(): Array[Airline] = {
    return airlines
  }


}
