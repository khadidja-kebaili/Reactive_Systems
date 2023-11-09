package controller

import model.Airplane

object AirportController {

  val airports: Array[Airplane] = Array(
    new Airplane("Frankfurt am Main"),
    new Airplane("Berlin Brandenburg"),
    new Airplane("Bremen"),
    new Airplane("München"),
    new Airplane("Stuttgart")
  )

  def isCityValid(city: Airplane): Boolean = {
    airports.contains(city)
  }

  def getCities(): Array[Airplane] = {
    return airports
  }

}
