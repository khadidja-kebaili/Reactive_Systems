package util

import akka.http.scaladsl.server.*
import akka.http.scaladsl.testkit.ScalatestRouteTest
import model.*
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.LocalTime


class ApiServerTest extends AnyWordSpec with Matchers with ScalatestRouteTest with ScalaFutures {
  "ApiServer" should {
    "generate an valid Airplane" in {
      val airplane = ApiServer.airplaneGenerator

      // Validate airline
      airplane.airlineName should not be empty

      // Validate flightNumber
      airplane.flightNumber should startWith("XYZ")
      airplane.flightNumber.drop(4).forall(_.isDigit) shouldBe true

      // Validate arrivalTime
      val arrivalTime = LocalTime.parse(airplane.arrivalTime)
      arrivalTime should not be null

      // Validate estimatedArrivalTime
      val estimatedArrivalTime = LocalTime.parse(airplane.estimatedArrivalTime)
      estimatedArrivalTime should not be null
      estimatedArrivalTime should be >= arrivalTime
    }

    "generate an valid Airport" in {
      val airportName = "Frankfurt"
      val airport = ApiServer.airportGenerator(airportName)
      airport shouldBe a[Airport]
      airport.departures shouldBe a[List[_]]
      airport.arrivals shouldBe a[List[_]]
      airport.name shouldEqual airportName
    }

    "generate a valid AirportsWithData instance" in {
      val data = ApiServer.airportsGenerator()

      data shouldBe a[AirportsWithData]
    }
  }
}
