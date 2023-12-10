package util
import model.*
import util.FlightAPI
import util.ModelJsonConverter
import akka.http.scaladsl.client.RequestBuilding.Get
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.actor.ActorSystem
import akka.http.scaladsl.model.*
import akka.http.scaladsl.server.*
import akka.http.scaladsl.server.Directives.*
import akka.http.scaladsl.unmarshalling.Unmarshaller
import org.scalatest.concurrent.ScalaFutures
import spray.json.RootJsonFormat
import scala.concurrent.Future
class FlightAPITest extends AnyWordSpec with Matchers with ScalatestRouteTest {
  val route = FlightAPI.route


  // Import your models and other necessary dependencies here

  "FlightAPI" should {

    "return a single flight for valid flight number" in {
      val flightNumber = 123
      val expectedFlight = Flight(123, "XYZ123", "10:00", "10:15")

      // Send a GET request to "/flight/{flightNumber}" and check the response
     /* Get(s"/flight/$flightNumber") ~> route ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
      }*/
    }

    "return a list of flights" in {
      val expectedFlights = List(
        Flight(1, "AirlineA", "DepA", "ArrA"),
        Flight(2, "AirlineB", "DepB", "ArrB")
      )


      // Send a GET request to "/flights" and check the response
      Get("/flights") ~> route ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`application/json`)
      }
    }

    // Similar tests for other endpoints...

  }

}
