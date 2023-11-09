package controller

import model.Airplane
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirportControllerTest extends AnyWordSpec with Matchers {
  "AirportController" when {
    "given a valid airline name" should {
      "return true" in {
        val airportController = AirportController
        airportController.isAirplaneValid(Airplane("Air Berlin")) shouldBe true
      }
      "return false" in {
        val airportController = AirportController
        airportController.isAirplaneValid(Airplane("Japan Airlines")) shouldBe false
      }
    }
  }
}
