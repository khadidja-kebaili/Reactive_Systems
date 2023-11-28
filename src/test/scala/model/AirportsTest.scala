package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirportsTest extends AnyWordSpec with Matchers {
  "Airports" should {
    val testAirports = List("Berlin-Tegel", "München", "Dortmund", "Erfurt-Weimar", "Stuttgart")
    val element = Airports(testAirports)
    val list = element.names.sorted
    "have the correct list of airport names" in {
      val list = Airports(testAirports).names.sorted
      list shouldBe a[List[String]]
      list(0) equals testAirports(0)
      list(1) equals testAirports(1)
      list(2) equals testAirports(2)
      list(3) equals testAirports(3)
      list(4) equals testAirports(4)
    }
  }
}

