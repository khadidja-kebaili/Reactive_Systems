package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import util.ApiServer

class AirportsWithDataTest extends AnyWordSpec with Matchers {

  "AirportsWithData" should {
    val airport1 = ApiServer.airportGenerator("Berlin")
    val airport2 = ApiServer.airportGenerator("Frankfurt")
    val airportsList = List(airport1, airport2)
    val airportsWithData = AirportsWithData(airportsList)

    "be created with a list of Airports" in {
      airportsWithData.airports shouldEqual airportsList
    }

    "return the correct number of airports" in {
      airportsWithData.airports.length shouldEqual 2
    }
  }
}
