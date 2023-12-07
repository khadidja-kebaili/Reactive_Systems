package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class FlightTest extends AnyWordSpec with Matchers {

  "Airplane" should {
    "have the correct name" in {
      val airplane = Flight("Boeing 747", "XYZ123", "10:00", "10:15")
      airplane.airlineName shouldEqual "Boeing 747"
      airplane.flightNumber shouldEqual "XYZ123"
      airplane.arrivalTime shouldEqual "10:00"
      airplane.estimatedArrivalTime shouldEqual "10:15"
    }
    "have a meaningful toString representation" in {
      val airplane = Flight("Airbus A380", "XYZ123", "10:00", "10:15")
      airplane.toString shouldEqual "Airbus A380, XYZ123, 10:00, 10:15"
    }
  }

}
