package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirplaneTest extends AnyWordSpec with Matchers {

  "Airplane" should {
    "have the correct name" in {
      val airplane = Airplane("Boeing 747")
      airplane.name shouldEqual "Boeing 747"
    }
    "have a meaningful toString representation" in {
      val airplane = Airplane("Airbus A380")
      airplane.toString shouldEqual "Airbus A380"
    }
  }

}
