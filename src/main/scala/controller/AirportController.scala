package controller

import model.Airplane

object AirportController {

  val exampleAirport: List[Airplane] = List(
    Airplane("Air Berlin"),
    Airplane("Lufthansa"),
    Airplane("Air France"),
    Airplane("Air Algerie")
  )

  def isAirplaneValid(city: Airplane): Boolean = {
    exampleAirport.contains(city)
  }

  def getAirplanes(): List[Airplane] = {
    return exampleAirport
  }

}
