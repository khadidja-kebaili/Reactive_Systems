package util

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import akka.http.scaladsl.testkit.ScalatestRouteTest
import model.Flight



class FlightTest extends AnyWordSpec with Matchers with ScalatestRouteTest {


  "Airplane" should {
    "have the correct name" in {
      val airplane = Flight(747, "XYZ123", "10:00", "10:15")
      airplane.flightNumber shouldEqual 747
      airplane.airlineName shouldEqual "XYZ123"
      airplane.arrivalTime shouldEqual "10:00"
      airplane.estimatedArrivalTime shouldEqual "10:15"
    }
    "have a meaningful toString representation" in {
      val airplane = Flight(380, "XYZ123", "10:00", "10:15")
      airplane.toString shouldEqual "380, XYZ123, 10:00, 10:15"
    }
  }

 
}
