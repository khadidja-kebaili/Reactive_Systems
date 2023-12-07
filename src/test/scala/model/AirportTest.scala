package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirportTest extends AnyWordSpec with Matchers {

  "Airport" should {
    "have the correct name" in {
      val arrivals: List[Flight] = List()
      val departures: List[Flight] = List()
      val airport = Airport("Frankfurt am Main", arrivals, departures)
      airport.name shouldEqual "Frankfurt am Main"
    }
    "have a meaningful toString representation" in {
      val arrivals: List[Flight] = List()
      val departures: List[Flight] = List()
      val airport = Airport("Berlin", arrivals, departures)
      airport.toString shouldEqual "Berlin"
    }
  }

}
