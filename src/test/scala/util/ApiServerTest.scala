/*
package util

import akka.actor.ActorSystem
import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.testkit.ScalatestRouteTest
import model.*
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import spray.json.RootJsonFormat

import java.time.LocalTime
import scala.concurrent.ExecutionContext


class ApiServerTest extends AnyWordSpec with Matchers with ScalatestRouteTest with ScalaFutures {
  "ModelJsonConverters" should {
    "provide implicit RootJsonFormat for Airplane" in {
      val converters = new ModelJsonConverters
      val airplaneFormat: RootJsonFormat[Flight] = converters.airplaneFormat
      assert(airplaneFormat != null)
    }

    "provide implicit RootJsonFormat for Airport" in {
      val converters = new ModelJsonConverters
      val airportFormat: RootJsonFormat[Airport] = converters.airportFormat
      assert(airportFormat != null)
    }

    "provide implicit RootJsonFormat for Airports" in {
      val converters = new ModelJsonConverters
      val airportsFormat: RootJsonFormat[Airports] = converters.airportsFormat
      assert(airportsFormat != null)
    }
  }

  "ApiServer" should {
    implicit val system: ActorSystem = ActorSystem("ApiServer")
    implicit val ec: ExecutionContext = system.dispatcher
    val routes = ApiServer.routes

    "extend ModelJsonConverters with SprayJsonSupport" in {
      ApiServer shouldBe a[ModelJsonConverters]
    }

    "return some airports for GET requests to the '/airports' path" in {
      Get("/airports") ~> routes ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should include regex "\"names\":\\["
        entityAs[String] shouldNot include regex "\"arrivals\":\\["
        entityAs[String] shouldNot include regex "\"departures\":\\["
      }
    }

    "return the right airport for GET requests to the '/airport/name' path" in {
      Get("/airport/Frankfurt") ~> routes ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
        entityAs[String] should include regex "\"arrivals\":\\["
        entityAs[String] should include regex "\"departures\":\\["
        entityAs[String] should include regex "\"name\""
      }
    }

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
  }
}
*/
