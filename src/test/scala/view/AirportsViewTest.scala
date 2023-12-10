package view

import model.{Airport, Flight}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class AirportsViewTest extends AnyWordSpec with Matchers {
  "AirportsView" should {
    var flight_I: Flight = Flight(1, "Lufthansa", "08:10", "09:20")
    var flight_II: Flight = Flight(2, "Air Algerie", "19:00", "19:00")
    var flight_III: Flight = Flight(3, "Air France", "11:40", "11:43")
    var flights: List[Flight] = List(flight_I, flight_II, flight_III)
    var airport_I: Airport = Airport("Berlin", flights, flights)
    var airport_II: Airport = Airport("London", flights, flights)
    var airport_III: Airport = Airport("Paris", flights, flights)
    var airports: List[Airport] = List(airport_I, airport_II, airport_III)
    "return the correct String representation of airports" in {
      val element = airports
      val list = List(element.foreach(elem => elem.name.sorted))
      val text = AirportsView.printAirports(element)
      text shouldBe a[String]
      text should startWith("Airports\n" + "-" * 30 + "\n" + list(0))
      //text should include(element.foreach(elem => elem.name(2)))
      //text should endWith(f"%%-20s".format(list.last))
    }
  }
}
