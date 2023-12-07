package controller

import model.Flight
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirportControllerTest extends AnyWordSpec with Matchers {
  "AirportController" should {
    "return a list of airports" in {
      val airports = AirportController.getAirports()
      airports shouldBe a[List[String]]
    }
  }
}
