package controller

import model.Airplane
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirportControllerTest extends AnyWordSpec with Matchers {
  "AirportController" when {
    "given a valid airline name" should {
      "return true" in {
        val airportController = AirportController.isAirplaneValid(Airplane("Air Berlin", "XYZ123", "10:00", "10:15"))
        airportController shouldBe true
      }
      "return false" in {
        val airportController = AirportController.isAirplaneValid(Airplane("Japan Airlines", "XYZ123", "10:00", "10:15"))
        airportController shouldBe false
      }
    }
    "return a list of airplanes" in {
      val airplanes = AirportController.getAirplanes()
      airplanes shouldBe a[List[Airplane]]
    }
  }
}
