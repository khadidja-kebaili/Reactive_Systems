package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirportTest extends AnyWordSpec with Matchers {

  "Airport" should {
    "have the correct name" in {
      val airport = Airport("Frankfurt am Main")
      airport.name shouldEqual "Frankfurt am Main"
    }
    "have a meaningful toString representation" in {
      val airport = Airport("Berlin")
      airport.toString shouldEqual "Berlin"
    }
  }

}
