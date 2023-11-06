package controller

import model.City

object CityController {

  val cities: Array[City] = Array(
    new City("Berlin"),
    new City("Frankfurt"),
    new City("Stuttgart"),
    new City("München")
  )

  def isCityValid(city: City): Boolean = {
    cities.contains(city)
  }

  def getCities(): Array[City] = {
    return cities
  }

}
