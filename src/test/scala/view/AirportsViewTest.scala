package view

import model.Airports
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirportsViewTest extends AnyWordSpec with Matchers {
  "AirportsView" should {
    "return the correct String representation of airports" in {
      val testAirports = List("Berlin-Tegel", "München", "Dortmund", "Erfurt-Weimar", "Stuttgart")
      val element = Airports(testAirports)
      val list = element.names.sorted
      val text = AirportsView.printAirports(element)
      text shouldBe a[String]
      text should startWith("Airports\n" + "-" * 30 + "\n" + list(0))
      text should include(element.names(2))
      text should endWith(f"%%-20s".format(list.last))
    }
  }
}
