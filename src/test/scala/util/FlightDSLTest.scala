package util

import model.Flight
import util.FlightDSL
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FlightDSLTest extends AnyWordSpec with Matchers {

  "FlightDSL" should {
    "parse valid input correctly" in {
      val input = "Vorname:Liam;Nachname:Johnson;Von:Berlin;Nach:Paris;Uhrzeit:14:45;Sitz:1A;Gate:2D;Boarding:14:25;1234;Airline:Air Berlin"

      val expectedFlight = Flight(1234, "Lufthansa", "14:45", "14:45")

      FlightDSL.parseFlight(input) shouldBe Some(expectedFlight)
    }

    "return None for invalid input" in {
      val invalidInput = "InvalidInput"

      FlightDSL.parseFlight(invalidInput) shouldBe None
    }
  }
}
