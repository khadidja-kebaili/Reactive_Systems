package controller

import model.Airplane

object AirportController {
  val exampleAirport: List[Airplane] = List(
    Airplane("Air Berlin", "XYZ123", "10:00", "10:15"),
    Airplane("Lufthansa", "XYZ123", "10:00", "10:15"),
    Airplane("Air France", "XYZ123", "10:00", "10:15"),
    Airplane("Air Algerie", "XYZ123", "10:00", "10:15")
  )

  def isAirplaneValid(city: Airplane): Boolean = {
    exampleAirport.contains(city)
  }

  def getAirplanes(): List[Airplane] = {
    exampleAirport
  }
}
