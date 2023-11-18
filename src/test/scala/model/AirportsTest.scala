package model

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirportsTest extends AnyWordSpec with Matchers {
  "Airports" should {
    val testAirports = List("Berlin-Tegel", "München", "Dortmund", "Erfurt-Weimar", "Stuttgart")
    val element = Airports(testAirports)
    val list = element.airports.sorted
    "have the correct list of airport names" in {
      val list = Airports(testAirports).airports.sorted
      list shouldBe a[List[String]]
      list(0) equals testAirports(0)
      list(1) equals testAirports(1)
      list(2) equals testAirports(2)
      list(3) equals testAirports(3)
      list(4) equals testAirports(4)
    }
    "have the correct toString representation" in {
      val text = element.toString
      text shouldBe a[String]
      text should startWith("Airports\n" + "-" * 30 + "\n" + list(0))
      text should include(element.airports(2))
      text should endWith(f"%%-20s".format(list.last))
    }
  }
}

